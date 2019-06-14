import json
import psycopg2
import db
from elasticsearch import helpers


file = open('data/movies_processed.json', encoding='utf8')

with open('data/genres.json', encoding='utf8') as f:
    genres = {x['tmdb']: (x['id'], x['name']) for x in json.loads(f.read())['genres']}

(conn, cursor) = db.open_sql()

processed = set()
cursor.execute('SELECT tmdb FROM Movie')
for i in cursor.fetchall():
    processed.add(i[0])

people = set()
cursor.execute('SELECT tmdb FROM MEMBER')
for i in cursor.fetchall():
    people.add(i[0])

companies = set()
cursor.execute('SELECT id FROM COMPANY')
for i in cursor.fetchall():
    companies.add(i[0])


def populate_sql(movie):
    cursor.execute('''
            INSERT INTO Movie (tmdb, name, poster, backdrop, plot, imdb, language, tagline, release, runtime, ratingsum, ratingcount, favouritecount, watchCount)
            VALUES (%(id)s,%(name)s,%(poster)s,%(backdrop)s,%(plot)s,%(imdb)s,%(language)s,%(tagline)s,%(release)s,%(runtime)s,0,0,0,0)
        ''', movie)

    for person in movie['cast'] + movie['crew']:
        if person['id'] in people:
            cursor.execute('''
                INSERT INTO MovieMember (role, memberid, movieid, isActor)
                VALUES (%s, %s, %s, %s)
            ''', (person['role'][:255], person['id'], movie['id'], person in movie['cast']))

    for genre in movie['genres']:
        cursor.execute('''
            INSERT INTO MovieGenre (movieid, genreid)
            Values (%s, %s)
            ON CONFLICT (movieid, genreid)
            DO NOTHING
        ''', (movie['id'], genres[genre][0]))

    for company in movie['companies']:
        if company in companies:
            cursor.execute('''
                INSERT INTO MovieCompany (movieid, companyid)
                VALUES (%s, %s)
            ''' , (movie['id'], company))

    for img in movie['posters']:
        cursor.execute('''
            INSERT INTO MEDIA (movieid, type, path)
            VALUES (%s, 'p', %s)
        ''', (movie['id'], img))

    for img in movie['backdrops']:
        cursor.execute('''
            INSERT INTO MEDIA (movieid, type, path)
            VALUES (%s, 'b', %s)
        ''', (movie['id'], img))

    for video in movie['videos']:
        cursor.execute('''
            INSERT INTO MEDIA (movieid, type, path)
            VALUES (%s, 'v', %s)
        ''', (movie['id'], video['path']))

def populate_sql_recommended(movie):
    #TODO verificar nome da tabela
    for m in movie['recommended']:
        cursor.execute('''
            INSERT INTO Recommended
            VALUES (%s, %s)
        ''', (movie['id'], m))


def populate_es(docs):
    es = db.elasticsearch()
    helpers.bulk(es, docs,  request_timeout=200)


docs = []
current = 0
for line in file:
    if current % 1000 == 0:
        print(current)
    current += 1
    
    movie = json.loads(line)
    
    if movie['id'] not in processed:
        movie['release'] = movie['release'] if movie['release'] != '' else '9999-01-01'
        populate_sql(movie)

        #elasticsearch doc
        docs.append({
            '_index': 'movieverse_movies',
            '_id': movie['id'],
            '_source': {
                'name': movie['name'],
                'release': movie['release'],
                'rating': 0,
                'genre': [genres[x][1] for x in movie['genres']],
                'poster': movie['poster'] 
            }
        })

        processed.add(movie['id'])

processed = set()
docs = []
current = 0
for line in file:
    if current % 1000 == 0:
        print(current)
    current += 1

    movie = json.loads(line)

    if movie['id'] not in processed:
        populate_sql_recommended(movie)

file.close()
conn.commit()
populate_es(docs)
