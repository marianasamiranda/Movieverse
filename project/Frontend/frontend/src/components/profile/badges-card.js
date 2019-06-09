import React, { Component } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Badge from './badge'
import { badges } from '../../var'

export default class BadgesCard extends Component {
  render() {

    let l = []
    let i = 0
    badges.forEach(x => {
      l.push( //TODO collected
        <Col xs="3" sm="2" key={i++} className="text-center badge-col badge">
          <Badge name={x.name} img={x.image} collected info={x.description}/>
        </Col>
      )
    })
    
    return (
      <div className="info-card badge-card">
        <Row>
          {l}
        </Row>
      </div>
    )
  }
}