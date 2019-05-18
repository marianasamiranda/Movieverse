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
import Axios from 'axios'
import { backend, avatars, labels } from '../var'
import { getToken } from '../cookies';

export default class FindPeople extends Component {
  constructor(props) {
    super(props)
    this.state = {
      mightKnowLoading: false,
      name: undefined,
      nameTimeout: 0,
      results: undefined
    }
    this.handleName = this.handleName.bind(this)
    this.search = this.search.bind(this)
  }

  componentDidMount() {
    document.title = "Find Users | Movieverse"
  }

  handleName(e) {
    if (this.state.nameTimeout) {
      clearTimeout(this.state.nameTimeout)
    }

    this.setState({
      name: e.target.value,
      nameTimeout: setTimeout(x => this.search(), 300)
    })
  }

  search() {
    let query = '?name=' + this.state.name
    Axios.get(backend + '/users-search' + query, 
        { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
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
              img={avatars + x[1].avatar}
              title={x[1].username}
              country={x[1].country}
              info={x[1].name}
              user
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

    else to_render = 
      <>
      <Container className="container-padding">
        <div className="title-medium">
          {labels[this.props.lang].userMightKnow}
        </div>
        <Row>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (5)</>} info="Real Name" />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (5)</>} info="Real Name" />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (3)</>} info="Real Name" />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (2)</>} info="Real Name" />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (2)</>} info="Real Name" />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad (2)</>} info="Real Name" />
          </Col>
        </Row>
        <Button variant="secondary" size="sm" className="button-slim" disabled={this.state.mightKnowLoading}>
          {!this.state.mightKnowLoading ? "Show more" : "Loading ..."}
        </Button>
      </Container>
      <Container className="container-padding">
        <div className="title-medium">
          {labels[this.props.lang].mostUpvoted}
          </div>
        <Row>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
          <Col lg="2" md="3" xs="4">
            <MovieCard small img="http://placehold.it/228x337" title={<><Flag country="pt" />asdsad</>} info={'x ' + labels[this.props.lang].likes} />
          </Col>
        </Row>
      </Container>
      </>

    return (
      <>
        <Jumbotron fluid>
          <Container className="text-center">
            <h1 className="jumbotron-text">{labels[this.props.lang].findUsers}</h1>
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
                    placeholder={labels[this.props.lang].usernameRealName}
                    onChange={this.handleName}
                  />
                </InputGroup>
              </Col>
            </Row>
          </Container>
        </Jumbotron>
        {to_render}
      </>
    )
  }
}