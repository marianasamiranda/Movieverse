from requests import get
from time import sleep
import json

downloaded_companies = set()

#get already downloaded
with open('data/downloaded_companies.txt', 'r') as file:
    for company_id in file:
        downloaded_companies.add(company_id[:-1])

downloaded = open('data/downloaded_companies.txt', 'a')

ids_to_get = []

#get tmdb ids
with open('data/companies_ids.txt', encoding='utf8') as file:
    for line in file:
        i = line[:-1]
        if i not in downloaded_companies:
            ids_to_get.append(i)


#one json document per line
out = open('data/companies.json', 'a')
for i in ids_to_get:
    while True:
        try:
            r = get(f'https://api.themoviedb.org/3/company/{i}?api_key=e491cc92624a810db982f00ead69f9dc').json()
            out.write(json.dumps(r) + '\n')
            downloaded.write(str(i) + '\n')
            print(r['id'])
            break
        except KeyError:
            break
        except Exception:
            pass

out.close()
