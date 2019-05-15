import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import Flag from './flag';

export default class MovieCard extends Component {
  render() {
    let card = 
      <div className="text-center movie-card-container">
        <img className="movie-card" src={this.props.img} alt=""
          onError={(e) => { e.target.onerror = null; e.target.src = "https://imgplaceholder.com/200x283/dddddd/777777/fa-image" }} />
        <div className="label">
          <p id="movie-name">{this.props.title}</p>
          <p id="movie-info" className="small muted">{this.props.info}</p>
          {this.props.country ? <Flag country={this.props.country} /> : ""}
        </div>
      </div>

    if (this.props.id) {
      return (  
        <Link to={'/movie/' + this.props.id}>
          {card}
        </Link>
      )
    }

    else if (this.props.member) {
      return (
        <Link to={'/member/' + this.props.member}>
          {card}
        </Link>
      )
    }

    else if (this.props.user) {
      return (
        <Link to={'/u/' + this.props.title}>
          {card}
        </Link>
      )
    }

    else
      return card
  }
}


