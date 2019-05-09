import json
import psycopg2
import db

l = []
with open('data/genres.json') as f:
    data = json.loads(f.read())
    for x in data['genres']:
        l.append((x['id'], x['name']))

conn = psycopg2.connect(dbname=db.db_name, user=db.db_user, password=db.db_pass, host=db.db_host)
cursor = conn.cursor()

for (genre_id, genre_name) in l:
    cursor.execute('''
        INSERT INTO GENRE (id, name) 
        VALUES(%s, %s)
        ON CONFLICT(id)
        DO NOTHING
    ''', (genre_id, genre_name))

conn.commit()
cursor.close
conn.close()