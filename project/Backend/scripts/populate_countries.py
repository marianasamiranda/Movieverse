import csv
import psycopg2
import db

struct = {}
with open('data/countries.csv') as file:
    f = csv.reader(file)
    header = f.__next__()
    name_idx = header.index('name')
    code_idx = header.index('alpha2')
    for r in f:
        struct[r[name_idx]] = r[code_idx]

conn = psycopg2.connect(dbname=db.db_name, user=db.db_user, password=db.db_pass, host=db.db_host)
cursor = conn.cursor()

for n,c in struct.items():
    cursor.execute('INSERT INTO Country (name, alphacode) VALUES (%s,%s)', (n, c))

conn.commit()
cursor.close()
conn.close()
