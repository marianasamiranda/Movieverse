import json
import psycopg2
import db

l = []
with open('data/genres.json') as f:
    data = json.loads(f.read())
    for x in data['genres']:
        l.append(x['name'])

conn = psycopg2.connect(dbname=db.db_name, user=db.db_user, password=db.db_pass, host=db.db_host)
cursor = conn.cursor()

id = 1

for genre in l:
    cursor.execute('INSERT INTO GENRE (id,name) VALUES(%s,%s)', (id, genre))
    id +=1

conn.commit()
cursor.close
conn.close()