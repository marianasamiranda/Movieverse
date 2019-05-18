import React, { Component } from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Image from 'react-bootstrap/Image'
import rocket from '../img/rocket.png'
import { labels } from '../var';

export default class Footer extends Component  {
  render() {
    return (
      <footer className="bg-dark-gray">
        <Container className="footer">  
          <Row>
            <Col md="2" className="d-none d-lg-block d-md-block">
              <Image src={rocket} className="rocket" />
            </Col>
            <Col md="10" sm="12">
              <div className="footer-title">
                {labels[this.props.lang].contactUs}
              </div>
              <div className="footer-item">
                <i className="fas fa-envelope fa-fw margin-right-10"></i>
                movieverse@movieverse.com
              </div>
              <div className="footer-item">
                <i className="fab fa-facebook-square fa-fw margin-right-10"></i>
                movieverse
              </div>
              <div className="footer-item">
                <i className="fab fa-instagram fa-fw margin-right-10"></i>
                @movieverse
              </div>
              <div className="footer-item">
                <i className="fab fa-twitter-square fa-fw margin-right-10"></i>
                @movieverse
              </div>
              <div className="footer-item-small">
                <p>{labels[this.props.lang].rightsReserved}</p>
              </div>
            </Col>
          </Row>
        </Container>
      </footer>
    )
  }
}