import React, { Component } from 'react';
import StarRatingComponent from 'react-star-rating-component';
import Image from 'react-bootstrap/Image'
import watched from '../../img/watched.png'
import watchedDisabled from '../../img/watched-disabled.png'
import watchlist from '../../img/watchlist.png'
import watchlistDisabled from '../../img/watchlist-disabled.png'
import favourite from '../../img/favourite.png'
import favouriteDisabled from '../../img/favourite-disabled.png'
import '../../styles/MoviePage.css'
import { getToken } from '../../cookies'
import { backend, labels } from '../../var'
import { getCurrentDate } from '../../utils'
import Axios from 'axios';

export default class MovieEvaluation extends Component {

  constructor(props) {
    super(props);
    this.state = {
      movieId: this.props.id,
      watched: this.props.watched,
      favourited: this.props.favourited,
      addedWatchlist: this.props.watchlist
    };
  }

  handleWatched() {
    var f = {}
    if (this.state.watched === false & this.state.addedWatchlist === true) {
      this.setState( { watched: true, addedWatchlist: false });
      f = {
        'watched': true,
        'watchlist': false,
        'dateWatched': getCurrentDate()
      }
    }
    else if (this.state.watched === false) {
      this.setState( { watched: true });
      f = {
        'watched': true,
        'dateWatched': getCurrentDate()
      }
    }
    else {
      if (this.state.favourited === true) {
        this.setState( { favourited: false, watched: false } )
        f = {
          'watched': false,
          'favourited': false
        }
      }
      else {
        this.setState( { watched: false });
        f = {
          'watched': false
        }
      }
    }

    Axios.patch(backend + '/movie/' + this.state.movieId + '/me',
    f,
    { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      console.log('saved successfully')}
    ).catch((e) =>
      console.log(e)
    )
  }

  handleFavourited() {
    var f = {}
    if (this.state.favourited === false) {
      var date = getCurrentDate()
      if(this.state.watched === false) {
        this.setState( {watched: true} );
        f = {
          watched: true,
          dateWatched: date
        }
      }
      if(this.state.addedWatchlist === true) {
        this.setState( { addedWatchlist: false })
        f = {
          watched: true,
          dateWatched: date,
          addedToWatchlist: false
        }
      }

      this.setState( { favourited: true });
      f['favourited'] = true
      f['dateFavourited'] = date
    }
    else {
      this.setState( { favourited: false });
      f = {
        favourited: false
      }
    }

    Axios.patch(backend + '/movie/' + this.state.movieId + '/me',
    f,
    { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      console.log('saved successfully')}
    ).catch((e) =>
      console.log(e)
    )
  }

  handleAddedWatchlist() {
    var f = {}
    if (this.state.addedWatchlist === false) {
      if(this.state.watched === true) {
        this.setState( { watched: false } )
        f['watched'] = false
      }
      if(this.state.favourited === true) {
        this.setState( { favourited: false })
        f['favourited'] = false
      }
      f['addedToWatchlist'] = true
      this.setState( { addedWatchlist: true });
    }
    else {
      this.setState( { addedWatchlist: false }) ;
      f = {
        addedToWatchlist: false,
      }
    }

    Axios.patch(backend + '/movie/' + this.state.movieId + '/me',
    f,
    { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      console.log('saved successfully')}
    ).catch((e) =>
      console.log(e)
    )
  }

  render() {
    let watchedMovie;
    let favouritedMovie;
    let addedWatchlist;
    
    if(this.state.watched === true) {
        watchedMovie = <td className="watched" onClick={this.handleWatched.bind(this)}>
          <Image src={ watched } width="30em" style={{ 'marginRight': '0.5em'}} />
          {labels[this.props.lang].markedWatched}
        </td>
    }
    else {
      watchedMovie = <td className="watched" onClick={this.handleWatched.bind(this)}>
        <Image src={ watchedDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].markWatched}
      </td>
    }
    
    if(this.state.favourited === true) {
      favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favourite } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addedToFavourites}
      </td>
    }
    else {
      favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favouriteDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addToFavourites}
      </td>
    }

    if(this.state.addedWatchlist === true) {
      addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlist } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addedToWatchlist}
      </td>
    }
    else {
      addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlistDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addToWatchlist}
      </td>
    }
    
    return <div className="evaluation">
      <table>
        <tbody>
          <tr>
            { watchedMovie }
            <td rowSpan="3">
              <div className="rating">
                <StarRatingComponent 
                  name="rate1" 
                  starCount={5}
                  value={1}
                />
              </div>
            </td>
          </tr>
          <tr>
            { favouritedMovie }
          </tr>
          <tr>
            { addedWatchlist }
          </tr>
        </tbody>
      </table>
    </div>
  }
}
