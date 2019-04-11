import React, { Component } from 'react';
import NavBar from './components/navbar'
import Footer from './components/footer'
import FrontPage from './components/frontpage';
import Feed from './components/feed';
import MoviePage from './components/moviepage'
import Axios from 'axios'

import './styles/App.css';
const backend = 'http://localhost:8080'

class App extends Component {
  constructor(props) {
    super(props)
    this.login = this.login.bind(this)
    this.register = this.register.bind(this)
    this.state = {
      loginFail: false,
      registerFail: false,
      errorMessage: ""
    }
  }


  login(data) {
    Axios.post(backend + '/login', data).then(x =>{
      console.log("success");
      this.setState({
        loginFail: false,
        registerFail: false
      })
    })
    .catch(x => {
      this.setState({
        loginFail: true,
        registerFail: false,
        errorMessage: x.response.data
      })
    })
  }


  register(data) {
    this.setState({
      registeredFail: false,
    })
    Axios.post(backend + '/register', data).then(x => {
      if (x.status === 200) {
        console.log("success");
        this.setState({
          loginFail: false,
          registerFail: false
        })
      }
    })
    .catch(x => {
      this.setState({
        loginFail: false,
        registerFail: true,
        errorMessage: x.response.data
      })
    })
  }


  render() {
    let navBarLinks = [
      { name: 'Discover Movies', url: '/movie-search', selected: false },
      { name: 'Find Actors', url: '/actor-search', selected: false }
    
    ]

    return (
      <div>
        <NavBar links={navBarLinks}/>
        {/*<FrontPage 
          login={this.login}
          register={this.register}
          loginFail={this.state.loginFail}
          registerFail={this.state.registerFail}
          errorMessage={this.state.errorMessage}
        />*/}
        {/*<Feed>

        </Feed>*/}

        <MoviePage />
        <Footer />
      </div>
    );
  }
}


export default App;
