import React, { Component } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class StatsItem extends Component {
  render() {
    return (
      <Row>
        <Col md="0">
          <img src={this.props.img} width="70%" alt="" />
        </Col>
        <Col md="0">
          <div className="stats-number">{this.props.number}</div>
          <div className="stats-name">{this.props.name}</div>
        </Col>
      </Row>
    )
  }
}