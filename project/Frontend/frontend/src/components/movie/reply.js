import React, { Component } from 'react';
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import { getToken } from '../../cookies'
import { backend, labels } from '../../var'
import Axios from 'axios';
import { Link } from 'react-router-dom'
import OopsModal from '../aux_pages/oops-modal'
import '../../styles/Comment.css'

export default class Reply extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      noAuth: this.props.noAuth,
      likes: this.props.likes,
      liked: this.props.isLiked
    };
  }

  handleLike() {

    if(this.state.noAuth) {
      this.setState({
        showModal: true
      })

      return;
    }

    let token = getToken()

    if(this.state.liked === false) {

      Axios.post(backend + '/movie/comment/' + this.props.id + '/like',
        {},
        { headers: { Authorization: "Bearer " + token } })
      .then((response) =>
        this.setState({
          likes: this.state.likes + 1,
          liked: true
        })
      )
    }
    else {
      
      Axios.post(backend + '/movie/comment/' + this.props.id + '/dislike',
        {},
        { headers: { Authorization: "Bearer " + token } })
      .then((response) =>
        this.setState({
          likes: this.state.likes - 1,
          liked: false
        })
      )
    }
  }

  handleCloseNotLoggedInModal() {
    this.setState({
      showModal: false
    })
  }

  render() {
    let message;

    if(this.state.noAuth) {
      message = <p>
        { labels[this.props.lang].youNeed } <a href="/">{ labels[this.props.lang].registeredOrLoggedIn }</a> { labels[this.props.lang].toBeAbleLike }
      </p>
    }

    return <>
      <OopsModal showModal={ this.state.showModal } handleClose={this.handleCloseNotLoggedInModal.bind(this)} message={ message } />
      <div className="reply">
        <Row>
          <Col style={{'maxWidth': '65px'}}>
            <Link to={`/u/${this.props.author}`}>
              <Image className="reply-pic" src={ this.props.profilepic } />
            </Link>
          </Col>
          <Col>
            <Link to={`/u/${this.props.author}`} style={{ 'color': 'black', 'textDecoration': 'none' }}>
              <strong>{ this.props.author } </strong>
            </Link>
            <div>{ this.props.content }</div>
            <div className="reply-info">
              <div className="reply-time">
                <i className="far fa-clock"></i> { this.props.time }
              </div>
              |
              { this.state.liked &&
                <div className="reply-likes" onClick={this.handleLike.bind(this)}><i className="fas fa-heart"></i> { this.state.likes }</div>
              }
              { !this.state.liked &&
                <div className="reply-likes" onClick={this.handleLike.bind(this)}><i className="far fa-heart"></i> { this.state.likes }</div>
              }
            </div>
          </Col>
        </Row>
      </div>
    </>  
  }
}
