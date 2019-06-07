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

export default class MovieEvaluation extends Component {

  constructor(props) {
    super(props);
    this.state = {
      movieId: this.props.id,
      watched: this.props.watched,
      favourited: this.props.favourited,
      addedWatchlist: this.props.watchlist,
      rating: 0,
      message: this.props.rating === 0 ? 'Rate the movie!' : 'cenas'
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

  onStarClick(nextValue, prevValue, name) {
    this.setState({rating: nextValue});
    if(nextValue === 1) {
      this.setState({message: 'Bad!'});
    }
    else if(nextValue === 2) {
      this.setState({message: 'Meh!'});
    }
    else if(nextValue === 3) {
      this.setState({message: 'Ok!'});
    }
    else if(nextValue === 4) {
      this.setState({message: 'Good!'});
    }
    else if(nextValue === 5) {
      this.setState({message: 'Wow!'});
    }
  }

  onStarHover(nextValue, prevValue, name) {
    if(nextValue === 1) {
      document.getElementById("label-onrate").innerHTML = "Bad!"; 
    }
    else if(nextValue === 2) {
      document.getElementById("label-onrate").innerHTML = "Meh!"; 
    }
    else if(nextValue === 3) {
      document.getElementById("label-onrate").innerHTML = "Ok!"; 
    }
    else if(nextValue === 4) {
      document.getElementById("label-onrate").innerHTML = "Good!"; 
    }
    else if(nextValue === 5) {
      document.getElementById("label-onrate").innerHTML = "Wow!"; 
    }
  }

  onStarHoverOut(nextValue, prevValue, name) {
    document.getElementById("label-onrate").innerHTML = this.state.message; 
  }


  starReset() {
    this.setState({
      rating: 0,
      message: 'Rate the movie!'
    })
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
      <Row className="eval-wrapper">
        <Col className="col-lg-7 col-md-5">
          <div style={{'line-height':'2.2em'}}>
            { watchedMovie }
          </div>
          <div style={{'line-height':'2.2em'}}>
            { favouritedMovie }
          </div>      
          <div style={{'line-height':'2.2em'}}>
            { addedWatchlist }
          </div>
        </Col>
        <Col className="col-lg-5 col-md-5">
          <div className="rating text-center">
            <StarRatingComponent
              name="cenas" /* name of the radio input, it is required */
              value={this.state.rating} /* number of selected icon (`0` - none, `1` - first) */
              starCount={5} /* number of icons in rating, default `5` */
              onStarClick={this.onStarClick.bind(this)} /* on icon click handler */
              onStarHover={this.onStarHover.bind(this)}
              onStarHoverOut={this.onStarHoverOut.bind(this)}
            />
            <i onClick={this.starReset.bind(this)} className="fas fa-times fa-xs" style={{'font-size': '1rem', 'position': 'relative', 'bottom': '0.7rem', 'left': '0.5em'}}></i>
            <p><span id="label-onrate" className="onrate">{ this.state.message }</span></p>
          </div>
        </Col>
      </Row>
    </div>
  }
}
