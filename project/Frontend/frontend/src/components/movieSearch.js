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
import queryString from 'query-string'

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
      title: undefined,
      sort: undefined,
      genres: undefined,
      titleTimeout: 0,
      results: undefined,
      latest: [],
      popular: [],
      upcoming: [],
      latestCurrent: 1,
      popularCurrent: 1,
      upcomingCurrent: 1
    })

    this.handleTitle = this.handleTitle.bind(this)
    this.handleSort = this.handleSort.bind(this)
    this.handleGenre = this.handleGenre.bind(this)
    this.search = this.search.bind(this)
    this.handleShowMore = this.handleShowMore.bind(this)
  }

  componentDidMount() {
    document.title = "Movie Search | Movieverse"
    this.getInfo()
    const q = queryString.parse(this.props.location.search)
    if (q.genre) {
      this.handleGenre([{value: q.genre}])
    }
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
    let g = []
    o.forEach(x => g.push(x.value))
    
    this.setState({
      genres: g,
    }, () => this.search())
  }

  handleShowMore(type) {
    if (type === 'latest')
      this.setState({ latestCurrent : this.state.latestCurrent + 1})

    if (type === 'popular')
      this.setState({ popularCurrent: this.state.popularCurrent + 1 })

    if (type === 'upcoming')
      this.setState({ upcomingCurrent: this.state.upcomingCurrent + 1 })
  }

  getInfo() {
    Axios.get(backend + '/movie-search-page').then(x => {
      this.setState({
        latest: x.data.latest,
        popular: x.data.popular,
        upcoming: x.data.upcoming
      })
    })
  }

  search() {
    let query = '?title=' + (this.state.title ? this.state.title : '')
    query += (this.state.genres ? '&genre=' + this.state.genres.join() : '')
    query += (this.state.sort ? '&sort=' + this.state.sort : '')    

    Axios.get(backend + '/movie-search' + query).then(x => {
      this.setState({
        results: x.data
      })      
    })
    
  }

  buildCards(movies) {
    let l = []
    movies.forEach(x => {
      let info
      
      if (x.release && x.rating)
        info = x.release === '9999-01-01' ? 'TBA' : x.release + ' (' + x.rating + ')'
      else if (x.release)
        info = x.release
      else
        info = x.rating
      
      l.push(
        <Col lg="2" md="3" xs="4" key={movies.indexOf(x)}>
          <MovieCard small
            img={'http://image.tmdb.org/t/p/w200/' + x.poster}
            title={x.name}
            info={info}
            id={x.id}
          />
        </Col>
      )
    })
    return l
  }

  render() {
    
    let to_render

    if (this.state.results) {
      to_render =
        <Container className="container-padding">
          <Row>
            {this.buildCards(this.state.results)}
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
            {this.buildCards(this.state.latest.slice(0, this.state.latestCurrent * 6))}
          </Row>
          {this.state.latestCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim"
              onClick={() => this.handleShowMore('latest')}>
              Show more
            </Button>
          : ""}
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            Popular
          </div>
          <Row>
            {this.buildCards(this.state.popular.slice(0, this.state.popularCurrent * 6))}
          </Row>
          {this.state.popularCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim"
              onClick={() => this.handleShowMore('popular')}>
             Show more
            </Button>
          : ""}
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            Upcoming
          </div>
          <Row>
            {this.buildCards(this.state.upcoming.slice(0, this.state.upcomingCurrent * 6))}
          </Row>
          {this.state.upcomingCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim" 
              onClick={() => this.handleShowMore('upcoming')}>
              Show more
            </Button>
          : ""}
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
                  value={this.state.genres ? 
                    genres_.filter(x => this.state.genres.includes(x.value))
                    : ""}
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