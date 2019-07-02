from locust import HttpLocust, TaskSet
import data
from faker import Faker
import random
from time import time
import logging


#logged out only
def frontpage(l):
    begin = time()
    l.client.get('/')
    logging.info(str(begin) + ',frontpage,' + str(time() - begin))

def register(l):
    data =  {
        'username': l.username, 
        'password': l.password, 
        'name': l.username,
        'email': l.username + '@email.com', 
        'country': 'PT', 
        'birthdate': '2019-07-01T15:18:22.358Z', 
        'gender': 'M'
    }
    begin = time()
    r = l.client.post('/register', json=data)
    logging.info(str(begin) + ',register,' + str(time() - begin))


def login(l):
    data = {'username': l.username, 'password': l.password}
    begin = time()
    response = l.client.post('/login', json=data)
    logging.info(str(begin) + ',login,' + str(time() - begin))
    return response.content.decode('utf-8')

def profile(l):
    begin = time()
    l.client.get('/profile', headers=l.authHeader)
    logging.info(str(begin) + ',profile,' + str(time() - begin))

def showtimes(l):
    begin = time()
    l.client.get('/showtimes?theater=' + str(random.choice(data.THEATERS)), name='showtimes')
    logging.info(str(begin) + ',showtimes,' + str(time() - begin))

def news(l):
    begin = time()
    l.client.get('/news')
    logging.info(str(begin) + ',news,' + str(time() - begin))

def movieSearch(l):
    begin = time()
    response = l.client.get('/movie/search?title=' + random.choice(data.MOVIE_NAMES), name='movieSearch')
    logging.info(str(begin) + ',movieSearch,' + str(time() - begin))
    json = response.json()
    if len(json) > 0:
        l.movieIds += [x['id'] for x in json[:90]]

def peopleSearch(l):
    begin = time()
    response = l.client.get('/member/search?name=' + l.faker.first_name(), name='peopleSearch')
    logging.info(str(begin) + ',peopleSearch,' + str(time() - begin))
    json = response.json()
    if len(json) > 0:
        l.memberIds += [x['id'] for x in json[:90]]

def movie(l):
    begin = time()
    l.client.get('/movie/' + str(random.choice(l.movieIds)), name='movie')
    logging.info(str(begin) + ',movie,' + str(time() - begin))

def member(l):
    begin = time()
    l.client.get('/member/' + str(random.choice(l.memberIds)), name='member')
    logging.info(str(begin) + ',member,' + str(time() - begin))

def feed(l):
    begin = time()
    l.client.get('/user/feed', headers=l.authHeader)
    logging.info(str(begin) + ',feed,' + str(time() - begin))



class UserBehavior(TaskSet):
    tasks = {news: 1, profile: 1, showtimes: 1, movie: 1, member: 1, movieSearch: 1, peopleSearch: 1, feed: 1}
    authHeader = None
    movieIds = [120, 155, 161, 680, 862, 954, 19995, 299534]  # temporary data
    memberIds = [31, 287, 1245, 1327, 1461, 3894, 5064, 6193]  # temporary data

    def on_start(self):
        frontpage(self)
        self.faker = Faker()
        self.username = self.faker.user_name() + str(random.randint(1, 9999))
        self.password = '$2a$10$rzUH4ldkL9qQnuSWp/USAumUXBMA6chjBqhkFFBJCgk6DK2wibBNS'
        register(self)
        token = login(self)
        self.authHeader = {"authorization": "Bearer " + token}


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    host='http://localhost:8080'
    min_wait = 0
    max_wait = 0
