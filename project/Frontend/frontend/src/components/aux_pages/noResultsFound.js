import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { labels } from '../../var';

export default class NoResultsFound extends Component {
  render() {
    return (
      <Container>
        <Row>
          <Col xs="12" className="text-center">
            <div className="title-medium">
              {labels[this.props.lang].noResultsFound}
            </div>
          </Col>
          <Col xs="12" className="text-center">
            <img src={require('../../img/monster_noresultsfound.svg')} 
              width="35%" className="aux-page-img" alt="404" />
          </Col>
        </Row>
      </Container>
    )
  }
}