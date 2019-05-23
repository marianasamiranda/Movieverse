import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Flag from '../flag';
import {labels} from '../../var'

export default class InfoCard extends Component {
  render() {
    return (
      <div className="info-card">
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            {labels[this.props.lang].name}
          </Col>
          <Col xs="6" className="text-right">
            {this.props.name}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            {labels[this.props.lang].favouriteGenre}
          </Col>
          <Col xs="6" className={"text-right " + (this.props.changeGenre ? "clickable" : "")} 
              onClick={this.props.changeGenre ? this.props.changeGenre : undefined}>
            {labels[this.props.lang][this.props.genre]}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            {labels[this.props.lang].birthdate}
          </Col>
          <Col xs="6" className="text-right">
            {this.props.birthdate}
          </Col>
        </Row>
        <Row className="item-border">
          <Col xs="6" className="info-item-label">
            {labels[this.props.lang].joined}
          </Col>
          <Col xs="6" className="text-right">
            {this.props.joined}
          </Col>
        </Row>
        <Row>
          <Col xs="6" className="info-item-label">
            {labels[this.props.lang].country}
          </Col>
          <Col xs="6" className="text-right">
            <Flag country={this.props.country} />
          </Col>
        </Row>
      </div>
    )
  }
}