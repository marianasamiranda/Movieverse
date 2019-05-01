from requests import get
from time import sleep
import json
import glob

downloaded_movies = set()

#get already downloaded
with open('data/downloaded_movies.txt', 'r') as file:
    for movie_id in file:
        downloaded_movies.add(movie_id[:-1])

downloaded = open('data/downloaded_movies.txt', 'a')

ids_to_get = []

#get tmdb ids
with open(glob.glob('data/movie_ids_*')[-1], encoding='utf8') as file:
    for line in file:
        j = json.loads(line)
        #min popularity = 0.6
        if (j['popularity'] > 0.6) and (not j['adult']) and (not j['video']) and (str(j['id']) not in downloaded_movies):
            ids_to_get.append(j['id'])


#one json document per line
out = open('data/movies.json', 'a')
for i in ids_to_get:
    while True:
        try:
            r = get(f'https://api.themoviedb.org/3/movie/{i}?api_key=e491cc92624a810db982f00ead69f9dc&append_to_response=credits,videos,images,recommendations').json()
            out.write(json.dumps(r) + '\n')
            downloaded.write(str(i) + '\n')
            print(r['id'])
            break
        except KeyError:
            break
        except Exception:
            pass

out.close()