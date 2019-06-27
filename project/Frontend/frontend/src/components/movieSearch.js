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
import {genres, selectStyles, backend, labels} from '../var'
import Axios from 'axios'
import queryString from 'query-string'
import NoResultsFound from './aux_pages/noResultsFound';
import InfiniteScroller from 'react-infinite-scroller'

const sort = ['dateAsc', 'dateDesc', 'rating']

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
      upcomingCurrent: 1,
      cards: [],
      next: 1,
    })

    this.handleTitle = this.handleTitle.bind(this)
    this.handleSort = this.handleSort.bind(this)
    this.handleGenre = this.handleGenre.bind(this)
    this.search = this.search.bind(this)
    this.handleShowMore = this.handleShowMore.bind(this)
    this.loadMore = this.loadMore.bind(this)
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
    Axios.get(backend + '/movie/search-page').then(x => {
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

    Axios.get(backend + '/movie/search' + query).then(x => {
      console.log('search');
      
      this.setState({
        results: x.data,
        next: 1,
        cards: [],
      })      
    })
    
  }

  buildCards(movies, isSearch, i) {
    let l = []
    let m
    
    if (isSearch) {
      m = movies.slice(18 * (i - 1), 18 * i)
    }
    else {
      m = movies
    }

    m.forEach(x => {
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
            img={'http://image.tmdb.org/t/p/w154/' + x.poster}
            title={x.name}
            info={info}
            id={x.id}
          />
        </Col>
      )
    })
    return l
  }

  loadMore() {
    const cards = this.state.cards.concat(this.buildCards(this.state.results, true, this.state.next))
    this.setState({
      cards: cards,
      next: this.state.next + 1,
    })
  }

  render() {
    let to_render

    if (this.state.results) {
      if (this.state.results.length === 0) {
        to_render =
          <Container className="container-padding">
            <NoResultsFound lang={this.props.lang}/>
        </Container>
      }
      else {
      to_render =
        <Container className="container-padding">
          <Row>
            <InfiniteScroller
              pageStart={0}
              loadMore={this.loadMore}
              hasMore={this.state.cards.length < this.state.results.length}>
              <Row>
                {this.state.cards}
              </Row>
            </InfiniteScroller>
          </Row>
        </Container>
      }
    }

    else {
      to_render = 
        <>
        <Container className="container-padding">
          <div className="title-medium">
            {labels[this.props.lang].newReleases}
          </div>
          <Row>
            {this.buildCards(this.state.latest.slice(0, this.state.latestCurrent * 6))}
          </Row>
          {this.state.latestCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim"
              onClick={() => this.handleShowMore('latest')}>
              {labels[this.props.lang].showMore}
            </Button>
          : ""}
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            {labels[this.props.lang].popular}
          </div>
          <Row>
            {this.buildCards(this.state.popular.slice(0, this.state.popularCurrent * 6))}
          </Row>
          {this.state.popularCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim"
              onClick={() => this.handleShowMore('popular')}>
              {labels[this.props.lang].showMore}
            </Button>
          : ""}
        </Container>
        <Container className="container-padding">
          <div className="title-medium">
            {labels[this.props.lang].upcoming}
          </div>
          <Row>
            {this.buildCards(this.state.upcoming.slice(0, this.state.upcomingCurrent * 6))}
          </Row>
          {this.state.upcomingCurrent < 5 ?
            <Button variant="secondary" size="sm" className="button-slim" 
              onClick={() => this.handleShowMore('upcoming')}>
              {labels[this.props.lang].showMore}
            </Button>
          : ""}
        </Container>
        </>
    }

    return (
      <>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">{labels[this.props.lang].movieSearch}</h1>
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
                    placeholder={labels[this.props.lang].name}
                    onChange={this.handleTitle} 
                  />
                </InputGroup>
              </Col>
            </Row>
            <Row>
              <Col md={{ span: 6, offset: 3 }} xs="12">
                <Select
                  placeholder={labels[this.props.lang].genres}
                  isSearchable
                  isClearable
                  isMulti
                  value={this.state.genres ? 
                    genres.filter(x => this.state.genres.includes(x))
                          .map(x => { return { value: x, label: labels[this.props.lang][x] } })
                    : ""}
                  options={genres.map(x => {return {value: x, label: labels[this.props.lang][x]}})}
                  styles={selectStyles}
                  onChange={this.handleGenre}
                  name="genres"
                />
              </Col>
            </Row>
            <Row>
              <Col md={{ span: 6, offset: 3 }} xs="12">
                <Select
                  placeholder={labels[this.props.lang].sortBy}
                  isSearchable
                  isClearable
                  options={sort.map(x => {return {value: x, label: labels[this.props.lang][x]}})}
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