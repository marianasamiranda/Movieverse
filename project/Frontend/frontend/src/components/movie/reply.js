import React, { Component } from 'react';
import Image from 'react-bootstrap/Image'
import '../../styles/Comment.css'

export default class Reply extends Component {
  constructor(props) {
    super(props);
    this.state = {
      likes: props.likes,
      liked: false
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

  render() {
    let liked_div;

    if(this.state.liked) {
      liked_div = <div className="reply-likes" onClick={this.handleLike.bind(this)}><i class="fas fa-heart"></i> { this.state.likes }</div>
    }
    else {
      liked_div = <div className="reply-likes" onClick={this.handleLike.bind(this)}><i class="far fa-heart"></i> { this.state.likes }</div>;
    }
    return <div className="reply">
      <div class="row">
        <div class="col" style={{'max-width': '65px'}}>
          <Image className="reply-pic" src="https://via.placeholder.com/50" />
        </div>
        <div class="col">
          {this.props.content}
          <div className="reply-info">
            <div className="reply-time">
              { this.props.time }
            </div>
            |
            {liked_div}
          </div>
        </div>
      </div>
  </div>
    }
}