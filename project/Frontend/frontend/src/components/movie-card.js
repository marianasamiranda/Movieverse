import React, { Component } from 'react'
import {Link} from 'react-router-dom'

export default class MovieCard extends Component {
  render() {
    let card = 
      <div className="text-center movie-card-container">
        <img className="movie-card" src={this.props.img} alt=""
          onError={(e) => { e.target.onerror = null; e.target.src = "https://imgplaceholder.com/228x337/dddddd/777777/fa-image" }} />
        <div className="label">
          <p id="movie-name">{this.props.title}</p>
          <p id="movie-info" className="small muted">{this.props.info}</p>
        </div>
      </div>

    if (this.props.id) {
      return (
        <Link to={'/movie?id=' + this.props.id}>
          {card}
        </Link>
      )
    }

    return card
  }
}


