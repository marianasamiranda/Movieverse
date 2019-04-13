import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import MovieCard from '../movie-card'
import Button from 'react-bootstrap/Button'

export default class MoviesCard extends Component {
  
  constructor(props) {
    super(props)
    this.state = {
      currentTab: 'recent',
      recent: this.buildMovieCards('recent'),
      favourites: this.buildMovieCards('favourites'),
      watchlist: this.buildMovieCards('watchlist'),
      recommended: this.buildMovieCards('recommended'),
      loading: false
    }
    this.handleChange = this.handleChange.bind(this)
  }

  buildMovieCards(tab) {
    let l = []
    this.props.movies[tab].forEach(x =>
      l.push(
        <Col xs="4" md="3" key={this.props.movies[tab].indexOf(x)}>
          <MovieCard
            img={x.img}
            title={x.title} />
        </Col>
      )
    )
    return l
  }

  handleChange(tab) {    
    this.setState({
      currentTab: tab
    })
  }

  render() {

    return (
      <div className="info-card">
        <Row>
          <Col lg="3" onClick={() => this.handleChange('recent')}
            className={"card-title " + (this.state.currentTab === "recent" ? "selected" : "")}>
            Recent
          </Col>
          <Col lg="3" onClick={() => this.handleChange('favourites')}
            className={"card-title " + (this.state.currentTab === "favourites" ? "selected" : "")}>
            Favourites
          </Col>
          <Col lg="3" onClick={() => this.handleChange('watchlist')}
            className={"card-title " + (this.state.currentTab === "watchlist" ? "selected" : "")}>
            Watchlist
          </Col>
          <Col lg="3" onClick={() => this.handleChange('recommended')}
            className={"card-title " + (this.state.currentTab === "recommended" ? "selected" : "")}>
            Recommended
          </Col>
        </Row>
        <Row className="box">
          {this.state[this.state.currentTab]}
        </Row>
        <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.loading}>
          {!this.state.loading ? "Show more" : "Loading ..."}
        </Button>
      </div>
    )
  }
}