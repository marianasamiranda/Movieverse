import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Authenticator from './authenticator/authenticator';
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'

//TODO

export default class Banner extends Component {
  render() {
    return (
      <div className="jumbotron jumbotron-fluid">
        <Container className="container-banner banner-bg">
          <Row>
            <Col lg="6" sm="12">
              <h1 className="jumbotron-text">ENGAGE WITH YOUR</h1>
              <h1 className="jumbotron-text">MOVIE FRIENDS!</h1>
            </Col>
            <Col lg="6" sm="12">
              <Authenticator />
            </Col>
          </Row>
          <Row>
          </Row>
        </Container>
      </div>
    )  
  }
}