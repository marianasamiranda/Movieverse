from requests import get
import datetime
import gzip
import io

date = datetime.datetime.now()
filename = f'movie_ids_{date.month:02d}_{date.day:02d}_{date.year}.json'
url = f'http://files.tmdb.org/p/exports/{filename}.gz'

file_gz = io.BytesIO(get(url).content)
file_uncompressed = gzip.open(file_gz, 'rt', encoding='utf8')

with open('data/' + filename, 'w', encoding='utf8') as out:
    out.write(str(file_uncompressed.read()))
