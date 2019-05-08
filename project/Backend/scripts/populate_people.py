import json
import psycopg2
import db
from elasticsearch import helpers

(conn, cursor) = db.open_sql()

file = open('data/people_processed.json', encoding='utf8')

processed = set()
cursor.execute('SELECT tmdb FROM Member')
for i in cursor.fetchall():
    processed.add(i[0])


def populate_sql(person):
    cursor.execute('''
        INSERT INTO Member (tmdb, name, image, birthdate, gender, imdb, biography, birthplace)
        VALUES (%(id)s,%(name)s,%(image)s,%(birthdate)s,%(gender)s,%(imdb)s,%(biography)s,%(birthplace)s)
    ''', person)

    for img in person['images']:
        cursor.execute('''
            INSERT INTO MEDIA (memberid, type, path)
            VALUES (%s, 'i', %s)
        ''', (person['id'], img))


def populate_es(docs):
    es = db.elasticsearch()
    helpers.bulk(es, docs)


docs = []
current = 0
for line in file:
    if current % 1000 == 0:
        print(current)
    current += 1
    person = json.loads(line, encoding='utf8')

    if person['id'] not in processed:
        populate_sql(person)
        processed.add(person['id'])

        docs.append({
            '_index': 'movieverse_people',
            '_id': person['id'],
            '_source': {
                'name': person['name'],
                'image': person['image']
            }
        })

file.close()
conn.commit()
conn.close()
populate_es(docs)