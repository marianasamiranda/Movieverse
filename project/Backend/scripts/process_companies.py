import sys
import json
import os

processed_companies = set()

if os.path.exists('data/processed_companies.txt'):
    with open('data/processed_companies.txt', 'r') as file:
        for id in file:
            processed_companies.add(id[:-1])

processed_companies_file = open('data/processed_companies.txt', 'a')

companies_processed = open('data/companies_processed.json', 'a', encoding='utf8')

with open('data/companies.json') as f:
    current = 0
    for line in f:
        try:
            if current % 1000 == 0:
                print(current)
            current += 1

            company = json.loads(line)
            if company['id'] == 49473 or str(company['id']) not in processed_companies:
                data = {}
                data['id'] = company['id']
                data['name'] = company['name']
                data['description'] = company['description']
                data['headquarters'] = company['headquarters']
                data['country'] = company['origin_country']
                data['image'] = company['logo_path']
                data['parent'] = company['parent_company']['id'] if company['parent_company'] else None
                data['homepage'] = company['homepage']

                processed_companies_file.write(str(data['id']) + '\n')
                companies_processed.write(json.dumps(data, ensure_ascii=False) + '\n')
        
        except Exception:
            pass

processed_companies_file.close()
companies_processed.close()
