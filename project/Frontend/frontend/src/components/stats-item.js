import React, { Component } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container';

export default class StatsItem extends Component {
  render() {
    return (
      <Container>
        <Row>
          <Col xs="6">
            <img src={this.props.img} width="100%" style={{ maxWidth: 80, minWidth: 60}} alt="" className="center-image"/>
          </Col>
          <Col xs="6">
            <div className="stats-number">{this.props.number}</div>
            <div className="stats-name">{this.props.name}</div>
          </Col>
        </Row>
      </Container>
    )
  }
}