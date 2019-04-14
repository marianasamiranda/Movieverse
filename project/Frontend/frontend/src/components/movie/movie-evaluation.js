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

export default class MovieEvaluation extends Component {

    constructor(props) {
        super(props);
        this.state = { watched: false, favourited: false, addedWatchlist: false };
    }

    handleWatched() {
        if (this.state.watched == false) {
            this.setState( { watched: true });
        }
        else {
            this.setState( { watched: false }) ;
        }
    }

    handleFavourited() {
        if (this.state.favourited == false) {
            this.setState( { favourited: true });
        }
        else {
            this.setState( { favourited: false }) ;
        }
    }

    handleAddedWatchlist() {
        if (this.state.addedWatchlist == false) {
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
            watchedMovie = <td class="watched" onClick={this.handleWatched.bind(this)}>
                <Image src={ watched } width="30em" style={{ 'margin-right': '0.5em'}} />
                Watched!
            </td>
        }
        else {
            watchedMovie = <td class="watched" onClick={this.handleWatched.bind(this)}>
                <Image src={ watchedDisabled } width="30em" style={{ 'margin-right': '0.5em'}} />
                Mark watched
            </td>
        }

        if(this.state.favourited == true) {
            favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
            <Image src={ favourite } width="30em" style={{ 'margin-right': '0.5em'}} />
            Added to favourites!
        </td>
        }
        else {
            favouritedMovie = <td className="favourited" onClick={this.handleFavourited.bind(this)}>
                <Image src={ favouriteDisabled } width="30em" style={{ 'margin-right': '0.5em'}} />
                Add to favourites
            </td>
        }

        if(this.state.addedWatchlist == true) {
            addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
                <Image src={ watchlist } width="30em" style={{ 'margin-right': '0.5em'}} />
                Added to watchlist!
            </td>
        }
        else {
            addedWatchlist = <td className="addedToWatchlist" onClick={this.handleAddedWatchlist.bind(this)}>
                <Image src={ watchlistDisabled } width="30em" style={{ 'margin-right': '0.5em'}} />
                Add to watchlist
            </td>
        }
        return <div className="evaluation">
            <table>
            <tr>
                { watchedMovie }
                <td rowspan="3">
                <div className="rating">
                    <StarRatingComponent 
                    name="rate1" 
                    starCount={5}
                    value="1"
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
            </table>
        </div>
    }
}
