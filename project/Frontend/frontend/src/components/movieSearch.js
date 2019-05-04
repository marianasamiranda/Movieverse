import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import MovieCard from './movie-card'
import Jumbotron from 'react-bootstrap/Jumbotron'
import Select from 'react-select'
import {genres, selectStyles, backend} from '../var'
import Axios from 'axios'

const sort = [
  {value: 'dateAsc', label: 'Date (Ascending)'},
  {value: 'dateDesc', label: 'Date (Descending)'},
  {value: 'rating', label: 'Rating'},
]

const genres_ = Object.keys(genres).map(x => genres[x])


export default class MovieSearch extends Component {
  constructor(props) {
    super(props)
    this.state = ({
      recentLoading: false,
      popularLoading: false,
      upcomingLoading: false,
      title: undefined,
      sort: undefined,
      genre: undefined,
      titleTimeout: 0,
      results: undefined
    })

    this.handleTitle = this.handleTitle.bind(this)
    this.handleSort = this.handleSort.bind(this)
    this.handleGenre = this.handleGenre.bind(this)
    this.search = this.search.bind(this)
  }

  componentDidMount() {
    document.title = "Movie Search | Movieverse"
  }

  handleTitle(e) {
    if (this.state.titleTimeout) {
      clearTimeout(this.state.titleTimeout)
    }

    this.setState({
      title: e.target.value,
      titleTimeout: setTimeout(x => this.search(), 200)
    })
  }

  handleSort(o) {    
    clearTimeout(this.state.titleTimeout)
    this.setState({
      sort: o ? o.value : undefined
    }, () => this.search())
  }

  handleGenre(o) {
    clearTimeout(this.state.titleTimeout)
    let g = ''
    o.forEach(x => g += x.value + ',')
    this.setState({
      genre: g,
    }, () => this.search())
  }

  search() {
    let query = '?title=' + (this.state.title ? this.state.title : '')
    query += (this.state.genre ? '&genre=' + this.state.genre : '')
    query += (this.state.sort ? '&sort=' + this.state.sort : '')    

    Axios.get(backend + '/movie-search' + query).then(x => {
      this.setState({
        results: x.data
      })      
    })
  }

  render() {

    let to_render

    if (this.state.results) {
      let results = [], i = 0
      Object.entries(this.state.results).forEach(x => {
        results.push(
          <Col lg="2" md="3" xs="4" key={i++}>
            <MovieCard small 
              img={'http://image.tmdb.org/t/p/w200/' + x[1].poster}
              title={x[1].name}
              info={x[1].release === '9999-01-01' ? 'TBA' : x[1].release + ' (' + x[1].rating + ')'} 
              id={x[1].id}
              />
          </Col>
        )
      })
      to_render =
        <Container className="container-padding">
          <Row>
            {results}
          </Row>
        </Container>
    }

    else {
      to_render = 
        <>
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
        </>
    }

    return (
      <>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">Movie Search</h1>
            <Row>
              <Col md={{span: 6, offset: 3}} xs="12">
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
                    onChange={this.handleTitle} 
                  />
                </InputGroup>
              </Col>
            </Row>
            <Row>
              <Col md={{ span: 6, offset: 3 }} xs="12">
                <Select
                  placeholder="Genres"
                  isSearchable
                  isClearable
                  isMulti
                  options={genres_}
                  styles={selectStyles}
                  onChange={this.handleGenre}
                  name="genres"
                />
              </Col>
            </Row>
            <Row>
              <Col md={{ span: 6, offset: 3 }} xs="12">
                <Select
                  placeholder="Sort By"
                  isSearchable
                  isClearable
                  options={sort}
                  styles={selectStyles}
                  onChange={this.handleSort}
                  name="sort"
                />
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        {to_render}
      </> 
    )
  }
}