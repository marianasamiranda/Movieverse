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
import Loading from '../aux_pages/loading'
import NoAuthError from '../aux_pages/noAuthError'
import NotFoundError from '../aux_pages/notFoundError'

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

    let user = undefined
    if (this.props.match) {
      user = this.props.match.params.username
    }

    this.state = {
      showGenreModal: false,
      showAvatarModal: false,
      dragOn: false,
      user: user,
    }
    this.getUserInfo(user)
    this.handleGenreModal = this.handleGenreModal.bind(this)
    this.handleAvatarModal = this.handleAvatarModal.bind(this)
    this.handleNewAvatarConfirm = this.handleNewAvatarConfirm.bind(this)
    this.handleNewAvatar = this.handleNewAvatar.bind(this)
    this.handleNewGenre = this.handleNewGenre.bind(this)
    this.handleNewGenreConfirm = this.handleNewGenreConfirm.bind(this)
    this.handleFriendRequest = this.handleFriendRequest.bind(this)
  }

  componentDidMount() {
    document.title = "Profile | Movieverse"
  }

  componentDidUpdate(prevProps, prevState) {
    this.checkReload()
  }

  getUserInfo(user) {
    const token = getToken()
    let query = ""
    if (user) {
      query = "?username=" + user
    }

    return Axios.get(backend + '/profile' + query, 
        { headers: { Authorization: "Bearer " + token } }).then(x => {
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
        stats: stats,
        user: x.data['self'] ? undefined : user,
        isFriend: data['friendship']
      })
    })
    .catch(x => {
      if (x.response.data === 'Wrong token') {
        this.setState({
          noAuth: true
        })
      }
      else {
        this.setState({
          noUser: true
        })
      }
    })
  }

  handleAvatarModal() {
    this.setState({
      showAvatarModal: !this.state.showAvatarModal
    })
  }

  handleNewAvatar(f) {
    this.setState({
      newAvatar: f
    })
  }

  handleNewAvatarConfirm(e) {
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
      this.handleAvatarModal()
    })
    .catch(x => { //TODO remove?
      console.log(x.response.data)
      this.setState({
        newAvatarLoading: false,
      })
    })
  }

  handleGenreModal(e) {
    const current = this.state.showGenreModal
    if (current) {
      this.setState({
        newGenre: undefined
      })
    }
    this.setState({
      showGenreModal: !this.state.showGenreModal
    })
  }
  
  handleNewGenre(e) {
    this.setState({
      newGenre: e.value
    })
  }

  handleNewGenreConfirm(e) {
    this.setState({
      newGenreLoading: true
    })

    Axios.put(backend + '/genre', {genre: this.state.newGenre}, 
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        let data = this.state.data
        data['genre'] = this.state.newGenre
        this.setState({
          data: data,
          newGenreLoading: false
        })
        this.handleGenreModal()
      })
  }

  handleFriendRequest(e) {
    Axios.post(backend + '/friend-request', {username: this.state.user},
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        let data = this.state.data
        data['friendship'] = 'requested'
        this.setState({
          data: data
        })
      })
      .catch(x => console.log(x.response.data))
  }

  friendStatusButton() {
    switch (this.state.data.friendship) {
      case 'friends':
        return (
          <Button disabled className="button-slim margin-top-10">
            Friends
          </Button>
        )
    
      case 'requested':
        return (
          <Button disabled variant="secondary" className="button-slim margin-top-10">
            Request sent
          </Button>
        )

      case 'received':
        return (
          <Button disabled variant="warning" className="button-slim margin-top-10">
            Request received
          </Button>
        )

      default:
        return (
          <Button variant="success" className="button-slim margin-top-10"
            onClick={this.handleFriendRequest}>
            <i className="fas fa-plus" /> Add friend
          </Button>
        )
    }
  }


  checkReload() {
    if (this.props.match && this.props.match.params.username !== this.state.user) {
      this.getUserInfo(this.props.match.params.username)
    }
    else if (!this.props.match) {
      this.getUserInfo()
    }
  }


  render() {
    if (this.state.noAuth) {
      return (
        <NoAuthError />
      )
    }

    else if (this.state.noUser) {
      return (
        <NotFoundError />
      )
    }

    else if (!this.state.data) {
      return (
        <Loading />
      )
    }

    const data = this.state.data

    return (
      <div>
        <Container className="container-padding-large">
          <Row>
            <Col xs="auto">
              <h1 className="title">{data.username}</h1>
            </Col>
            {this.state.user ?
              <Col xs="auto" >
                {this.friendStatusButton()}
              </Col>
              : ""
            }
          </Row>
          <Row>
            <Col xs="12" lg="6">
              <Row>
                <Col xs="12" sm="5" className="text-center" onClick={this.handleAvatarModal}>
                  <img 
                    src={data.avatar ? data.avatarImg : avatars + data.gender + '.svg'} 
                        className={"profile-img " + (!this.state.user ? "clickable" : "")}
                        alt="Profile" />
                  {!this.state.user ?
                  <i className="far fa-edit align-top edit"></i>
                  : ""}
                </Col>
                <Col xs="12" sm="7">
                  <InfoCard
                    name={data.name}
                    genre={data.genre ? data.genre : "None"}
                    birthdate={data.birthdate}
                    joined={data.joindate}
                    country={data.country}
                    changeGenre={!this.state.user ? this.handleGenreModal : ""}
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

        {!this.state.user ?
        <div>
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
                onChange={this.handleNewGenre}
                name="sort" />
            </Modal.Body>
            {this.state.newGenreLoading ? 
              <Modal.Footer>
                <ReactLoading type="bars" color="#4d4d4d" width="40pt" height="29pt" className="float-right" />
              </Modal.Footer>
              :
              <Modal.Footer>
                <Button size="sm" variant="secondary" onClick={this.handleGenreModal}>
                  Cancel
                </Button>
                <Button disabled={this.state.newGenre ? false : true} size="sm" 
                        className="custom-button" onClick={this.handleNewGenreConfirm}>
                  Confirm
                </Button>
              </Modal.Footer>
            }
          </Modal>

          {/* Change avatar modal */}
          <Modal show={this.state.showAvatarModal} onHide={this.handleAvatarModal}>
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
                  <Button size="sm" variant="secondary" onClick={this.handleAvatarModal}>
                    Cancel
                  </Button>
                  <Button size="sm" disabled={this.state.newAvatar ? false : true} 
                          className="custom-button" onClick={this.handleNewAvatarConfirm}>
                    Confirm
                  </Button>
                </Modal.Footer>
              }
            </Modal>
          </div>
        : ""}
      </div>
    )
  }
}