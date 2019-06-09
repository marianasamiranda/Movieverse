import React, { Component } from 'react';
import Post from './post'
import { labels } from '../../var';

//TODO

export default class Main extends Component {

  constructor(props){
    super(props)
    this.state = {
      user: props.user,
      entries: props.entries
    }
  }

  render() {
    let entries = []
    
    this.state.entries.forEach(element => {
      entries.push(
        <Post
          data = {element}
        >
        </Post>
      )
    });

    return (
      <div className="main">
          <div id="main-feed-title">
            FEED
          </div>
          {entries}
          <div className="feed-container" style={{display: 'flex', justifyContent: 'center',}}>
            <input type="button" className="button" value={labels[this.props.lang].loadMore + "  ..."}></input>
          </div>
          
      </div>
    )  
  }
}