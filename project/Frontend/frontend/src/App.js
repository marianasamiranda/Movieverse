import React, { Component } from 'react'
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontpage'
import Feed from './components/feed/feed'
import Member from './components/member/member'
import Company from './components/company/company'
import MoviePage from './components/movie/moviepage'
import MediaPage from './components/movie/mediapage'
import CrewPage from './components/movie/crewpage'
import MovieSearch from './components/movieSearch'
import PeopleSearch from './components/peopleSearch'
import FindUsers from './components/findUsers'
import Showtimes from './components/showtimes'
import Profile from './components/profile/profile'
import Admin from './components/admin'
import Tour from './components/tour'
import {BrowserRouter as Router, Route, withRouter, Switch} from 'react-router-dom/'
import {getToken, setLanguage, getLanguage, setUsername, getUsername} from './cookies'

import './styles/App.css';
import NotFoundError from './components/aux_pages/notFoundError';
import NewsPage from './components/newsPage';
import { labels } from './var';

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      language : getLanguage(),
      logged: getToken() && getUsername() !== 'admin',
      admin: getToken() && getUsername() === 'admin'
    }
    this.handleSession = this.handleSession.bind(this)
    this.setAvatar = this.setAvatar.bind(this)
    this.changeLanguage = this.changeLanguage.bind(this)
  }

  handleSession(username) {
    if ((!username && !this.state.admin) || (username && username !== 'admin')) {
      setUsername(username)
      this.setState({
        logged: !this.state.logged,
        username: username,
        admin: false
      })
    }
    else {
        setUsername('admin')
        this.setState({
          admin: !this.state.admin,
          username: 'admin',
          logged: false
      })
    }
  }

  setAvatar(img) {
    this.setState({
      avatar: img
    })
  }

  changeLanguage(l) {
    setLanguage(l)
    this.setState({
      language: l
    })
  }

  
  render() {
    let navBarLinks
    let mainPage
    
    if (this.state.admin) {
      navBarLinks = [ 
        {name: labels[this.state.language].logout, logout: true, url: ''} 
      ]
      mainPage = <Route exact path="/" render={() =>
        <Admin lang={this.state.language} />}
      />
    }

    else {
      navBarLinks = [
        { name: labels[this.state.language].news, url: '/news', logged: false },
        { name: labels[this.state.language].movies, url: '/movies', logged: false },
        { name: labels[this.state.language].nowPlaying, url: '/showtimes', logged: false },
        { name: labels[this.state.language].people, url: '/people', logged: false },
        // { name: 'Feed', url: '/feed', logged: false },
        // { name: 'Actor', url: '/actor', logged: false },
        { name: labels[this.state.language].users, url: '/users', logged: true },
        { name: labels[this.state.language].tour, url: '/tour', logged: false },
      ]
      if (!this.state.logged) {
        mainPage = <Route exact path="/" render={() => 
          <FrontPage handleSession={this.handleSession} lang={this.state.language} />} 
        />
      }
      else { //TODO change to Feed
        mainPage = <Route exact path="/" 
                render={() => <Feed lang={this.state.language}/>} />
      }
    }

    return (
      <Router>
        <NavBar links={navBarLinks} logged={this.state.logged} handleSession={this.handleSession} 
          avatar={this.state.avatar} changeLanguage={this.changeLanguage} lang={this.state.language}/>
        <main>
            <Switch>
              {mainPage}
              <Route exact path="/news" 
                render={() => <NewsPage lang={this.state.language} />} />

              <Route exact path="/movies" 
                render={(props) => <MovieSearch {...props} lang={this.state.language} />} />

              <Route exact path="/showtimes" 
                render={(props) => <Showtimes {...props} lang={this.state.language} />} />

              <Route exact path="/people" 
                render={() => <PeopleSearch lang={this.state.language} />} />

              <Route exact path="/profile" 
                render={() => <Profile setAvatar={this.setAvatar} lang={this.state.language} />} />

              <Route exact path="/users" 
                render={() => <FindUsers lang={this.state.language} />} />

              <Route exact path="/movie/:id" 
                render={(props) => <MoviePage {...props} lang={this.state.language} />}  />

              <Route exact path="/movie/:id/members" 
                render={(props) => <CrewPage {...props} lang={this.state.language} />}  />
                
              <Route exact path="/movie/:id/media"
                render={(props) => <MediaPage {...props} lang={this.state.language} />}  />

              <Route exact path="/u/:username" 
                render={(props) => <Profile {...props} lang={this.state.language} />} />

              <Route exact path="/feed" 
                render={() => <Feed user="useruser1" lang={this.state.language}/>} />

              <Route exact path="/member/:id"
                render={(props) => <Member {...props} lang={this.state.language} />} />

              <Route exact path="/company/:id"
                render={(props) => <Company {...props} lang={this.state.language} />} />

              <Route exact path="/tour"
                render={(props) => <Tour {...props} lang={this.state.language} />} />

              <Route render={() => <NotFoundError lang={this.state.language} />} />
              
            </Switch>
        </main>
        <Footer lang={this.state.language}/>
      </Router>
    );
  }
}

export default App;
