import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { Link } from 'react-router-dom'

export default class NotFoundError extends Component {
  render() {
    return (
      <Container className="container-padding-extra">
        <Row>
          <Col xs="12" className="text-center">
            <div className="title-medium">
              404 - Page not found
            </div>
          </Col>
          <Col xs="12" className="text-center">
            <img src={require('../../img/monster_404.svg')} 
              width="35%" className="aux-page-img" alt="404" />
          </Col>
          <Col xs="12" className="text-center margin-top-10">
            <Link to="/" className="link">
              To the main page!
            </Link>
          </Col>
        </Row>
      </Container>
    )
  }
}