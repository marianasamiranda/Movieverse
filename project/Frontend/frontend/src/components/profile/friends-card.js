import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'

export default class FriendsCard extends Component {
  
  constructor(props) {
    super(props)
    this.state = {
      loading: false
    }
  }

  render() {

    let l = []
    this.props.friends.forEach(x =>
      l.push(
        <Col xs="12" sm="6" md="4" lg="12" key={this.props.friends.indexOf(x)} className="friend margin-bottom-10">
          <Row>
            <Col xs="3" className="text-right">
              <img src={x.img} alt=""/>
            </Col>
            <Col xs="9">
              <p>{x.username}</p>
            </Col>
         </Row>
        </Col>
      )
    )

    return (
      <div className="info-card">
        <Row>
          <Col className="card-title selected">
            Friends
          </Col>
        </Row>
        <Row className="box">
          {l}
        </Row>
        <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.loading}>
          {!this.state.loading ? "Show more" : "Loading ..."}
        </Button>
      </div>
    )
  }
}