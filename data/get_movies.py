from requests import get
from time import sleep
import json

tmdb_ids = []
with open ('movielens_data.csv') as file:
    header = file.__next__()[:-1].split(',')

    for line in file:
        tmdb_ids.append(line[:-1].split(',')[header.index('tmdbId')])

key = 'e491cc92624a810db982f00ead69f9dc'
out = open('data2.json', 'a')
current = 1

for i in tmdb_ids:
    r1 = get(f'https://api.themoviedb.org/3/movie/{i}?api_key={key}').json()
    sleep(1)
    r2 = get(f'https://api.themoviedb.org/3/movie/{i}/credits?api_key={key}').json()
    out.write(f'"{i}": {{"info": {json.dumps(r1)}, "credits" : {json.dumps(r2)}}},\n')
    sleep(1)
    print(current)
    current += 1

out.close()