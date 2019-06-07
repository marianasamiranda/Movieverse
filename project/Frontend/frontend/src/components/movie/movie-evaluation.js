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
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Axios from 'axios';

const movieEval = {
  1: "Bad!",
  2: "Meh!",
  3: "Ok!",
  4: "Good!",
  5: "Wow!"
}

export default class MovieEvaluation extends Component {

  constructor(props) {
    super(props);
    this.state = {
      movieId: this.props.id,
      watched: this.props.watched,
      favourited: this.props.favourited,
      addedWatchlist: this.props.watchlist,
      rating: this.props.rating,
      message: (this.props.rating === undefined || this.props.rating === 0) ? 'Rate the movie!' : movieEval[this.props.rating]
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
        this.setState({
          watched: false,
          rating: 0,
          message: 'Rate the movie!'
        });
        f = {
          'watched': false,
          'rating': null
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
    if(this.state.addedWatchlist === false) {
      if(this.state.watched === true) {
        this.setState( { watched: false } )
        f['watched'] = false
      }
      if(this.state.favourited === true) {
        this.setState( { favourited: false })
        f['favourited'] = false
      }
      if(this.state.rating !== 0) {
        this.setState({
          rating: 0,
          message: 'Rate the movie!'
        })
        f['rating'] = null
      }
      f['addedToWatchlist'] = true
      this.setState({
        addedWatchlist: true
      });
    }
    else {
      this.setState({
        addedWatchlist: false
      }) ;
      f = {
        addedToWatchlist: false
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

  onStarClick(nextValue, prevValue, name) {
    var date = getCurrentDate()
    var f = {}
    if(this.state.watched === false) {
      this.setState({
        watched: true
      });
      f = {
        watched: true,
        dateWatched: date
      }
    }
    if(this.state.addedWatchlist === true) {
      this.setState({
        addedWatchlist: false
      })
      f = {
        watched: true,
        dateWatched: date,
        addedToWatchlist: false
      }
    }
    f['rating'] = nextValue
    this.setState({
      rating: nextValue,
      message: movieEval[nextValue]
    });

    Axios.patch(backend + '/movie/' + this.state.movieId + '/me',
    f,
    { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      console.log('saved successfully')}
    ).catch((e) =>
      console.log(e)
    )
  }

  onStarHover(nextValue, prevValue, name) {
    document.getElementById("label-onrate").innerHTML = movieEval[nextValue]; 

  }

  onStarHoverOut(nextValue, prevValue, name) {
    document.getElementById("label-onrate").innerHTML = this.state.message;
  }


  starReset() {
    var f = {}
    this.setState({
      rating: 0,
      message: 'Rate the movie!'
    })

    f['rating'] = null

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
        watchedMovie = <span className="watched" onClick={this.handleWatched.bind(this)}>
          <Image src={ watched } width="30em" style={{ 'marginRight': '0.5em'}} />
          {labels[this.props.lang].markedWatched}
        </span>
    }
    else {
      watchedMovie = <span className="watched" onClick={this.handleWatched.bind(this)}>
        <Image src={ watchedDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].markWatched}
      </span>
    }
    
    if(this.state.favourited === true) {
      favouritedMovie = <span className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favourite } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addedToFavourites}
      </span>
    }
    else {
      favouritedMovie = <span className="favourited" onClick={this.handleFavourited.bind(this)}>
        <Image src={ favouriteDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addToFavourites}
      </span>
    }

    if(this.state.addedWatchlist === true) {
      addedWatchlist = <span className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlist } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addedToWatchlist}
      </span>
    }
    else {
      addedWatchlist = <span className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
        <Image src={ watchlistDisabled } width="30em" style={{ 'marginRight': '0.5em'}} />
        {labels[this.props.lang].addToWatchlist}
      </span>
    }
    return <div>
      <Row className="eval-wrapper">
        <Col lg="6" md="5" sm="12">
          <div style={{'lineHeight':'2.2em'}}>
            { watchedMovie }
          </div>
          <div style={{'lineHeight':'2.2em'}}>
            { favouritedMovie }
          </div>      
          <div style={{'lineHeight':'2.2em'}}>
            { addedWatchlist }
          </div>
        </Col>
        <Col lg="6" md="5" sm="12">
          <div className="rating text-center" style={{ 'marginTop': '0.2em' }}>
            <StarRatingComponent
              name="rate1"
              value={ this.state.rating }
              starCount={ 5 }
              onStarClick={ this.onStarClick.bind(this) }
              onStarHover={ this.onStarHover.bind(this) }
              onStarHoverOut={ this.onStarHoverOut.bind(this) }
            />
            <i onClick={ this.starReset.bind(this)} className="fas fa-times fa-xs" style={{'fontSize': '1rem', 'position': 'relative', 'bottom': '0.7rem', 'left': '0.5em'}}></i>
            <p><span id="label-onrate" className="onrate">{ this.state.message }</span></p>
          </div>
        </Col>
      </Row>
    </div>
  }
}
