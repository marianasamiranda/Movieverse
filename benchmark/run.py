#python run.py <time_in_seconds>

import re
import sys
import subprocess
import os

if not os.path.exists('results'):
    os.mkdir('results')

def run_locust(name, time):
    if name == 'all':
        file = './locustfile.py'
    else:
        file = './locustfile_custom.py'
    subprocess.call(['locust', '-f', file, '--no-web', '-c 6', '-r 6', '--run-time', time + 's', '--csv=results/' + name])

def generate_task(method):
    s = ''
    for m in methods:
        if m == method:
            s += m + ':1,'
        else:
            s += m + ':0,'
    return '{' + s[:-1] + '}'

filename = 'locustfile.py'
new_file = 'locustfile_custom.py'

if len(sys.argv) > 1:
    time = sys.argv[1]
else:
    time = '60'


#all methods
run_locust('all', time)

methods = ['news', 'profile', 'showtimes', 'movie', 'member', 'movieSearch', 'peopleSearch']


for method in methods:
    with open(filename) as file:
        out = open(new_file, 'w')
        for line in file:
            line = re.sub('{news: 1, profile: 1, showtimes: 1, movie: 1, member: 1, movieSearch: 1, peopleSearch: 1}',
                            generate_task(method),
                            line)
            out.write(line)
        out.close()
        run_locust('method_' + method, time)


subprocess.call(['python', 'plot.py', 'results/all_requests.csv'])
subprocess.call(['python', 'plot.py', 'results/method_*_requests.csv'])
