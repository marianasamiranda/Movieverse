from requests import get
from bs4 import BeautifulSoup
import re
from collections import defaultdict
from time import sleep
import random
import json

url_prefix = 'https://www.imdb.com/showtimes/cinema/PT/'

with open('data/theaters.json') as f:
    cinemas = json.loads(f.read())

struct = defaultdict(dict)

for cine_name, cine_data in cinemas.items():
    url = url_prefix + cine_data['imdb']
    content = get(url).content
    soup = BeautifulSoup(content, 'html.parser')
    for p in soup.find_all('div', class_='list_item'):
        link = p.find_all('a', href=True)[0]
        imdb_id = re.search(r'.*?/(tt\d+)',link['href'])[1]
        
        showtimes = p.find_all('div', class_='showtimes')[0]
        showtimes = re.sub(r'\s+', ' ', showtimes.text)
        
        struct[cine_name][imdb_id] = showtimes
    sleep(random.randint(1, 2) + random.random())

print(struct)
