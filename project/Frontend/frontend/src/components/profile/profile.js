import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import '../../styles/Profile.css'
import InfoCard from './info-card'
import BadgesCard from './badges-card'
import StatsCard from './stats-card'
import Select from 'react-select'
import {genres, backend, avatars} from '../../var'
import Dropzone from 'react-dropzone'
import FriendsCard from './friends-card'
import MoviesCard from './movies-card'
import ReactLoading from 'react-loading'
import { getToken } from '../../cookies'
import Axios from 'axios'

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
      dragOn: false,
      user: this.props.user,
    }
    this.getUserInfo()
    this.handleGenreModal = this.handleGenreModal.bind(this)
    this.handleAvatarModalChange = this.handleAvatarModalChange.bind(this)
    this.handleAvatarModalConfirm = this.handleAvatarModalConfirm.bind(this)
    this.handleNewAvatar = this.handleNewAvatar.bind(this)
  }

  componentDidMount() {
    document.title = "Profile | Movieverse"
  }

  getUserInfo() {
    const token = getToken()

    if (!this.state.user) {
      return Axios.get(backend + '/profile', { headers: { Authorization: "Bearer " + token } }).then(x => {
        let stats = {}
        stats['Movies'] = x.data.statsMovies
        stats['Hours'] = x.data.statsHours
        stats['Comments'] = x.data.statsComments
        stats['Ratings'] = x.data.statsRatings
        stats['Friends'] = x.data.statsFriends
        
        let data = x.data
        data['avatarImg'] = avatars + data['avatar']

        this.setState({
          data: x.data,
          stats: stats
        })

        this.props.setAvatar(
          data.avatar ?
            data['avatarImg'] :
            avatars + data.gender + '.svg')
      })
    }
  }

  handleGenreModal(e) {
    this.setState({
      showGenreModal: !this.state.showGenreModal
    })
  }

  handleAvatarModalChange() {
    this.setState({
      showAvatarModal: !this.state.showAvatarModal
    })
  }

  handleNewAvatar(f) {
    this.setState({
      newAvatar: f
    })
  }

  handleAvatarModalConfirm(e) {
    this.setState({
      newAvatarLoading: true
    })

    let f = new FormData()
    f.set('image', this.state.newAvatar)

    Axios.put(backend + '/avatar', f, { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      let data = this.state.data
      data['avatar'] = x.data
      data['avatarImg'] = avatars + x.data + '?' + new Date().getTime()
      this.setState({
        data: data,
        newAvatarLoading: false,
      })
      this.props.setAvatar(data['avatarImg'])
      this.handleAvatarModalChange()
    })
    .catch(x => {
      console.log(x.response.data);
      
      this.setState({
        newAvatarLoading: false,
      })
    })
  }

  render() {    
    //TODO add proper loading screen
    if (!this.state.data) {
      return (
        <div></div>
      )
    }
    else {
      const data = this.state.data
    
      return (
        <div>
          <Container className="container-padding-large">
            <Row>
              <Col>
                <h1 className="title">{data.username}</h1>
              </Col>
            </Row>
            <Row>
              <Col xs="12" lg="6">
                <Row>
                  <Col xs="12" sm="5" className="text-center" onClick={this.handleAvatarModalChange}>
                    <img 
                      src={data.avatar ? data.avatarImg : avatars + data.gender + '.svg'} 
                          className="profile-img" 
                          alt="Profile" />
                    <i className="far fa-edit align-top edit"></i>
                  </Col>
                  <Col xs="12" sm="7">
                    <InfoCard 
                      name={data.name}
                      genre="Action"
                      birthdate={data.birthdate}
                      joined={data.joindate}
                      country={data.country}
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
                <StatsCard stats={this.state.stats}/>
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
          <Modal show={this.state.showAvatarModal} onHide={this.handleAvatarModalChange}>
            <Modal.Header closeButton>
              <Modal.Title>Change Avatar</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Dropzone 
                accept='image/jpeg, image/png, image/svg'
                multiple={false}
                onDrop={acceptedFiles => this.handleNewAvatar(acceptedFiles[0])} >
                {({ getRootProps, getInputProps }) => (
                  <section >
                    <div {...getRootProps()} className="dropzone">
                      <input {...getInputProps()} />
                      <p>{this.state.newAvatar 
                          ? this.state.newAvatar.name 
                          : "Drag an image or click here to select (.jpeg, .png, .svg)"}</p>
                    </div>
                  </section>
                )}
              </Dropzone>
            </Modal.Body>

              {this.state.newAvatarLoading ? 
                <Modal.Footer>
                  <ReactLoading type="bars" color="#4d4d4d" width="40pt" height="29pt" className="float-right" />
                </Modal.Footer>
                :
                <Modal.Footer>
                  <Button size="sm" variant="secondary" onClick={this.handleAvatarModalChange}>
                    Cancel
                  </Button>
                  <Button size="sm" disabled={this.state.newAvatar ? false : true} className="custom-button" onClick={this.handleAvatarModalConfirm}>
                    Confirm
                  </Button>
                </Modal.Footer>
              }
          </Modal>
        </div>
      )
    }
  }
}