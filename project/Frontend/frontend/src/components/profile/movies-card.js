import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import MovieCard from '../movie-card'
import Button from 'react-bootstrap/Button'
import { labels } from '../../var';

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
            {labels[this.props.lang].recent}
          </Col>
          <Col lg="3" onClick={() => this.handleChange('favourites')}
            className={"card-title " + (this.state.currentTab === "favourites" ? "selected" : "")}>
            {labels[this.props.lang].favourites}
          </Col>
          <Col lg="3" onClick={() => this.handleChange('watchlist')}
            className={"card-title " + (this.state.currentTab === "watchlist" ? "selected" : "")}>
            {labels[this.props.lang].watchlist}
          </Col>
          <Col lg="3" onClick={() => this.handleChange('recommended')}
            className={"card-title " + (this.state.currentTab === "recommended" ? "selected" : "")}>
            {labels[this.props.lang].recommended}
          </Col>
        </Row>
        <Row className="box">
          {this.state[this.state.currentTab]}
        </Row>
        <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.loading}>
          {!this.state.loading ? labels[this.props.lang].showMore : labels[this.props.lang].loading }
        </Button>
      </div>
    )
  }
}