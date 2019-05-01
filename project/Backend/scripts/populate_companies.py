import json
import psycopg2
import db

conn = psycopg2.connect(dbname=db.db_name, user=db.db_user, password=db.db_pass, host=db.db_host)
cursor = conn.cursor()

file = open('data/companies_processed.json', encoding='utf8')

cursor.execute('SELECT alphacode, id FROM Country')
countries = dict(cursor.fetchall())


def populate_sql(company):
    try:
        company['countryid'] = countries[company['country']]
    except Exception:
        company['countryid'] = None
    cursor.execute('''
        INSERT INTO Company (id, name, headquarters, homepage, description, image, countryid)
        VALUES (%(id)s,%(name)s,%(headquarters)s,%(homepage)s,%(description)s,%(image)s,%(countryid)s)
        ON CONFLICT (id)
        DO NOTHING
    ''', company)


current = 0
for line in file:
    if current % 1000 == 0:
        print(current)
    current += 1
    company = json.loads(line, encoding='utf8')

    populate_sql(company)

file.close()
conn.commit()
