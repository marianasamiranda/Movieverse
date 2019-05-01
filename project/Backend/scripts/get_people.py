from requests import get
from time import sleep
import json

downloaded_people = set()

#get already downloaded
with open('data/downloaded_people.txt', 'r') as file:
    for people_id in file:
        downloaded_people.add(people_id[:-1])

downloaded = open('data/downloaded_people.txt', 'a')

ids_to_get = []

#get tmdb ids
with open('data/people_ids.txt', encoding='utf8') as file:
    for line in file:
        i = line[:-1]
        if i not in downloaded_people:
            ids_to_get.append(i)


#one json document per line
out = open('data/people.json', 'a')
for i in ids_to_get:
    while True:
        try:
            r = get(f'https://api.themoviedb.org/3/person/{i}?api_key=e491cc92624a810db982f00ead69f9dc&append_to_response=images,external_ids').json()
            out.write(json.dumps(r) + '\n')
            downloaded.write(str(i) + '\n')
            print(r['id'])
            #sleep(0.2)
            break
        except KeyError:
            break
        except Exception:
            pass

out.close()
