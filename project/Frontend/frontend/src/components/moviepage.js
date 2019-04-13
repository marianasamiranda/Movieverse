import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image'
import '../styles/MoviePage.css'
import star from '../img/star.png'
import StarRatingComponent from 'react-star-rating-component';

export default class MoviePage extends Component {
  render() {
    return (
      <Container>
        <div className="backdrop" style={{'background': 'url(https://image.tmdb.org/t/p/original/l7iBeeotCkkJeT5NfGD3yZ6rvSs.jpg) no-repeat center center', 'background-size': 'cover'}}></div>
        <div className="poster">
          <Image src="https://image.tmdb.org/t/p/w600_and_h900_bestv2/cwBq0onfmeilU5xgqNNjJAMPfpw.jpg" />
        </div>
        <div className="movie-title-div d-flex">
          <div class="mr-auto p-2">
            <span>The Favourite </span>
            <span><Image src="https://m.media-amazon.com/images/G/01/IMDb/BG_rectangle._CB1509060989_SY230_SX307_AL_.png" height="30vh" /></span>
          </div>
          <div class="p-2">
            <Image src={star} height="30vh" />
          </div>
          <div class="p-2">
            4.1
          </div>
        </div>
        <div className="evaluation">
        <table>
          <tr>
            <td>Mark Watched</td>
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
            <td>Add to Favourite</td>
            </tr>
          <tr>
            <td>Add to Watchlist</td>
          </tr>
        </table>
        </div>
      </Container>
    )
  }
}