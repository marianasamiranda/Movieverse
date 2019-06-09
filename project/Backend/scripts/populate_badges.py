import db
import json

(conn, cursor) = db.open_sql()

cursor.execute('SELECT name FROM Badge')
badges = set([x[0] for x in cursor.fetchall()])

file = open('data/badges.json')
data = json.load(file)
for b in data:
    if b['name'] not in badges:
        cursor.execute('''
            INSERT INTO Badge (name, description, image)
            VALUES (%s,%s,%s)
        ''', (b['name'], b['description'], b['image']))

conn.commit()