import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import MovieCard from './movie-card'
import Jumbotron from 'react-bootstrap/Jumbotron'
//import Axios from 'axios'
//import {backend} from '../var'
import Select from 'react-select'
import {genres, selectStyles} from '../var'

const studios = [
  { value: '21 Century Fox', label: '21 Century Fox' },
  { value: 'A24', label: 'A24'},
  { value: 'ABC', label: 'ABC'},
]

const sort = [
  {value: 'dateAsc', label: 'Date (Ascending)'},
  {value: 'dateDest', label: 'Date (Descending)'},
  {value: 'rating', label: 'Rating'},
]

const genres_ = Object.keys(genres).map(x => genres[x])


export default class MovieSearch extends Component {
  constructor(props) {
    super(props)
    this.state = ({
      recentLoading: false,
      popularLoading: false,
      upcomingLoading: false
    })
  }

  componentDidMount() {
    document.title = "Movie Search | Movieverse"
  }

  handleChange(e) {

  }

  render() {
    return (
      <div>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">Movie Search</h1>
            <Row>
              <Col md={{span: 6, offset: 3}}>
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
                    placeholder="Movie Title" 
                    onChange={this.handleChange} 
                  />
                </InputGroup>
              </Col>
            </Row>
            <Row>
              <Col md={{ span: 3, offset: 3 }} sm="6" xs="12">
                <Select
                  placeholder="Sort By"
                  isSearchable
                  isClearable
                  options={sort}
                  styles={selectStyles}
                  onChange={this.handleChange}
                  name="sort"
                />
              </Col>
              <Col md="3" sm="6" xs="12">
                <Select
                  placeholder="Studio"
                  isSearchable
                  isClearable
                  options={studios}
                  styles={selectStyles}
                  onChange={this.handleChange}
                  name="studio"
                />
              </Col>
            </Row>
            <Row>
              <Col md={{span: 6, offset: 3}}>
                <Select
                  placeholder="Genres"
                  isSearchable
                  isClearable
                  isMulti
                  options={genres_}
                  styles={selectStyles}
                  //onChange={this.handleChange}
                  name="genres"
                />
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        <Container className="container-padding">
          <div className="title-medium">
            New Releases
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.recentLoading}>
            {!this.state.recentLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            Popular
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.popularLoading}>
            {!this.state.popularLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            Upcoming
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="* * * * *" />
            </Col>
          </Row>
          <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.upcomingLoading}>
            {!this.state.upcomingLoading ? "Show more" : "Loading ..."}
          </Button>
        </Container>
      </div> 
    )
  }
}