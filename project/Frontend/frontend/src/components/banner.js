import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Authenticator from './authenticator/authenticator';
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import Jumbotron from 'react-bootstrap/Jumbotron'
import {labels} from '../var'


export default class Banner extends Component {
  render() {
    return (
      <Jumbotron fluid>
        <Container className="container-banner banner-bg">
          <Row>
            <Col lg="6" sm="12">
              <h1 className="jumbotron-text">{labels[this.props.lang].bannerTop}</h1>
              <h1 className="jumbotron-text">{labels[this.props.lang].bannerBottom}</h1>
            </Col>
            <Col lg="6" sm="12">
              <Authenticator handleSession={this.props.handleSession} lang={this.props.lang}/>
            </Col>
          </Row>
          <Row>
          </Row>
        </Container>
      </Jumbotron>
    )  
  }
}