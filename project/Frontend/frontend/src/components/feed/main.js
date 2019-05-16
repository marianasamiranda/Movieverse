import React, { Component } from 'react';
import Post from './post'

//TODO

export default class Main extends Component {

  constructor(props){
    super(props)
    this.state = {
      user: props.user
    }
  }

  render() {
    return (
      <div className="main">
          <div id="main-feed-title">
            FEED
          </div>
          <Post
            user={this.state.user}
          >
          </Post>
          <Post
            user={this.state.user}
          >
          </Post> 
          <Post
            user={this.state.user}
          >
          </Post>

          
          <div className="feed-container" style={{display: 'flex', justifyContent: 'center',}}>
            <input type="button" className="button" value="LOAD MORE..."></input>
          </div>
          
      </div>
    )  
  }
}