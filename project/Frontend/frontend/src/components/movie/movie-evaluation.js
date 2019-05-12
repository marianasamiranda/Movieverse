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
import { backend } from '../../var'
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
    if (this.state.watched == false & this.state.addedWatchlist == true) {
      this.setState( { watched: true, addedWatchlist: false });
    }
    else if (this.state.watched == false) {
      this.setState( { watched: true });
    }
    else {
      if (this.state.favourited == true) {
        this.setState( { favourited: false } )
      }
      this.setState( { watched: false }) ;
    }
  }

  handleFavourited() {
    if (this.state.favourited == false) {
      if(this.state.watched == false) {
        this.setState( {watched: true} );
      }
      if(this.state.addedWatchlist == true) {
        this.setState( { addedWatchlist: false })
      }

      this.setState( { favourited: true });
    }
    else {
      this.setState( { favourited: false }) ;
    }
  }

  handleAddedWatchlist() {
    if (this.state.addedWatchlist == false) {
      if(this.state.watched == true) {
        this.setState( {watched: false} );
      }
      if(this.state.favourited == true) {
        this.setState( { favourited: false })
      }
      this.setState( { addedWatchlist: true });
    }
    else {
      this.setState( { addedWatchlist: false }) ;
    }
  }

  render() {
    let watchedMovie;
    let favouritedMovie;
    let addedWatchlist;
    
    if(this.state.watched == true) {
        watchedMovie = <td className="watched" onClick={this.handleWatched.bind(this)}>
          <Image src={ watched } width="30em" style={{ 'marginRight': '0.5em'}} />
          Watched!
        </td>
    }
    else {
      watchedMovie = <td className="watched" onClick={this.handleWatched.bind(this)}>
        <Image src={ watchedDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        Mark watched
      </td>
    }
    
    if(this.state.favourited == true) {
      favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favourite } width="30em" style={{ 'marginRight': '0.5em'}} />
        Added to favourites!
      </td>
    }
    else {
      favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favouriteDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        Add to favourites
      </td>
    }

    if(this.state.addedWatchlist == true) {
      addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlist } width="30em" style={{ 'marginRight': '0.5em'}} />
        Added to watchlist!
      </td>
    }
    else {
      addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlistDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        Add to watchlist
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
