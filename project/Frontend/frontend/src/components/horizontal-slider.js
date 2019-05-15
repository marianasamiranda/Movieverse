import React, { Component } from 'react';
import Image from 'react-bootstrap/Image'

export default class HorizontalSlider extends Component {
  
  constructor(props) {
    super(props)

    this.state = {
      content: this.props.content,
      moreLink: this.props.more,
      loadMore: this.props.loadMore
    }
  }


  render() {
    console.log(this.state.content)
    var content = this.state.content.map((entry) => <div class="hitem">
      <a href={entry.href} target="_blank">
        <Image className="movieThumbnail" src={entry.src} />
      </a>
    </div>);
    return <div class="hcontainer">
      { content }
      {
        this.state.loadMore ? (
          <div class="hitem">
          <a href={this.state.moreLink} class="showMoreMedia vertical-align">
            Show More&#160;<i class="fas fa-plus-circle"></i>
          </a>
        </div>
        ) : ""
      }

    </div>
  }
}