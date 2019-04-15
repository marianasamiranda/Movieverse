import React, { Component } from 'react';
import Image from 'react-bootstrap/Image'
import '../../styles/Comment.css';

export default class Comment extends Component {
  constructor(props) {
    super(props)
    this.state = { likes: props.likes, liked: false };
  }

  handleLike() {
    if (this.state.liked == false) {
      this.setState( { likes: this.state.likes + 1, liked: true });
    }
    else {
      this.setState( { likes: this.state.likes - 1, liked: false }) ;
    }
  }

  render() {
    let liked_div;

    if(this.state.liked) {
      liked_div = <div class="liked-button"><i class="fas fa-heart"></i> { this.state.likes }</div>;
    }
    else {
      liked_div = <div class="like-button"><i class="far fa-heart"> </i> { this.state.likes }</div>;
    }
    return <div className="comment-container">
      <div className="info d-flex">
        <Image className="profile-pic p-2" src={this.props.profilepic} />
          <div className="info-author p-2">
            by {this.props.author}
            <br />
            <i class="far fa-clock"></i> {this.props.time}
          </div>
      </div>
      <p>
        {this.props.content}
      </p>
      <p className="likes" onClick={this.handleLike.bind(this)}>
        { liked_div } 
      </p>
    </div>
    }
}