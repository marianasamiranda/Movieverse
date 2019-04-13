import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Flag from '../flag';
import {genres} from '../../var'

export default class InfoCard extends Component {
  render() {
    return (
      <div className="info-card">
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
              Name
          </Col>
          <Col xs="6" className="text-right">
            {this.props.name}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            Favourite Genre
          </Col>
          <Col xs="6" className="text-right clickable" onClick={this.props.changeGenre}>
            {genres[this.props.genre]['label']}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            Birthdate
          </Col>
          <Col xs="6" className="text-right">
            {this.props.birthdate}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            Joined
          </Col>
          <Col xs="6" className="text-right">
            {this.props.joined}
          </Col>
        </Row>
        <Row>
          <Col xs="6" className="info-item-label">
            Country
          </Col>
          <Col xs="6" className="text-right">
            <Flag country={this.props.country} />
          </Col>
        </Row>
      </div>
    )
  }
}