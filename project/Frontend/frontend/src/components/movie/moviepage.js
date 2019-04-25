import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import star from '../../img/star.png'
import MovieCard from '../movie-card'
import DiscussionBox from './discussion-box'
import MovieEvaluation from './movie-evaluation'
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import '../../styles/MoviePage.css'

export default class MoviePage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      windowSize: window.innerWidth,
    };
  }

  handleResize = e => {
    const windowSize = window.innerWidth;
    this.setState(prevState => {
      return {
        windowSize
      };
    });
  };

  componentDidMount() {
    window.addEventListener("resize", this.handleResize);
  }

  componentWillUnmount() {
    window.removeEventListener("resize", this.handleResize);
  }

  render() {
    let headerTitle;
    if(this.state.windowSize < 768) {
      headerTitle = <div class="movie-title-div">
        <span>The Favourite </span>
        <span><Image src="https://m.media-amazon.com/images/G/01/IMDb/BG_rectangle._CB1509060989_SY230_SX307_AL_.png" height="30vh" /></span>
        <br />
        <Image src={star} height="30vh" /> 4.1
        <hr />
      </div>
    }
    else {
      headerTitle = <div className="movie-title-div d-flex">
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
    }
    return (
      <Container>
        <div className="movie-header">
          <div className="backdrop" style={{'background': 'url(https://image.tmdb.org/t/p/original/l7iBeeotCkkJeT5NfGD3yZ6rvSs.jpg) no-repeat center center', 'background-size': 'cover'}}></div>
          <div className="poster">
            <Image src="https://image.tmdb.org/t/p/w600_and_h900_bestv2/cwBq0onfmeilU5xgqNNjJAMPfpw.jpg" />
          </div>
          { headerTitle }
          <MovieEvaluation />
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
              <Tabs>
                <TabList>
                  <h1 style={{ 'display': 'inline-block', 'border': '0', 'padding-bottom': 0, 'margin-bottom': 0 }}
                  >Media</h1>
                  <Tab>Videos</Tab>
                  <Tab>Backdrops</Tab>
                </TabList>
                <TabPanel>
                  <h2>...</h2>
                </TabPanel>
                <TabPanel>
                  <h2>...</h2>
                </TabPanel>
              </Tabs>
              <h1>Discussion</h1>
              <DiscussionBox />
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
                  United States of America
                </p>
              </div>
            </div>
          </div>
        </div>  
      </Container>
    )
  }
}