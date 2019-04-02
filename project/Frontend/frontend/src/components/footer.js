import React, { Component } from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class Footer extends Component  {
  render() {
    return (
      <div className="bg-dark-gray">
        <Container>
          <Row>
            <Col lg="6" md="6" sm="12">
              <div className="footer-title">
                Contact Us
                </div>
              <div className="footer-item">
                <i className="fas fa-envelope fa-fw"></i>
                asdasdasd@asdasd.com
                </div>
              <div className="footer-item">
                <i className="fab fa-facebook-square fa-fw"></i>
                movieverse
                </div>
              <div className="footer-item">
                <i className="fab fa-instagram fa-fw"></i>
                @movieverse
                </div>
              <div className="footer-item">
                <i className="fab fa-twitter-square fa-fw"></i>
                @movieverse
                </div>
              <div className="footer-item-small">
                <p>© Movieverse 2019. All rights reserved</p>
              </div>
            </Col>
          </Row>
        </Container>
      </div>
    )
  }
}