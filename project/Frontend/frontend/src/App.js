import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontPage';
import Feed from './components/feed';
import MoviePage from './components/moviepage'
import MovieSearch from './components/movieSearch'
import PeopleSearch from './components/peopleSearch'
import FindUsers from './components/findUsers'
import Axios from 'axios'

import './styles/App.css';

class App extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    let navBarLinks = [
      { name: 'Discover Movies', url: '/movie-search', selected: false },
      { name: 'Find Actors', url: '/actor-search', selected: false }
    
    ]

    return (
      <div>
        <NavBar links={navBarLinks}/>
        <MovieSearch/>
        <Footer/>
      </div>
    );
  }
}


export default App;
