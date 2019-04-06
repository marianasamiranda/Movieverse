import React, { Component } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class SellingPoint extends Component {
  render() {
    return (
      <Row className="selling-point text-center">
        <Col lg="auto" md="auto" sm="2">
          <img src={this.props.img} width={this.props.width ? this.props.width : "70vw"} alt="" />
        </Col>
        <Col >
          {this.props.text}
        </Col>
      </Row>
    )
  }
}