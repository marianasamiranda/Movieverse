import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Jumbotron from 'react-bootstrap/Jumbotron'

//TODO

export default class Banner extends Component {
  render() {
    return (
      <Jumbotron fluid className="jumbotron">
        <Container>
          <h1 className="header-text">ENGAGE WITH YOUR</h1>
          <h1 className="header-text">MOVIE FRIENDS!</h1>
        </Container>
      </Jumbotron>
    )  
  }
}