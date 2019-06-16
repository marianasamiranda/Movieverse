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
import Loading from './aux_pages/loading'
import NoAuthError from './aux_pages/noAuthError'

export default class FindPeople extends Component {
  constructor(props) {
    super(props)
    this.state = {
      mightKnowLoading: false,
      name: undefined,
      nameTimeout: 0,
      results: undefined,
      loading: true,
      data: []
    }
    this.getInfo()
    this.handleName = this.handleName.bind(this)
    this.search = this.search.bind(this)
  }

  componentDidMount() {
    document.title = "Find Users | Movieverse"
  }
  
  getInfo() {
    Axios.get(backend + '/user/search-page', { headers: { Authorization: "Bearer " + getToken()}}).then(x => {
      this.setState({
        data: x.data,
        loading: false
      })
    })
    .catch(x => this.setState({
      noAuth: true
    }))
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
    Axios.get(backend + '/user/search' + query, 
        { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      this.setState({
        results: x.data
      })
    })
  }

  buildAvatars(data) {
    console.log(data);
    
    let l = [], i = 0
    data.forEach(x => {
      l.push(
        <Col lg="2" md="3" xs="4" key={i++}>
          <MovieCard small
            img={avatars + x.avatar}
            title={x.username}
            country={x.country}
            info={x.name + (x.count !== undefined ? '\n(' + x.count + ' 👍)' : "")}
            user
          />
        </Col>
      )
    })
    return l
  }


  render() {

    if (this.state.noAuth) {
      return <NoAuthError lang={this.props.lang} />
    }

    if (this.state.loading) {
      return <Loading lang={this.props.lang} />
    }

    let to_render

    if (this.state.results) {
      //let results = [], i = 0
      //Object.entries(this.state.results).forEach(x => {
      //  results.push(
      //    <Col lg="2" md="3" xs="4" key={i++}>
      //      <MovieCard small
      //        img={avatars + x[1].avatar}
      //        title={x[1].username}
      //        country={x[1].country}
      //        info={x[1].name}
      //        user
      //      />
      //    </Col>
      //  )
      //})
      console.log(this.state.results);
      
      to_render =
        <Container className="container-padding">
          <Row>
            {this.buildAvatars(this.state.results)}
          </Row>
        </Container>
    }

    else to_render = 
      <Container className="container-padding">
        <div className="title-medium">
          {labels[this.props.lang].mostUpvoted}
          </div>
        <Row>
          {this.buildAvatars(this.state.data)}
        </Row>
      </Container>

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