#python run.py <time_in_seconds>

import re
import sys
import subprocess
import os
import shutil



if os.path.exists('results'):
	shutil.rmtree('results')

os.mkdir('results')


def run_locust(name, time):
    if name == 'all':
        file = './locustfile.py'
    else:
        file = './locustfile_custom.py'
    subprocess.call(['locust', '-f', file, '--no-web', '--logfile=results/' + name + '.log', '-c 100', '-r 25', '--run-time', time + 's', '--csv=results/' + name])

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

methods = ['news', 'profile', 'showtimes', 'movie', 'member', 'movieSearch', 'peopleSearch', 'feed']


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
subprocess.call(['python', 'plot_log.py', 'results/method_*.log'])
