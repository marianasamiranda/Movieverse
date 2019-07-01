from faker import Faker
import db
import random
from datetime import datetime

NUM_USERS = 500

fake = Faker()

(conn, cursor) = db.open_sql()

for i in range(NUM_USERS):
    name = fake.name()
    username = 'test_user_' + str(i)
    email = fake.email()
    password = '$2a$10$rzUH4ldkL9qQnuSWp/USAumUXBMA6chjBqhkFFBJCgk6DK2wibBNS'
    genreid = random.randint(1, 19)
    birthdate = datetime.now()
    joindate = datetime.now()
    gender = random.choice(['F', 'M'])
    countryid = random.randint(1, 219)


    cursor.execute('''
        INSERT INTO Muser (name, email, username, password, genreid, birthdate, joindate, gender, countryid, 
            moviecount, minutescount, commentscount, ratingscount, friendscount, likescount, favouritecount)
        VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,0,0,0,0,0,0,0)
    ''', (name, email, username, password, genreid, birthdate, joindate, gender, countryid))


conn.commit()
