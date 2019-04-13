import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import '../../styles/Profile.css'
import InfoCard from './info-card'
import BadgesCard from './badges-card';
import StatsCard from './stats-card';
import Select from 'react-select'
import {genres} from '../../var'
import Dropzone from 'react-dropzone'
import FriendsCard from './friends-card'
import MoviesCard from './movies-card';

const stats = [
  {name: 'Movies', img:'popcorn.svg', total:123},
  {name: 'Hours', img:'pocket-watch.svg', total:123},
  {name: 'Comments', img:'chat.svg', total:123},
  {name: 'Ratings', img:'star.svg', total:123},
  {name: 'Friends', img:'heart.svg', total:123},
]

const friends = [
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') },
  { username: 'username1', img:require( '../../img/girl.png') }
]

const movies = {
  recent: [
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')}
  ],
  favourites: [
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')},
    {title: 'Movie Title', img:require('../../img/movie_card.png')}
  ],
  watchlist: [
    { title: 'Movie Title', img:require( '../../img/movie_card.png') }
  ],
  recommended: [
    { title: 'Movie Title', img:require( '../../img/movie_card.png') },
    { title: 'Movie Title', img:require( '../../img/movie_card.png') },
    { title: 'Movie Title', img:require( '../../img/movie_card.png') },
    { title: 'Movie Title', img:require( '../../img/movie_card.png') }
  ]
}


export default class Profile extends Component {
  constructor(props) {
    super(props)
    this.state = {
      showGenreModal: false,
      showAvatarModal: false,
      dragOn: false
    }
    this.handleGenreModal = this.handleGenreModal.bind(this)
    this.handleAvatarModal = this.handleAvatarModal.bind(this)
  }

  handleGenreModal(e) {
    this.setState({
      showGenreModal: !this.state.showGenreModal
    })
  }

  handleAvatarModal(e) {
    this.setState({
      showAvatarModal: !this.state.showAvatarModal
    })
  }

  render() {
    return (
      <div>
        <Container className="container-padding-large">
          <Row>
            <Col>
              <h1 className="title">johhnyd</h1>
            </Col>
          </Row>
          <Row>
            <Col xs="12" lg="6">
              <Row>
                <Col xs="12" sm="5" className="text-center" onClick={this.handleAvatarModal}>
                  <img src={require('../../img/monster.png')} className="profile-img" alt="Profile" />
                  <i className="far fa-edit align-top edit"></i>
                </Col>
                <Col xs="12" sm="7">
                  <InfoCard 
                    name="John Doe"
                    genre="Action"
                    birthdate="1/1/1"
                    joined="1/1/1"
                    country="pt"
                    changeGenre={this.handleGenreModal}
                  />
                </Col>
              </Row>
            </Col>
            <Col xs="12" lg="6" xl={{span: 5, offset: 1}}>
              <BadgesCard />
            </Col>
          </Row>
          <Row>
            <Col>
              <StatsCard stats={stats}/>
            </Col>
          </Row>
          <Row>
            <Col xs="12" lg="9">
              <MoviesCard movies={movies} />
            </Col>
            <Col xs="12" lg="3">
              <FriendsCard friends={friends} />
            </Col>
          </Row>
        </Container>


        {/* Change favourite genre modal */}
        <Modal show={this.state.showGenreModal} onHide={this.handleGenreModal} size="sm">
          <Modal.Header closeButton>
            <Modal.Title>Favourite Genre</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Select 
              placeholder="Genre"
              isSearchable
              options={Object.keys(genres).map(x => genres[x])}
              //onChange={this.handleChange}
              name="sort" />
          </Modal.Body>
          <Modal.Footer>
            <Button size="sm" variant="secondary" onClick={this.handleGenreModal}>
              Cancel
            </Button>
            <Button size="sm" className="custom-button" onClick={this.handleGenreModal}>
              Confirm
            </Button>
          </Modal.Footer>
        </Modal>

        {/* Change avatar modal */}
        <Modal show={this.state.showAvatarModal} onHide={this.handleAvatarModal}>
          <Modal.Header closeButton>
            <Modal.Title>Change Avatar</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Dropzone 
              multiple={false}
              onDrop={acceptedFiles => console.log(acceptedFiles)}>
              {({ getRootProps, getInputProps }) => (
                <section className="dropzone">
                  <div {...getRootProps()}>
                    <input {...getInputProps()} />
                    <p>Drag an image or click here to select</p>
                  </div>
                </section>
              )}
            </Dropzone>
          </Modal.Body>
          <Modal.Footer>
            <Button size="sm" variant="secondary" onClick={this.handleAvatarModal}>
              Cancel
            </Button>
            <Button size="sm" className="custom-button" onClick={this.handleAvatarModal}>
              Confirm
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    )
  }
}