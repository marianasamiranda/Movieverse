import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class StatsCard extends Component {
  render() {

    let l = []
    this.props.stats.forEach(x => {
      l.push(
        <Col className="user-stats" key={this.props.stats.indexOf(x)}>
          <Row>
            <Col xs="6" className="text-right">
              <img src={require('../../img/' + x.img)} alt="Stats" />
            </Col>
            <Col xs="6">
              <p className="number">{x.total}</p>
            </Col>
          </Row>
          <p className="label">{x.name}</p>
        </Col>
      )
    });

    return (
      <div className="info-card">
        <Row>
          {l}
        </Row>
      </div>
    )
  }
}