import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

const stats = [
  { name: 'Movies', img: 'popcorn.svg' },
  { name: 'Hours', img: 'pocket-watch.svg' },
  { name: 'Comments', img: 'chat.svg' },
  { name: 'Ratings', img: 'star.svg' },
  { name: 'Friends', img: 'heart.svg' },
]

export default class StatsCard extends Component {
  render() {

    let l = []
    stats.forEach(x => {
      l.push(
        <Col className="user-stats" key={stats.indexOf(x)}>
          <Row>
            <Col xs="6" className="text-right">
              <img src={require('../../img/' + x.img)} alt="Stats" />
            </Col>
            <Col xs="6">
              <p className="number">{this.props.stats[x.name]}</p>
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