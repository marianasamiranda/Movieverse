import subprocess
print('Countries')
subprocess.call(['python', 'populate_countries.py'])
print('Genres')
subprocess.call(['python', 'populate_genres.py'])
print('Companies')
subprocess.call(['python', 'populate_companies.py'])
print('People')
subprocess.call(['python', 'populate_people.py'])
print('Movies')
subprocess.call(['python', 'populate_movies.py'])