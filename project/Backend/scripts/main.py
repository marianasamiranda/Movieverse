import subprocess

print('Downloading latest movies ids file')
subprocess.call(['python', 'get_movies_ids.py'])

print('Downloading movies info')
subprocess.call(['python', 'get_movies.py'])

print('Downloading people info')
subprocess.call(['python', 'get_people.py'])

print('Downloading companies info')
subprocess.call(['python', 'get_companies.py'])

print('Processing movies info')
subprocess.call(['python', 'process_movies.py'])

print('Processing people info')
subprocess.call(['python', 'process_people.py'])

print('Processing companies info')
subprocess.call(['python', 'process_companies.py'])

#TODO populate database

print('Done')