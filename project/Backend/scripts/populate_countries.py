import json
import psycopg2
import db

struct = {}
with open('data/countries.json') as file:
    struct = json.loads(file.read())

conn = psycopg2.connect(dbname=db.db_name, user=db.db_user, password=db.db_pass, host=db.db_host)
cursor = conn.cursor()

for n,c in struct.items():
    cursor.execute('INSERT INTO Country (alphacode, name) VALUES (%s,%s)', (n, c))

conn.commit()
cursor.close()
conn.close()
