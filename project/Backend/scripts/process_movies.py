import sys
import json
import os

companies_ids = set()
people_ids = set()
processed_movies = set()

if os.path.exists('data/processed_movies.txt'):
    with open('data/processed_movies.txt', 'r') as file:
        for movie_id in file:
            processed_movies.add(movie_id[:-1])

processed_movies_file = open('data/processed_movies.txt', 'a')

movies_processed = open('data/movies_processed.json', 'a', encoding='utf8')

with open('data/movies.json') as f:
    current = 0
    for line in f:
        try:
            if current % 1000 == 0:
                print(current)
            current += 1

            movie = json.loads(line)
            if str(movie['id']) not in processed_movies:
                data = {}
                data['id'] = movie['id']
                data['name'] = movie['title']
                data['imdb'] = movie['imdb_id']
                data['backdrop'] = movie['backdrop_path']
                data['budget'] = movie['budget']
                data['language'] = movie['original_language']
                data['release'] = movie['release_date']
                data['runtime'] = movie['runtime']
                data['plot'] = movie['overview']
                data['poster'] = movie['poster_path']
                data['genres'] = [x['id'] for x in movie['genres']]
                data['companies'] = [x['id'] for x in movie['production_companies']]
                data['tagline'] = movie['tagline']
                data['cast'] = [{'id': x['id'], 'role': x['character'], 'actor': True} for x in movie['credits']['cast']]
                data['crew'] = [{'id': x['id'], 'role': x['job'], 'actor': False} for x in movie['credits']['crew']]
                data['videos'] = [{'name': x['name'], 'type': x['type'], 'path': x['key']} for x in movie['videos']['results']]
                data['backdrops'] = [x['file_path'] for x in movie['images']['backdrops']]
                data['posters'] = [x['file_path'] for x in movie['images']['posters']]
                data['recommended'] = [x['id'] for x in movie['recommendations']['results']]

                for i in data['companies']:
                    companies_ids.add(i)

                for x in data['cast']:
                    people_ids.add(x['id'])

                for x in data['crew']:
                    people_ids.add(x['id'])

                processed_movies_file.write(str(data['id']) + '\n')
                movies_processed.write(json.dumps(data, ensure_ascii=False) + '\n')
        except Exception:
            pass

processed_movies_file.close()
movies_processed.close()

with open('data/companies_ids.txt', 'w') as f:
    f.write('\n'.join(str(x) for x in companies_ids))

with open('data/people_ids.txt', 'w') as f:
    f.write('\n'.join(str(x) for x in people_ids))
