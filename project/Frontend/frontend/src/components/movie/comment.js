import React, { Component } from 'react';
import Image from 'react-bootstrap/Image';
import Reply from './reply'
import Axios from 'axios';
import { getToken } from '../../cookies'
import { backend, labels } from '../../var'
import OopsModal from '../aux_pages/oops-modal'
import '../../styles/Comment.css';

export default class Comment extends Component {
  constructor(props) {
    super(props);

    this.state = {
      noAuth: this.props.noAuth,
      showModal: false,
      likes: this.props.likes,
      liked: this.props.isLiked,
      // showing_replies: false,
      // display_replies: [],
      replies: this.props.replies
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

      Axios.post(backend + '/comment/' + this.props.id + '/like',
        {},
        { headers: { Authorization: "Bearer " + token } })
      .then((response) =>
        console.log(response)
      ).catch((e) =>
        console.log(e.response)
      )
      this.setState({
        likes: this.state.likes + 1,
        liked: true
      });
    }
    else {
      
      Axios.post(backend + '/comment/' + this.props.id + '/dislike',
        {},
        { headers: { Authorization: "Bearer " + token } })
      .then((response) =>
        console.log(response)
      ).catch((e) =>
        console.log(e.response)
      )
      this.setState({
        likes: this.state.likes - 1,
        liked: false
      });
    }
  }

  handleReply() {
    if (this.state.showing_replies === false) {
      this.setState( { display_replies: this.props.replies.map((reply) =>
        <Reply time={reply.time} content={reply.content} likes={reply.likes} />
      ), showing_replies: true })}
    else {
      // se estiver a mostrar replies fechar
      this.setState( {
        display_replies: '',
        showing_replies: false
      })
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
      <OopsModal showModal={this.state.showModal} handleClose={this.handleCloseNotLoggedInModal.bind(this)} message={ message } />
      <div className="comment" style={{ 'marginBottom': '30px'}}>
        <div className="comment-container">
          <div className="info d-flex">
            <Image className="profile-pic p-2" src={this.props.profilepic} />
            <div className="info-author p-2">
              by {this.props.author}
              <br />
              <i className="far fa-clock"></i> {this.props.time}
            </div>
          </div>
          <p>
            {this.props.content}
          </p>
          <div className="likes" onClick={this.handleLike.bind(this)}>
            { this.state.liked &&
              <div className="liked-button"><i className="fas fa-heart"></i> { this.state.likes }</div>
            }
            { !this.state.liked && 
              <div className="like-button"><i className="far fa-heart"> </i> { this.state.likes }</div>
            } 
          </div>
          {/*<div className="replies" onClick={this.handleReply.bind(this)}>
            <i className="far fa-comment-dots"></i> {this.props.replies.length }
    </div>*/}
        </div>
        {/*<div className="replies-container">
          { this.state.display_replies }
    </div>*/}
      </div>
    </>
  }
}