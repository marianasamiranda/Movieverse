import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontPage'
import Feed from './components/feed'
import MoviePage from './components/movie/moviepage'
import MovieSearch from './components/movieSearch'
import PeopleSearch from './components/peopleSearch'
import FindUsers from './components/findUsers'
import Showtimes from './components/showtimes'
import Axios from 'axios'
import Profile from './components/profile/profile'
import {BrowserRouter as Router, Route} from 'react-router-dom/'

import './styles/App.css';

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      logged : true
    }
    this.handleSession = this.handleSession.bind(this)
  }

  handleSession() {
    this.setState({
      logged: !this.state.logged
    })
  }

  render() {
    let navBarLinks = [
      { name: 'Discover Movies', url: '/movies', logged: false },
      { name: 'Now Playing', url: '/showtimes', logged: false },
      { name: 'Search People', url: '/people', logged: false },
      { name: 'Find Users', url: '/users', logged: true },
    ]

    return (
      <Router>
        <NavBar links={navBarLinks} logged={this.state.logged} handleSession={this.handleSession}/>
        <main>
          <Route exact path="/" render={() => <FrontPage handleSession={this.handleSession} />} />
          <Route exact path="/movies" component={MovieSearch} />
          <Route exact path="/showtimes" component={Showtimes} />
          <Route exact path="/people" component={PeopleSearch} />
          <Route exact path="/profile" component={Profile} />
          <Route exact path="/users" component={FindUsers} />
        </main>
        <Footer/>
      </Router>
    );
  }
}


export default App;
