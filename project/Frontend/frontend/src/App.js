import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontpage';
import Feed from './components/feed';
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
        <FrontPage />
        <Footer />
      </div>
    );
  }
}


export default App;
