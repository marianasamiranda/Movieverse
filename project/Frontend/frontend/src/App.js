import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontpage';
import Feed from './components/feed/feed';
import Actor from './components/actor/actor';
import MoviePage from './components/movie/moviepage'
import MediaPage from './components/movie/mediapage'
import MovieSearch from './components/movieSearch'
import PeopleSearch from './components/peopleSearch'
import FindUsers from './components/findUsers'
import Showtimes from './components/showtimes'
import Profile from './components/profile/profile'
import {BrowserRouter as Router, Route, withRouter, Switch} from 'react-router-dom/'
import {getToken, setLanguage, getLanguage} from './cookies'

import './styles/App.css';
import NotFoundError from './components/aux_pages/notFoundError';
import NewsPage from './components/newsPage';
import { labels } from './var';

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      language : getLanguage(),
      logged: getToken() ? true : false
    }
    this.handleSession = this.handleSession.bind(this)
    this.setAvatar = this.setAvatar.bind(this)
    this.changeLanguage = this.changeLanguage.bind(this)
  }

  handleSession(username) {
    this.setState({
      logged: !this.state.logged,
      username: username
    })
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
    let navBarLinks = [
      { name: labels[this.state.language].news, url: '/news', logged: false },
      { name: labels[this.state.language].movies, url: '/movies', logged: false },
      { name: labels[this.state.language].nowPlaying, url: '/showtimes', logged: false },
      { name: labels[this.state.language].people, url: '/people', logged: false },
      // { name: 'Feed', url: '/feed', logged: false },
      // { name: 'Actor', url: '/actor', logged: false },
      { name: labels[this.state.language].users, url: '/users', logged: true },
    ]

    let mainPage
    if (!this.state.logged) {
      mainPage = <Route exact path="/" render={() => 
        <FrontPage handleSession={this.handleSession} lang={this.state.language} />} 
      />
    }
    else { //TODO change to Feed
      mainPage = <Route exact path="/" render={() => 
        <Profile setAvatar={this.setAvatar} lang={this.state.language} /> }
      />
    }

    return (
      <Router>
        <NavBar links={navBarLinks} logged={this.state.logged} handleSession={this.handleSession} 
          avatar={this.state.avatar} changeLanguage={this.changeLanguage} lang={this.state.language}/>
        <main>
            <Switch>
              {mainPage}
              <Route exact path="/news" render={() => <NewsPage lang={this.state.language} />} />
              <Route exact path="/movies" render={(props) => <MovieSearch {...props} lang={this.state.language} />} />
              <Route exact path="/showtimes" render={() => <Showtimes lang={this.state.language} />} />
              <Route exact path="/people" render={() => <PeopleSearch lang={this.state.language} />} />
              <Route exact path="/profile" render={() => <Profile setAvatar={this.setAvatar} lang={this.state.language} />} />
              <Route exact path="/users" render={() => <FindUsers lang={this.state.language} />} />
              <Route exact path="/movie/:id" render={(props) => <MoviePage {...props} lang={this.state.language} />}  />
              {/*<Route exact path="/media/:id" component={MediaPage} />*/}
            <Route exact path="/u/:username" render={(props) => <Profile {...props} lang={this.state.language} />} />
              <Route exact path="/feed" render={() => <Feed user="useruser1"/>} />
              <Route exact path="/member/:id" component={Actor} />
              <Route render={() => <NotFoundError lang={this.state.language} />} />
            </Switch>
        </main>
        <Footer lang={this.state.language}/>
      </Router>
    );
  }
}

export default App;
