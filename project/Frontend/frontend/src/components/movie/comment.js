import React, { Component } from 'react';
import Image from 'react-bootstrap/Image';
import Reply from './reply'
import '../../styles/Comment.css';

export default class Comment extends Component {
  constructor(props) {
    super(props);
    this.state = {
      likes: props.likes,
      liked: false,
      // showing_replies: false,
      // display_replies: [],
      replies: props.replies
    };
  }

  handleLike() {
    if (this.state.liked === false) {
      this.setState( { likes: this.state.likes + 1, liked: true });
    }
    else {
      this.setState( { likes: this.state.likes - 1, liked: false }) ;
    }
  }

  handleReply() {
    if (this.state.showing_replies === false) {
      this.setState( { display_replies: this.props.replies.map((reply) =>
        <Reply time={reply.time} content={reply.content} likes={reply.likes} />
      ), showing_replies: true })}
    else {
      // se estiver a mostrar replies fechar
      this.setState( {display_replies: '', showing_replies: false })
    }

  }

  render() {
    let liked_div;
    
    if(this.state.liked) {
      liked_div = <div className="liked-button"><i className="fas fa-heart"></i> { this.state.likes }</div>;
    }
    else {
      liked_div = <div className="like-button"><i className="far fa-heart"> </i> { this.state.likes }</div>;
    }
    return <div className="comment" style={{ 'marginBottom': '30px'}}>
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
          { liked_div } 
        </div>
        {/*<div className="replies" onClick={this.handleReply.bind(this)}>
          <i className="far fa-comment-dots"></i> {this.props.replies.length }
  </div>*/}
      </div>
      {/*<div className="replies-container">
        { this.state.display_replies }
  </div>*/}
    </div>
    }
}