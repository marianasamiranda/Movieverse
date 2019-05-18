import React, { Component } from 'react';
import Banner from './banner'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import SellingPoint from './selling-point'
import MovieCard from './movie-card'
import StatsItem from './stats-item'
import logo from '../img/logo.png'
import '../styles/FrontPage.css'
import Axios from 'axios';
import { backend, labels } from '../var';

export default class FrontPage extends Component {

  constructor(props) {
    super(props)
    this.state = {
      data: {
        users: '',
        movies: '',
        members: '',
        comments: '',
        releases: [],
        cards: undefined
      }
    }
    this.getInfo()
  }

  getInfo() {
    Axios.get(backend + '/frontpage').then(x => {
      this.setState({
        data: x.data
      })
      this.buildCards()
    })
  }

  componentDidMount() {
    document.title = "Movieverse"
  }

  buildCards() {
    let l = []
    this.state.data.releases.forEach(x => {
      l.push(
        <Col sm="3" xs="6" key={this.state.data.releases.indexOf(x)}>
          <MovieCard 
            img={'http://image.tmdb.org/t/p/w200/' + x.poster} 
            title={x.name} 
            info={x.date}
            id={x.id} />
        </Col>
      )
    })
    this.setState({
      cards: l
    })
  }

  render() {
    return (
      <div>
        <Banner handleSession={this.props.handleSession} lang={this.props.lang} />
        <Container className="container-padding">
          <div className="title">
            {labels[this.props.lang].newReleases}
          </div>
          <Row>
            {this.state.cards}
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title">
              {labels[this.props.lang].joinMovieverse}
            </div>
            <Row>
              <Col lg="6">
                <SellingPoint 
                  img={require('../img/heart.png')} 
                  text={labels[this.props.lang].sellPoint1} />
              </Col>
              <Col lg="6">
                <SellingPoint 
                  img={require('../img/popcorn.png')} 
                  text={labels[this.props.lang].sellPoint2} />
              </Col>
            </Row>
            <Row>
              <Col lg="6">
                <SellingPoint 
                  img={require('../img/notebook.png')} 
                  text={labels[this.props.lang].sellPoint3} />
              </Col>
              <Col lg="6">
                <SellingPoint 
                  img={require('../img/achievement.png')} 
                  text={labels[this.props.lang].sellPoint4} />
              </Col>
            </Row>
          </Container>
        </div>
        <Container className="container-padding stats">
          <div className="title">
            {labels[this.props.lang].statistics}
          </div>
          <Row>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem 
                img={require('../img/girl.png')} 
                name={labels[this.props.lang].users}
                number={this.state.data.users} />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem 
                img={require('../img/watch.png')} 
                name={labels[this.props.lang].movies} 
                number={this.state.data.movies} />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem 
                img={require('../img/actor.png')} 
                name={labels[this.props.lang].members}
                number={this.state.data.members} />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem 
                img={require('../img/comments.png')} 
                name={labels[this.props.lang].comments}
                number={this.state.data.comments} />
            </Col>
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title">
              {labels[this.props.lang].aboutUs}
            </div>
            <Row>
              <Col lg="6">
                <div className="about-text">
                  {labels[this.props.lang].aboutUsText}
                </div>
              </Col>
              <Col lg="6" md="auto" className="text-right">
                <img src={logo} width="80%" alt="" />
              </Col>
            </Row>
          </Container>
        </div>
      </div>
      )
  }
}