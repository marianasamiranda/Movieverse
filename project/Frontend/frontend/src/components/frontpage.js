import React, { Component } from 'react';
import Banner from './banner'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import HorizontalCard from './horizontal-card'
import MovieCard from './movie-card'
import StatsItem from './stats-item'
import logo from '../img/logo.png'

export default class FrontPage extends Component {
  render() {
    return (
      <div>
        <Banner />
        <Container className="container-padding">
          <div className="title">
            NEW RELEASES
          </div>
          <Row>
            <Col>
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col>
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col>
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
            <Col>
              <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
            </Col>
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title padding-top">
              JOIN MOVIEVERSE TODAY!
            </div>
            <Row>
              <Col lg="6">
                <HorizontalCard img={require('../img/heart.png')} text="Get to know your friends' movie tastes" />
              </Col>
              <Col lg="6">
                <HorizontalCard img={require('../img/popcorn.png')} text="Catch up with the hottest new releases" />
              </Col>
            </Row>
            <Row>
              <Col lg="6">
                <HorizontalCard img={require('../img/notebook.png')} text="Keep track of your entire movie history" />
              </Col>
              <Col lg="6">
                <HorizontalCard img={require('../img/achievement.png')} text="Earn achievements watching movies" />
              </Col>
            </Row>
          </Container>
        </div>
        <Container className="container-padding">
          <div className="title padding-top">
            STATISTICS
          </div>
          <Row>
            <Col lg="3" md="3" sm="6" className="stats-margin">
              <StatsItem img={require('../img/girl.png')} name="Users" number="123" />
            </Col>
            <Col lg="3" md="3" sm="6" className="stats-margin">
              <StatsItem img={require('../img/watch.png')} name="Movies" number="20000" />
            </Col>
            <Col lg="3" md="3" sm="6" className="stats-margin">
              <StatsItem img={require('../img/actor.png')} name="Actors" number="1000" />
            </Col>
            <Col lg="3" md="3" sm="6" className="stats-margin">
              <StatsItem img={require('../img/time.png')} name="Hours" number="962362" />
            </Col>
          </Row>
        </Container>
        <div className="bg-light-gray">
          <Container className="container-padding">
            <div className="title padding-top">
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