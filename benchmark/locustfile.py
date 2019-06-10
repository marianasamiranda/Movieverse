from locust import HttpLocust, TaskSet
import data
from faker import Faker
import random

#logged out only
def frontpage(l):
    l.client.get('/')

def login(l):
    data = {'username': l.username, 'password': l.password}
    response = l.client.post('/login', json=data)
    return response.content.decode('utf-8')

def profile(l):
    l.client.get('/profile', headers=l.authHeader)

def showtimes(l):
    l.client.get('/showtimes?theater=' + str(random.choice(data.THEATERS)), name='showtimes')

def news(l):
    l.client.get('/news')

def movieSearch(l):
    response = l.client.get('/movie-search?title=' + random.choice(data.MOVIE_NAMES), name='movieSearch')
    json = response.json()
    if len(json) > 0:
        l.movieIds = [x['id'] for x in response.json()[:30]]
    else:
        l.movieIds = [120, 155, 161, 680, 862, 954, 19995, 299534]

def peopleSearch(l):
    response = l.client.get('/people-search?name=' + l.faker.first_name(), name='peopleSearch')
    json = response.json()
    if len(json) > 0:
        l.memberIds = [x['id'] for x in json[:30]]
    else:
        l.memberIds = [31, 287, 1245, 1327, 1461, 3894, 5064, 6193]

def movie(l):
    l.client.get('/movie/' + str(random.choice(l.movieIds)), name='movie')

def member(l):
    l.client.get('/member/' + str(random.choice(l.memberIds)), name='member')

#TODO remaining methods


class UserBehavior(TaskSet):
    tasks = {news: 1, profile: 1, showtimes: 1, movie: 1, member: 1, movieSearch: 1, peopleSearch: 1}
    authHeader = None
    username = None
    password = None
    movieIds = [120, 155, 161, 680, 862, 954, 19995, 299534]  # temporary data
    memberIds = [31, 287, 1245, 1327, 1461, 3894, 5064, 6193]  # temporary data
    faker = Faker()

    def on_start(self):
        frontpage(self)
        (self.username, self.password) = data.CREDENTIALS.pop()
        token = login(self)
        self.authHeader = {"authorization": "Bearer " + token}


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host='http://localhost:8080'
    min_wait = 0
    max_wait = 0
