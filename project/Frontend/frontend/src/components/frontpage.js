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

export default class FrontPage extends Component {

  componentDidMount() {
    document.title = "Movieverse"
  }

  render() {
    return (
      <div>
        <Banner handleSession={this.props.handleSession} />
        <Container className="container-padding">
          <div className="title">
            NEW RELEASES
          </div>
          <Row>
            <Col sm="3" xs="6">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col sm="3" xs="6">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col sm="3" xs="6">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col sm="3" xs="6">
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title">
              JOIN MOVIEVERSE TODAY!
            </div>
            <Row>
              <Col lg="6">
                <SellingPoint img={require('../img/heart.png')} text="Get to know your friends' movie tastes" />
              </Col>
              <Col lg="6">
                <SellingPoint img={require('../img/popcorn.png')} text="Catch up with the hottest new releases" />
              </Col>
            </Row>
            <Row>
              <Col lg="6">
                <SellingPoint img={require('../img/notebook.png')} text="Keep track of your entire movie history" />
              </Col>
              <Col lg="6">
                <SellingPoint img={require('../img/achievement.png')} text="Earn achievements watching movies" />
              </Col>
            </Row>
          </Container>
        </div>
        <Container className="container-padding stats">
          <div className="title">
            STATISTICS
          </div>
          <Row>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem img={require('../img/girl.png')} name="Users" number="123" />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem img={require('../img/watch.png')} name="Movies" number="20000" />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem img={require('../img/actor.png')} name="Actors" number="1000" />
            </Col>
            <Col xs="12" sm="6" lg="3" className="stats-margin">
              <StatsItem img={require('../img/time.png')} name="Hours" number="9623" />
            </Col>
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title">
              ABOUT US
            </div>
            <Row>
              <Col lg="6">
                <div className="about-text">
                  Lorem ipsum dolor sit amet, consectetur
                  adipiscing elit. Curabitur nec magna lorem.  Etiam quis sapien convallis, eleifend arcu vel,
                  euismod tellus. Suspendisse porttitor est
                  nibh, id semper enim eleifend in.
                  Aliquam erat volutpat. Etiam finibus dui sed nunc semper condimentum. Vestibulum quis nulla quis quam pulvinar porttitor. Nullam ut dignissim justo.
                  Aliquam erat volutpat. Etiam finibus dui sed nunc semper condimentum. Vestibulum quis nulla quis quam pulvinar porttitor. Nullam ut dignissim justo.
                  Aliquam erat volutpat. Etiam finibus dui sed nunc semper condimentum. Vestibulum quis nulla quis quam pulvinar porttitor. Nullam ut dignissim justo.
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