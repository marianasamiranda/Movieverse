import React, { Component } from 'react';
import Post from './post'

//TODO

export default class Main extends Component {
  render() {
    return (
      <div className="main">
          <div id="main-feed-title">
            FEED
          </div>
          <Post>

          </Post>
          <Post>
            
          </Post>
            
          <Post>
          </Post>

          
          <div className="feed-container" style={{display: 'flex', justifyContent: 'center',}}>
            <input type="button" className="button" value="LOAD MORE..."></input>
          </div>
          
      </div>
    )  
  }
}