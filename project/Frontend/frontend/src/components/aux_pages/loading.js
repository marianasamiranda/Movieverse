import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { PulseLoader } from 'react-spinners'
import { labels } from '../../var'

export default class Loading extends Component {
  render() {
    return (
      <Container className="container-padding-extra">
        <Row>
          <Col className="text-center">
            <div className="title">
              {labels[this.props.lang].loading}
            </div>
            <PulseLoader 
              css={{marginBottom: '30px'}}
              color="#e2463e"
              size={15}
            />
            <img src={require('../../img/logo.png')} 
              width="35%" className="aux-page-img" alt="Logo" />
          </Col>
        </Row>
      </Container>
    )
  }
}