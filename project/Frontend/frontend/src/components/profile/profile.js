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
import {genres, backend, avatars, labels} from '../../var'
import Dropzone from 'react-dropzone'
import FriendsTab from './friends-tab'
import MoviesTab from './movies-tab'
import ReactLoading from 'react-loading'
import { getToken } from '../../cookies'
import Axios from 'axios'
import Loading from '../aux_pages/loading'
import NoAuthError from '../aux_pages/noAuthError'
import NotFoundError from '../aux_pages/notFoundError'


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
      user: user
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
    this.checkReload(prevProps)
  }

  getUserInfo(user) {
    const token = getToken()
    let query = ""
    if (user) {
      query = "?username=" + user
    }

    return Axios.get(backend + '/user/profile' + query, 
        { headers: { Authorization: "Bearer " + token } }).then(x => {
      
      let data = x.data
      data['avatarImg'] = avatars + data['avatar']
          
      //TODO remove this.state.data
      this.setState({
        data: x.data,
        stats: {
          movies:x.data.statsMovies,
          hours: x.data.statsHours,
          comments: x.data.statsComments,
          ratings: x.data.statsRatings,
          friends: x.data.statsFriends
        },
        user: x.data.self ? undefined : user,
        badges: x.data.badges,
        friends: x.data.friends,
        isFriend: x.data.friendship,
        movies: {
          recent: x.data.recent,
          favourites: x.data.favourite,
          watchlist: x.data.watchlist,
          recommended: x.data.recommended
        }
      })

      if (x.data['self'] || !this.props.match)
        document.title = "Profile | Movieverse"
      else
        document.title = x.data.username + " Profile | Movieverse"
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

    Axios.put(backend + '/user//avatar', f, { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
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

    Axios.put(backend + '/user/genre', {genre: this.state.newGenre}, 
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
    Axios.post(backend + '/user/friends/requests/new', {username: this.state.user},
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        let data = this.state.data
        data['friendship'] = 'requested'
        this.setState({
          data: data
        })
      })
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


  checkReload(prevProps) {
    if (this.state.data) {
      if (this.props.match && this.props.match.params.username !== this.state.data.username) {
        this.getUserInfo(this.props.match.params.username)
      }
      else if (!this.props.match && prevProps.match) {
        this.getUserInfo()
      }
    }
  }


  render() {
    if (this.state.noAuth) {
      return (
        <NoAuthError lang={this.props.lang} />
      )
    }

    else if (this.state.noUser) {
      return (
        <NotFoundError lang={this.props.lang} />
      )
    }

    else if (!this.state.data) {
      return (
        <Loading lang={this.props.lang}/>
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
                    lang={this.props.lang}
                  />
                </Col>
              </Row>
            </Col>
            <Col xs="12" lg="6" xl={{span: 5, offset: 1}}>
              <BadgesCard userBadges={this.state.badges}/> 
            </Col>
          </Row>
          <Row>
            <Col>
              <StatsCard stats={this.state.stats} lang={this.props.lang}/>
            </Col>
          </Row>
          <Row>
            <Col xs="12" lg="9">
              <MoviesTab movies={this.state.movies} user={data.username} lang={this.props.lang}/>
            </Col>
            <Col xs="12" lg="3">
              <FriendsTab friends={this.state.friends} user={data.username} lang={this.props.lang}/>
            </Col>
          </Row>
        </Container>

        {!this.state.user ?
        <div>
          {/* Change favourite genre modal */}
          <Modal show={this.state.showGenreModal} onHide={this.handleGenreModal} size="sm">
            <Modal.Header closeButton>
              <Modal.Title>{labels[this.props.lang].favouriteGenre}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Select 
                placeholder="Genre"
                isSearchable
                  options={genres.map(x => { return { value: x, label: labels[this.props.lang][x] } })}
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
                  {labels[this.props.lang].cancel}
                </Button>
                <Button disabled={this.state.newGenre ? false : true} size="sm" 
                        className="custom-button" onClick={this.handleNewGenreConfirm}>
                  {labels[this.props.lang].confirm}
                </Button>
              </Modal.Footer>
            }
          </Modal>

          {/* Change avatar modal */}
          <Modal show={this.state.showAvatarModal} onHide={this.handleAvatarModal}>
            <Modal.Header closeButton>
                <Modal.Title>{labels[this.props.lang].changeAvatar}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Dropzone 
                accept='image/jpeg, image/png, image/svg'
                multiple={false}
                onDrop={acceptedFiles => this.handleNewAvatar(acceptedFiles[0])} >
                {({ getRootProps, getInputProps }) => (
                  <section>
                    <div {...getRootProps()} className="dropzone">
                      <input {...getInputProps()} />
                      <p>{this.state.newAvatar 
                          ? this.state.newAvatar.name 
                          : labels[this.props.lang].dragImage + " (.jpeg, .png, .svg)"}</p>
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
                    {labels[this.props.lang].cancel}
                  </Button>
                  <Button size="sm" disabled={this.state.newAvatar ? false : true} 
                          className="custom-button" onClick={this.handleNewAvatarConfirm}>
                    {labels[this.props.lang].confirm}
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