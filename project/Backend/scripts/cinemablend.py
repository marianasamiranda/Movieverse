from requests import get
import xmltodict
import re
import db

url = 'https://www.cinemablend.com/rss_news_movies.xml'
content = get(url).content
data = xmltodict.parse(content)

struct = {}
i = 0
for article in data['rss']['channel']['item']:
    struct[i] = {
        'title': article['title'],
        'description': re.sub(r'<.*?>', '',article['description'] or ''),
        'date': article['pubDate'],
        'image': article['enclosure']['@url'],
        'link': article['link']
    }
    i += 1

for article in data['rss']['channel']['item']:
    struct[i] = {
        'title': article['title'],
        'description': re.sub(r'<.*?>', '',article['description'] or ''),
        'date': article['pubDate'],
        'image': article['enclosure']['@url'],
        'link': article['link']
    }
    i += 1

for article in data['rss']['channel']['item']:
    struct[i] = {
        'title': article['title'],
        'description': re.sub(r'<.*?>', '',article['description'] or ''),
        'date': article['pubDate'],
        'image': article['enclosure']['@url'],
        'link': article['link']
    }
    i += 1


(conn, cursor) = db.open_sql()
cursor.execute('DELETE FROM News')
for id, info in struct.items():
    cursor.execute('''
        INSERT INTO News
        VALUES (%s,%s,%s,%s,%s,%s)
    ''', (id, info['title'], info['description'], info['date'], info['image'], info['link']))

conn.commit()
conn.close()

print('cinemablend')