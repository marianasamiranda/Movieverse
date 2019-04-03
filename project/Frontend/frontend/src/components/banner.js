import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Jumbotron from 'react-bootstrap/Jumbotron'
import Authenticator from './authenticator/authenticator';
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'

//TODO

export default class Banner extends Component {
  render() {
    return (
      <Jumbotron fluid className="jumbotron">
        <Container>
          <Row>
            <Col lg="6" sm="12">
              <h1 className="header-text">ENGAGE WITH YOUR</h1>
              <h1 className="header-text">MOVIE FRIENDS!</h1>
            </Col>
            <Col lg="6" sm="12">
              <Authenticator />
            </Col>
          </Row>
        </Container>
      </Jumbotron>
    )  
  }
}