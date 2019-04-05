import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontpage';
import Axios from 'axios'

import './App.css';

class App extends Component {
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


Axios.get('http://localhost:8080/hello?name=seesrserrse').then(x => console.log(x)).catch(x => console.log(x))



export default App;
