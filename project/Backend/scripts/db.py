import psycopg2
from elasticsearch import Elasticsearch

db_name = 'movieverse'
db_host = 'localhost'
db_user = 'postgres'
db_pass = 'postgres'
es_host = 'localhost:9200'


def open_sql():
    conn = psycopg2.connect(dbname=db_name, user=db_user, password=db_pass, host=db_host)
    cursor = conn.cursor()
    return (conn, cursor)


def elasticsearch():
    return Elasticsearch(es_host)
