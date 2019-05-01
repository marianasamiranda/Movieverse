import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import star from '../../img/star.png'
import MovieCard from '../movie-card'
import DiscussionBox from './discussion-box'
import MovieEvaluation from './movie-evaluation'
import Axios from 'axios';
import Loading from '../aux_pages/loading'
import {backend} from '../../var'
import Language from '../language'
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import '../../styles/MoviePage.css'
import HorizontalSlider from '../horizontal-slider';

export default class MoviePage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      windowSize: window.innerWidth,
      movie: undefined
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
    const { movie_id } = this.props.match.params.id

    return Axios.get(backend + '/movie/' + movie_id)
      .then(x => {
        const movie_info = x.data;
        this.setState({ movie: movie_info });
      })
  }

  componentWillUnmount() {
    window.removeEventListener("resize", this.handleResize);
  }

  render() {

    // TODO: Fazer isto de outra forma
    const videos = [
      {"href": "https://www.youtube.com/watch?v=SYb-wkehT1g", "src": "http://img.youtube.com/vi/SYb-wkehT1g/0.jpg"},
      {"href": "https://www.youtube.com/watch?v=SYb-wkehT1g", "src": "http://img.youtube.com/vi/SYb-wkehT1g/0.jpg"},
      {"href": "https://www.youtube.com/watch?v=SYb-wkehT1g", "src": "http://img.youtube.com/vi/SYb-wkehT1g/0.jpg"},
      {"href": "https://www.youtube.com/watch?v=SYb-wkehT1g", "src": "http://img.youtube.com/vi/SYb-wkehT1g/0.jpg"}
    ]

    const backdrops = [
      {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
      {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
      {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
      {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"}
    ]

    if (!this.state.movie) {
      return (
        <Loading />
      )
    }

    let headerTitle;
    if(this.state.windowSize < 768) {
      headerTitle = <div className="movie-title-div">
        <span>{this.state.movie.name} </span>
        <span><Image src="https://m.media-amazon.com/images/G/01/IMDb/BG_rectangle._CB1509060989_SY230_SX307_AL_.png" height="30vh" /></span>
        <br />
        <Image src={star} height="30vh" /> 4.1
        <hr />
      </div>
    }
    else {
      headerTitle = <div className="movie-title-div d-flex">
          <div className="mr-auto p-2">
            <span>{this.state.movie.name} </span>
            <span><Image src="https://m.media-amazon.com/images/G/01/IMDb/BG_rectangle._CB1509060989_SY230_SX307_AL_.png" height="30vh" /></span>
          </div>
          <div className="p-2">
            <Image src={star} height="30vh" />
          </div>
          <div className="p-2">
            4.1
          </div>
        </div>
    }
    return (
      <Container>
        <div className="movie-header">
          <div className="backdrop" style={{'background': 'url(https://image.tmdb.org/t/p/original/l7iBeeotCkkJeT5NfGD3yZ6rvSs.jpg) no-repeat center center', 'backgroundSize': 'cover'}}></div>
          <div className="poster">
            <Image src="https://image.tmdb.org/t/p/w600_and_h900_bestv2/cwBq0onfmeilU5xgqNNjJAMPfpw.jpg" />
          </div>
          { headerTitle }
          <MovieEvaluation />
        </div>
        <div className="container-fluid">
          <div className="row">
            <div className="movie-content col-lg-8 order-lg-1 order-sm-2 order-2">
              <h1>Summary</h1>
                <p>{this.state.movie.summary}</p>
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
                  <h1 style={{ 'display': 'inline-block', 'border': '0', 'paddingBottom': 0, 'marginBottom': 0 }}
                  >Media</h1>
                  <Tab>Videos</Tab>
                  <Tab>Backdrops</Tab>
                </TabList>
                <TabPanel>
                  <HorizontalSlider more="/media" content={videos}/>
                </TabPanel>
                <TabPanel>
                  <HorizontalSlider more="/media" content={backdrops}/>   
                </TabPanel>
              </Tabs>
              <h1>Discussion</h1>
              <DiscussionBox />
            </div>
            <div className="col-lg-4 order-lg-2 order-sm-1 order-1">
              <div className="sidebar">
                <h6>Original Language</h6>
                  <Language language="en" />
                <h6>Runtime</h6>
                  <p>81m</p>
                <h6>Genres</h6>
                <div className="movie-genre">
                  <ul>
                    <li><a href="#">Animation</a></li>
                    <li><a href="#">Comedy</a></li>
                    <li><a href="#">Family</a></li>         
                  </ul>
                </div>
                <h6>Prod. Companies</h6>
                <p>
                  Pixar
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