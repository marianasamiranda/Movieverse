import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Jumbotron from 'react-bootstrap/Jumbotron'
import MovieCard from './movie-card'
import Button from 'react-bootstrap/Button'
//import Axios from 'axios'
//import {backend} from '../var'

export default class PeopleSearch extends Component {
  constructor(props) {
    super(props)
    this.state = {
      bornTodayLoading: false,
      topLoading: false
    }
  }

  componentDidMount() {
    document.title = "People Search | Movieverse"
  }

  render() {
    return (
      <div>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">Search Cast and Crew Members</h1>
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
                    placeholder="Name"
                    onChange={this.handleChange}
                  />
                </InputGroup>
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        <Container className="container-padding">
          <div className="title-medium">
            Born today
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.bornTodayLoading}>
            {!this.state.bornTodayLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            People with most credits
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Name" info="xx years" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.topLoading}>
            {!this.state.topLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
      </div>
    )
  }
}