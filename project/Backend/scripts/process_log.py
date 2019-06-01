from time import sleep
import db
from elasticsearch import helpers
import re
import traceback

log_file = '../logs/app.log' #app log location
delta = 5 #seconds between each log read

file = open(log_file)
docs = []
es = db.elasticsearch()

while True:
    line = file.readline()

    if line:
        try:
            timestamp = str.strip((re.match(r'.*?\ ', line)[0]))
            method = re.search(r'(\w*?)\(', line)[1]
            docs.append({
                '_index': 'movieverse_logs',
                '_source': {
                    'timestamp': timestamp,
                    'method': method
                }
            })
        except:
            pass

    else:
        helpers.bulk(es, docs)
        docs = []
        sleep(delta)
