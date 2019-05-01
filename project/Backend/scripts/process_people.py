import sys
import json
import os

processed_people = set()

if os.path.exists('data/processed_people.txt'):
    with open('data/processed_people.txt', 'r') as file:
        for id in file:
            processed_people.add(id[:-1])

processed_people_file = open('data/processed_people.txt', 'a')

people_processed = open('data/people_processed.json', 'a', encoding='utf8')

with open('data/people.json') as f:
    current = 0
    for line in f:
        try:
            if current % 1000 == 0:
                print(current)
            current += 1

            person = json.loads(line)
            if str(person['id']) not in processed_people:
                data = {}
                data['id'] = person['id']
                data['name'] = person['name']
                data['department'] = person['known_for_department']
                data['birthdate'] = person['birthday']
                data['gender'] = 'F' if person['gender'] == 1 else 'M' if person['gender'] == 2 else 'U'
                data['imdb'] = person['imdb_id']
                data['biography'] = person['biography']
                data['birthplace'] = person['place_of_birth']
                data['social_media'] = person['external_ids']
                data['image'] = person['profile_path']
                data['images'] = [x['file_path'] for x in person['images']['profiles']]

                processed_people_file.write(str(data['id']) + '\n')
                people_processed.write(json.dumps(data, ensure_ascii=False) + '\n')
        
        except Exception:
            pass

processed_people_file.close()
people_processed.close()
