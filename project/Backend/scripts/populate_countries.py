import json
import psycopg2
import db

struct = {}
with open('data/countries.json') as file:
    struct = json.loads(file.read())

(conn, cursor) = db.open_sql()

for n,c in struct.items():
    cursor.execute('INSERT INTO Country (alphacode, name) VALUES (%s,%s)', (n, c))

conn.commit()
cursor.close()
conn.close()
