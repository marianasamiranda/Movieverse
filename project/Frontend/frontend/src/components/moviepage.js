import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import star from '../img/star.png'
import StarRatingComponent from 'react-star-rating-component';
import MovieCard from './movie-card'
import Comment from './comment'
import '../styles/MoviePage.css'

export default class MoviePage extends Component {
  render() {
    return (
      <Container>
        <div className="movie-header">
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
                <td>Add to Favourites</td>
              </tr>
              <tr>
                <td>Add to Watchlist</td>
              </tr>
            </table>
          </div>
        </div>
        <div class="container-fluid">
          <div class="row">
            <div class="movie-content col-lg-8 order-lg-1 order-sm-2 order-2">
              <h1>Summary</h1>
              <p>In the early 18th century, England is at war with the French. Nevertheless, duck racing and pineapple eating are thriving. A frail Queen Anne occupies the throne, and her close friend, Lady Sarah, governs the country in her stead, while tending to Anne's ill health and mercurial temper. When a new servant, Abigail, arrives, her charm endears her to Sarah. Sarah takes Abigail under her wing, and Abigail sees a chance to return to her aristocratic roots.</p>
              <h1>Top Billed Cast</h1>
              <Row>
            <Col lg="3" md="3" xs="3">
              <MovieCard small img="http://placehold.it/228x337" title="Olivia Colman" info="Queen Anne" />
            </Col>
            <Col lg="3" md="3" xs="3">
              <MovieCard small img="http://placehold.it/228x337" title="Emma Stone" info="Abigail" />
            </Col>
            <Col lg="3" md="3" xs="3">
              <MovieCard small img="http://placehold.it/228x337" title="Rachel Weisz" info="Lady Sarah" />
            </Col>
            <Col lg="3" md="3" xs="3">
              <MovieCard small img="http://placehold.it/228x337" title="Nicholas Hoult" info="Harley" />
            </Col>
          </Row>
              <h1>Discussion</h1>
              <Comment profilepic="https://via.placeholder.com/70" author="George W. Bush" time="1h ago" content="i took my friend to see this and a week later she said it affected her so much that she cried about it for an entire therapy session, which is probably yorgos’s ultimate artistic goal and would mean more to him than any of the major directing awards that he should be earning!" likes={ 10 }/>

              <Comment profilepic="https://via.placeholder.com/70" author="Woody Allen" time="4h ago" content="i think i reached level 10 surrealism in seeing a yorgos lanthimos movie at 10:30pm when everything else around me was closed and when i got out it was all so dark and not a soul around me except the couple who also saw this in my theater. no one said anything but i knew we were all silently afraid yorgos was crawling on the ceiling above us and was about to unleash a pot of lobsters with knives taped to them on our heads." likes={ 65 }/>

              <Comment profilepic="https://via.placeholder.com/70" author="Emma Stone" time="10h ago" content="oh my god. you guys are sure as hell aren’t ready for this movie. It’s fucking bonkers. It’s this year’s the lobster for lanthimos. I deeply fell in love with the cinematography, the dark comedic script and the costume design especially for me who loves these kind of films. rachel is fantastic, olivia is amazing and for emma, my queen deserves a oscar nom because I feel like this is a much better performance than la la land. I can’t not wait to see this again with a much bigger crowd." likes={ 94 }/>

              {/* TODO: INSERIR AQUI LOAD MORE DO DANIEL */}
            </div>
            <div class="col-lg-4 order-lg-2 order-sm-1 order-1">
              <div class="sidebar">
                <h6>Original Language</h6>
                <h6>Runtime</h6>
                <p>81m</p>
                <h6>Genres</h6>
                  <div class="movie-genre">
                    <ul>
                      <li><a href="#">Animation</a></li>
                      <li><a href="#">Comedy</a></li>
                      <li><a href="#">Family</a></li>         
                    </ul>
                  </div>
                <h6>Prod. Companies</h6>
                <p>Pixar
                  <br />
                United States of America</p>
              </div>
            </div>
          </div>
        </div>
      </Container>
    )
  }
}