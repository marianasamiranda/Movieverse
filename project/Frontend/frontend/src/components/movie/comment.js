import React, { Component } from 'react';
import Image from 'react-bootstrap/Image';
import Reply from './reply'
import Axios from 'axios';
import ResizableTextarea from './resizable-text-area'
import moment from 'moment';
import { getCurrentDate } from '../../utils';
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
      numberReplies: this.props.numberReplies,
      showingReplies: false,
      moreReplies: false,
      currentPage: 0,
      replies: undefined
    }
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
      
      Axios.post(backend + '/movie/comment/' + this.props.id + '/dislike',
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

  getComment = (newComment) => {

    var f = {}

    f['message'] = newComment
    f['date'] = getCurrentDate()

    let self=this

    Axios.post(backend + '/movie/comment/' + this.props.id + '/reply',
      f,
      { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      
      let element = {
        'id': response.data.id,
        'userAvatar': response.data.userAvatar,
        'username': response.data.username,
        'date': response.data.date,
        'content': response.data.content,
        'likes': response.data.likes,
        'isLiked': false
      }

      self.setState(prevState => ({
        replies: [...prevState.replies, element],
        numberReplies: self.state.numberReplies + 1
      }))
    }).catch((e) =>
      console.log(e)
    )

    console.log(self.state.numberReplies)
  }

  getMoreComments(page) {

    var header = {}
    var token = getToken()

    if(token !== undefined) {
      header = { headers: { Authorization: "Bearer " + token } }
    }

    let self = this;

    Axios.get(backend + '/movie/comment/' + self.props.id + '/replies?page=' + page,
      header)
    .then(function(response) {
      
      let newReplies = [];

      response.data.replies.forEach(function(reply) {
        reply["date"] = moment(reply.date).format("YYYY-MM-DD HH:mm")
        newReplies = [reply].concat(newReplies);
      });

      self.setState({
        replies: self.state.replies === undefined ? newReplies : newReplies.concat(self.state.replies),
        currentPage: self.state.currentPage + 1,
        moreReplies: response.data.moreReplies,
        showingReplies: true
      });
    }
    ).catch((e) =>
      console.log(e.response)
    )
  }

  handleReply() {
    if (this.state.showingReplies === false && this.state.replies === undefined) {
      this.getMoreComments(0);
    }
    else if (this.state.showingReplies === false) {
      this.setState({
        showingReplies: true
      })
    }
    else {
      // se estiver a mostrar replies fechar
      this.setState({
        showingReplies: false
      })
    }
  }

  handleCloseNotLoggedInModal() {
    this.setState({
      showModal: false
    })
  }
  
  handleShowMore() {
    this.getMoreComments(this.state.currentPage);

    this.forceUpdate()
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
          <div className="replies" onClick={this.handleReply.bind(this)}>
            <i className="far fa-comment-dots"></i> { this.state.numberReplies }
          </div>
        </div>
        { this.state.showingReplies && 
          <div className="replies-container">
          { this.state.replies.length !== this.state.numberReplies &&
            <div className="reply text-center">
              <div className="show-more" onClick={this.handleShowMore.bind(this)}>Show more...</div>
            </div>
          }
          {
            this.state.replies.map(reply => {
              return (
                <Reply key={ reply.id } id={ reply.id } profilepic={ `/avatars/` + reply.userAvatar } author={ reply.username } time={ reply.date } content={ reply.content } likes={ reply.likes } isLiked={ reply.isLiked } noAuth={ this.state.noAuth } lang={ this.props.lang } />
              )}
            )
          }
          { !this.state.noAuth &&
            <div className="reply">
              <ResizableTextarea noAuth={ this.state.noAuth } callBackFromParent={ this.getComment } lang={this.props.lang} />
            </div>
          }
      </div>
      }
      </div>
    </>
  }
}