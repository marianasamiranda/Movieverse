from requests import get
from bs4 import BeautifulSoup
import re
from collections import defaultdict
from time import sleep
import random
import json
import db

url_prefix = 'https://www.imdb.com/showtimes/cinema/PT/'
struct = defaultdict(dict)
(conn, cursor) = db.open_sql()

#cinemas
with open('data/theaters.json', encoding='utf8') as f:
    cinemas = json.loads(f.read())

#countries
#with open('data/countries.json', encoding='utf8') as f:
#    countries = {v:k for k,v in json.loads(f.read()).items()}
cursor.execute('SELECT * FROM Country')
countries = {x[1]:(x[0],x[2]) for x in cursor.fetchall()}


for cine_name, cine_data in cinemas.items():
    print(cine_name)

    #add theater to db if not exists
    cursor.execute('''
        INSERT INTO Theater
        Values(%s,%s,%s,%s,%s)
        ON CONFLICT (id)
        DO NOTHING
    ''', (cine_data['id'], cine_name, cine_data['city'], cine_data['url'], countries[cine_data['country']][0]))

    url = url_prefix + cine_data['imdb']
    content = get(url).content
    soup = BeautifulSoup(content, 'html.parser')
    for p in soup.find_all('div', class_='list_item'):
        link = p.find_all('a', href=True)[0]
        imdb_id = re.search(r'.*?/(tt\d+)',link['href'])[1]        
        showtimes = p.find_all('div', class_='showtimes')[0]
        showtimes = re.sub(r'\s+', ' ', showtimes.text)
        struct[cine_name][imdb_id] = showtimes

    sleep(1 + random.random())

cursor.execute('SELECT imdb, tmdb FROM Movie')
movies = dict(cursor.fetchall())

cursor.execute('DELETE FROM Showtime')

for theater, data in struct.items():
    for movie_tmdb, dates in data.items():
        if movie_tmdb in movies:
            cursor.execute('''
                INSERT INTO Showtime (movieid, theaterid, "Date")
                VALUES (%s,%s,%s)
            ''', (movies[movie_tmdb], cinemas[theater]['id'], dates))

conn.commit()
conn.close()
