import React, { Component } from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { labels } from '../var'
import '../styles/Tour.css'


export default class Tour extends Component {
  componentDidMount() {
    document.querySelectorAll('a[href^="#__"]').forEach(anchor => {
      anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
          behavior: 'smooth'
        });
      });
    });
  }

  componentWillUnmount() {
  }

  render() {
    return (
      <Container className="flex">
        <Container className="container-padding-large">
          <Row>
            <Col md="3" className="navigation-sidebar">
              <aside className="aside-container">
                <div className="text-center title-medium">
                  {labels[this.props.lang].contents}
                </div>
                <ul>
                  <li><a href="#__authentication">{labels[this.props.lang].authentication}</a></li>
                  <li><a href="#__feed">{labels[this.props.lang].feed}</a></li>
                  <li><a href="#__profile">{labels[this.props.lang].profile}</a></li>
                  <li><a href="#__movies">{labels[this.props.lang].movies}</a></li>
                  <li><a href="#__people">{labels[this.props.lang].people}</a></li>
                  <li><a href="#__users">{labels[this.props.lang].users}</a></li>
                  <li><a href="#__search">{labels[this.props.lang].search}</a></li>
                  <li><a href="#__news">{labels[this.props.lang].news}</a></li>
                  <li><a href="#__showtimes">{labels[this.props.lang].showtimes}</a></li>
                </ul>
              </aside>
            </Col>
            <Col xs="12" lg={{span:9, offset: 3}}>
              <article className="tour-content">
                <Row>
                  <Col xs="12">
                    <div className="title-medium text-center">
                      {labels[this.props.lang].tour}
                    </div>
                  </Col>
                </Row>

                <section className="tour-intro">
                  <Row>
                    <Col sm="3" md="2" className="text-center">
                      <img src={require('../img/info.svg')} alt="Info" width="90%" />
                    </Col>
                    <Col xs="12" sm="9" md="10" className="text-center middle">
                      {labels[this.props.lang].tourIntro}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__authentication"> 
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].authentication}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      {labels[this.props.lang].authenticationSection1}
                    </Col>
                    <Col md="6">
                      <img src={require('../img/tour/authentication.png')} alt="Authentication" width="100%" />
                    </Col>
                  </Row>
                  <div className="break"/>
                  <Row>
                    <Col md="9">
                      <img src={require('../img/tour/logout.png')} alt="Logout" width="100%" />
                    </Col>
                    <Col md="3">
                      {labels[this.props.lang].authenticationSection2}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__feed">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].feed}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="7">
                      <img src={require('../img/tour/feed.png')} alt="Feed" width="100%" />
                    </Col>
                    <Col md="5">
                      {labels[this.props.lang].feedSection1}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__profile">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].profile}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      {labels[this.props.lang].profileSection1}
                    </Col>
                    <Col md="6" className="text-center">
                      <img src={require('../img/tour/profile_goto.png')} alt="How to access" width="70%" />
                    </Col>
                  </Row>
                  <div className="break" />
                  <Row>
                    <Col md="7">
                      <img src={require('../img/tour/profile_movies.png')} alt="Movies" width="100%" />
                    </Col>
                    <Col md="5">
                      {labels[this.props.lang].profileSection2}
                    </Col>
                  </Row>
                  <div className="break" />
                  <Row>
                    <Col md="5">
                      <Row>
                        <Col md="12" className="break">
                          {labels[this.props.lang].profileSection3}
                        </Col>
                        <Col md="12" className="text-center">
                          <img src={require('../img/tour/profile_change_avatar.png')} alt="Change avatar" width="75%" />
                        </Col>
                      </Row>
                    </Col>
                    <Col md="7">
                      <Row>
                        <Col md="12" className="break">
                          {labels[this.props.lang].profileSection4}
                        </Col>
                        <Col md="12">
                          <img src={require('../img/tour/profile_change_genre.png')} alt="Change genre" width="100%" />
                        </Col>
                      </Row>
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__movies">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].movies}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      {labels[this.props.lang].moviesSection1}
                    </Col>
                    <Col md="12" className="text-center">
                      <div className="break" />
                      <img src={require('../img/tour/movie.png')} alt="Movie page" width="80%" />
                    </Col>
                  </Row>
                  <div className="break" />
                  <Row>
                    <Col md="5">
                      {labels[this.props.lang].moviesSection2}
                    </Col>
                    <Col md="7">
                      <img src={require('../img/tour/movie_comments.png')} alt="Movie comments" width="100%" />
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__people">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].people}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="7">
                      <img src={require('../img/tour/member.png')} alt="Member page" width="100%" />
                    </Col>
                    <Col md="5">
                      {labels[this.props.lang].memberSection1}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__users">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].users}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      {labels[this.props.lang].usersSection1}
                    </Col>
                    <Col md="6">
                      <img src={require('../img/tour/users_add_friend.png')} alt="Add friend" width="100%" />
                    </Col>
                  </Row>
                  <div className="break" />
                  <Row>
                    <Col xs="12" className="break">
                      {labels[this.props.lang].usersSection2}
                    </Col>
                    <Col xs="12">
                      <Row>
                        <Col md="4">
                          <img src={require('../img/tour/users_goto.png')} alt="How to access" width="100%" />
                        </Col>
                        <Col md="8">
                          <img src={require('../img/tour/users_requests.png')} alt="Requests" width="100%" />
                        </Col>
                      </Row>
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__search">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].search}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col xs="12">
                      {labels[this.props.lang].searchSection1}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__news">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].news}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      <img src={require('../img/tour/news_article.png')} alt="Article" width="100%" />
                    </Col>
                    <Col md="6">
                      {labels[this.props.lang].newsSection1}
                    </Col>
                  </Row>
                </section>

                <section className="tour-section" id="__showtimes">
                  <Row>
                    <Col xs="12">
                      <div className="title-medium">
                        {labels[this.props.lang].showtimes}
                      </div>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="7">
                      {labels[this.props.lang].showtimesSection1}
                    </Col>
                    <Col md="5" className="text-center">
                      <img src={require('../img/tour/showtimes_movie.png')} alt="Movie" width="75%" />
                    </Col>
                  </Row>
                </section>

              </article>
            </Col>
          </Row>
        </Container>
      </Container>
    )
  }
}