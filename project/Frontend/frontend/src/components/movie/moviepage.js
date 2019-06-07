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
import { backend, labels } from '../../var'
import Language from '../language'
import { getToken } from '../../cookies'
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import HorizontalSlider from '../horizontal-slider';
import '../../styles/MoviePage.css'

export default class MoviePage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      windowSize: window.innerWidth,
      movie: undefined,
      watched: undefined,
      favourited: undefined,
      watchlist: undefined,
      rating: undefined
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

    const token = getToken()

    var movieInfo
    var originalURL = null
    var source = null

    Axios.get(backend + '/movie/' + this.props.match.params.id)
      .then(x => {
        document.title = x.data.name + " | Movieverse"
        movieInfo = x.data;

        var backdrops = []
        var videos = []
        var posters = []

        if(x.data.backdrops) {
          for(const url of x.data.backdrops) {
            var backdropId = url.split(/[\/.]/)[1];
            originalURL = "https://image.tmdb.org/t/p/original/" + backdropId + ".jpg"
            
            source = "https://image.tmdb.org/t/p/w500_and_h282_face/" + backdropId + ".jpg"
            backdrops.push({
              "href": originalURL,
              "src": source
            })
          }
        }

        if(x.data.videos) {
          for(const videoId of x.data.videos) {
            originalURL = "https://www.youtube.com/watch?v=" + videoId
            source = "http://img.youtube.com/vi/" + videoId + "/0.jpg";
            videos.push({
              "href": originalURL,
              "src": source
            })
          }
        }

        if(x.data.posters) {
          for(const url of x.data.posters) {
            var posterId = url.split(/[\/.]/)[1];
            originalURL = "https://image.tmdb.org/t/p/original/" + posterId + ".jpg"
            source = "https://image.tmdb.org/t/p/w220_and_h330_face/" + posterId + ".jpg"
            posters.push({
              "href": originalURL,
              "src": source
            })
          }
        }

        this.setState({
          backdrops: backdrops,
          videos: videos,
          posters: posters
        })
      }).catch(e => {
        this.setState({
          posters: [],
          videos: [],
          backdrops: []
        })
      }).then(k =>
        Axios.get(backend + '/movie/' + this.props.match.params.id + '/me', 
      { headers: { Authorization: "Bearer " + token } })
        .then(y => {

          this.setState({
            movie: movieInfo,
            watched: (y.data.watched && y.data.watched === true)  ? true : false,
            favourited: (y.data.favourite && y.data.favourite === true) ? true : false,
            watchlist: (y.data.watchlist && y.data.watchlist === true) ? true : false,
            rating: (y.data.isRated && y.data.isRated === true) ? y.data.rating : 0
          })
      })
      .catch(o => {
        this.setState({
          movie: movieInfo,
          watched: false,
          favourited: false,
          watchlist: false
        })
      })
    )
  };

  componentWillUnmount() {
    window.removeEventListener("resize", this.handleResize);
  }

  render() {

    if (!this.state.movie) {
      return (
        <Loading lang={this.props.lang} />
      )
    }

    let headerTitle;
    if(this.state.windowSize < 768) {
      headerTitle = <div className="movie-title-div">
        <span>{this.state.movie.name} </span>
        <span><a target="_blank" rel="noopener noreferrer" href={`http://www.imdb.com/ + ${this.state.movie.imdb}`}><Image src="https://m.media-amazon.com/images/G/01/IMDb/BG_rectangle._CB1509060989_SY230_SX307_AL_.png" height="30vh" /></a></span>
        <br />
        <Image src={star} height="30vh" /> {this.state.movie.rating}
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
            {this.state.movie.rating }
          </div>
        </div>
    }
    const poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/" + this.state.movie.poster
    const backdrop = "https://image.tmdb.org/t/p/original/" + this.state.movie.backdrop
    return (
      <Container>
        <div className="movie-header">
          <div className="backdrop" style={{'background': `url("${backdrop}") no-repeat center center`, 'backgroundSize': 'cover'}}></div>
          <div className="poster">
            <Image src={poster} />
          </div>
          { headerTitle }
          <MovieEvaluation id={this.state.movie.tmdb} watched={this.state.watched} favourited={this.state.favourited} watchlist={this.state.watchlist} rating={this.state.rating} lang={this.props.lang} />
        </div>
        <div className="container-fluid">
          <div className="row">
            <div className="movie-content col-lg-8 order-lg-1 order-sm-2 order-2">
              {this.state.movie.tagline}
              <h1>{labels[this.props.lang].summary}</h1>
                <p>{this.state.movie.plot}</p>
              <h1>{labels[this.props.lang].cast}</h1>
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
              {(this.state.backdrops.length !== 0  ||
                this.state.posters.length   !== 0  ||
                this.state.videos.length    !== 0) &&
                <Tabs>
                  <TabList>
                    <h1 style={{ 'display': 'inline-block', 'border': '0', 'paddingBottom': 0, 'marginBottom': 0 }}
                    >{labels[this.props.lang].media}</h1>
                    { this.state.posters.length !== 0 &&
                      <Tab>{labels[this.props.lang].posters}</Tab>
                    }
                    { this.state.videos.length !== 0 &&
                      <Tab>{labels[this.props.lang].videos}</Tab>
                    }
                    { this.state.backdrops.length !== 0 &&
                      <Tab>{labels[this.props.lang].backdrops}</Tab>
                    }
                  </TabList>
                  { this.state.posters.length === 5 &&
                    <TabPanel>
                      <HorizontalSlider more={`/media/${this.props.match.params.id}`} loadMore="true" content={this.state.posters}/>
                    </TabPanel> 
                  }
                  { this.state.posters.length !== 5 && this.state.posters.length !== 0 &&
                    <TabPanel>
                      <HorizontalSlider content={this.state.posters}/>
                    </TabPanel>
                  }
                  { this.state.videos.length === 5 &&
                    <TabPanel>
                      <HorizontalSlider more={`/media/${this.props.match.params.id}`} loadMore="true" content={this.state.videos}/>
                    </TabPanel>
                  }
                  { this.state.videos.length !== 5 && this.state.videos.length !== 0 &&
                    <TabPanel>
                      <HorizontalSlider content={this.state.videos}/>
                    </TabPanel>
                  }
                  { this.state.backdrops.length === 5 &&
                    <TabPanel>
                      <HorizontalSlider more={`/media/${this.state.movie.id}`} loadMore="true" content={this.state.backdrops}/>   
                    </TabPanel>
                  }
                  { this.state.backdrops.length !== 5 && this.state.backdrops.length !== 0 &&
                    <TabPanel>
                      <HorizontalSlider content={this.state.backdrops}/>   
                    </TabPanel>
                  }
                </Tabs>
              }
              <h1>{labels[this.props.lang].discussion}</h1>
              <DiscussionBox lang={this.props.lang} />
            </div>
            <div className="col-lg-4 order-lg-2 order-sm-1 order-1">
              <div className="sidebar">
                <h6>{labels[this.props.lang].originalLanguage}</h6>
                  <Language language={this.state.movie.language} />
                <h6>{labels[this.props.lang].runtime}</h6>
                  <p>{this.state.movie.runtime}m</p>
                <h6>{labels[this.props.lang].genres}</h6>
                <div className="movie-genre">
                  <ul>
                    { this.state.movie.genres.map((genre) =>
                        <li><a href={`/movies?genre=${genre}`}>{genre}</a></li>
                    )}      
                  </ul>
                </div>
                <h6>{labels[this.props.lang].prodCompanies}</h6>
                <p>
                  { this.state.movie.companies.map((company) =>
                    <div className="prod-company"><a href="#">{company}</a><br/></div>
                  )} 
                </p>
              </div>
            </div>
          </div>
        </div>  
      </Container>
    )
  }
}