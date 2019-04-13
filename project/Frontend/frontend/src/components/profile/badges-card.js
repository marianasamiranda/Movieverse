import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Badge from './badge'

export default class BadgesCard extends Component {
  render() {

    let l = []
    for (var i of Array(15).keys()) {
      l.push(
        <Col xs="3" sm="2" key={i} className="text-center badge-col badge">
          <Badge name="badge" collected info="Badge info"/>
        </Col>
      )
    }
    
    return (
      <div className="info-card badge-card">
        <Row>
          {l}
        </Row>
      </div>
    )
  }
}