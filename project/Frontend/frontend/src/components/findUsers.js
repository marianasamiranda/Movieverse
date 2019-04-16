import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Jumbotron from 'react-bootstrap/Jumbotron'
import MovieCard from './movie-card'
import Button from 'react-bootstrap/Button'
import Flag from './flag'
//import Axios from 'axios'
//import {backend} from '../var'

export default class FindPeople extends Component {
  constructor(props) {
    super(props)
    this.state = {
      mightKnowLoading: false,
    }
  }

  componentDidMount() {
    document.title = "Find Users | Movieverse"
  }

  render() {
    return (
      <div>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">Find Users</h1>
            <Row>
              <Col md={{ span: 6, offset: 3 }}>
                <InputGroup className="input-margin">
                  <InputGroup.Prepend>
                    <InputGroup.Text id="inputGroupPrepend" className="bg-light-gray">
                      <i className="fas fa-search" />
                    </InputGroup.Text>
                  </InputGroup.Prepend>
                  <Form.Control
                    className="search-input"
                    type="text"
                    name="title"
                    placeholder="Username/Real Name"
                    onChange={this.handleChange}
                  />
                </InputGroup>
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        <Container className="container-padding">
          <div className="title-medium">
            Users you might know
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (5)</div>} info="Real Name" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (5)</div>} info="Real Name" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (3)</div>} info="Real Name" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (2)</div>} info="Real Name" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (2)</div>} info="Real Name" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad (2)</div>} info="Real Name" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.mightKnowLoading}>
            {!this.state.mightKnowLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            Most upvoted
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title={<div><Flag country="pt"/>asdsad</div>} info="x likes" />
            </Col>
          </Row>
        </Container>
      </div>
    )
  }
}