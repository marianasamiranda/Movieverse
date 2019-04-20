import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Jumbotron from 'react-bootstrap/Jumbotron'
import MovieCard from './movie-card'
import Select from 'react-select'
//import Axios from 'axios'
//import {backend} from '../var'
import {selectStyles, theaters} from '../var'

const theaters_ = []
theaters.forEach(x => theaters_.push({value: x, label: x}))

export default class PeopleSearch extends Component {
  constructor(props) {
    super(props)
    this.state = {
      theater: 'Cinemas NOS Braga Parque'
    }
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(v) {
    this.setState({
      theater : v.value
    })
  }

  componentDidMount() {
    document.title = "Showtimes | Movieverse"
  }

  render() {
    return (
      <div>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">Showtimes</h1>
            <Row >
              <Col md={{ span: 6, offset: 3 }} className="input-margin">
                <Select
                  placeholder="Theater"
                  isSearchable
                  options={theaters_}
                  styles={selectStyles}
                  onChange={this.handleChange}
                  value={{value: this.state.theater, label: this.state.theater}}
                />
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        <Container className="container-padding">
          <div className="title-medium">
            {this.state.theater}
          </div>
          <Row>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
            <Col lg="2" md="3" xs="4">
              <MovieCard small img="http://placehold.it/228x337" title="Movie Title" info="4:30 pm | 5:20 |  6:00" />
            </Col>
          </Row>
        </Container>
      </div>
    )
  }
}