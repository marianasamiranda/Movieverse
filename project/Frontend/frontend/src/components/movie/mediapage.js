import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Jumbotron from 'react-bootstrap/Jumbotron';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'
import Nav from 'react-bootstrap/Nav';
import { backend, labels } from '../../var';
import Axios from 'axios';
import { Link } from 'react-router-dom';
import Loading from '../aux_pages/loading';
import InfiniteScroll from 'react-infinite-scroller';
import '../../styles/MoviePage.css';


export default class MediaPage extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movieName: undefined,
      movieYear: undefined,
      selectedTab: undefined,
      activeKey: "1",
      videos: undefined,
      backdrops: undefined,
      posters: undefined,
      videosPage: 0,
      backdropsPage: 0,
      postersPage: 0,
      moreVideos: false,
      moreBackdrops: false,
      morePosters: false
    }

    this.loadPosters()
    this.loadBackdrops()
    this.loadVideos()
  }

  componentDidMount() {
    
    Axios.get(backend + '/movie/' + this.props.match.params.id + '/short')
    .then(y => {
      this.setState({
        movieName: y.data.name,
        movieYear: y.data.year
      });
    })
  }

  loadVideos() {
    let self = this

    Axios.get(backend + '/movie/' + this.props.match.params.id + '/videos?page=' + this.state.videosPage)
    .then(function(response) {

      let tmpVideos = []

      if(response.data.videos) {
        response.data.videos.map((video) =>
          tmpVideos.push({
            'href': 'https://www.youtube.com/watch?v=' + video,
            'src': 'http://img.youtube.com/vi/' + video + '/0.jpg'
          })
        )
      }

      self.setState({
        videos: self.state.videos !== undefined ? self.state.videos.concat(tmpVideos) : tmpVideos,
        moreVideos: response.data.moreVideos,
        videosPage: self.state.videosPage + 1
      })

    })
  }

  loadBackdrops() {
    let self = this

    Axios.get(backend + '/movie/' + this.props.match.params.id + '/backdrops?page=' + this.state.backdropsPage)
    .then(function(response) {

      let tmpBackdrops = []

      if(response.data.backdrops) {
        response.data.backdrops.map((backdrop) =>
          tmpBackdrops.push({
            'href': 'https://image.tmdb.org/t/p/original/' + backdrop,
            'src': 'https://image.tmdb.org/t/p/w500_and_h282_face/' + backdrop
          })
        )
      }

      self.setState({
        backdrops: self.state.backdrops !== undefined ? self.state.backdrops.concat(tmpBackdrops) : tmpBackdrops,
        moreBackdrops: response.data.moreBackdrops,
        backdropsPage: self.state.backdropsPage + 1
      })

    })
  }

  loadPosters() {
    let self = this

    Axios.get(backend + '/movie/' + this.props.match.params.id + '/posters?page=' + this.state.postersPage)
    .then(function(response) {

      let tmpPosters = []

      if(response.data.posters) {
        response.data.posters.map((poster) =>
          tmpPosters.push({
            'href': 'https://image.tmdb.org/t/p/original/' + poster,
            'src': 'https://image.tmdb.org/t/p/w500/' + poster
          })
        )
      }
      self.setState({
        posters: self.state.posters !== undefined ? self.state.posters.concat(tmpPosters) : tmpPosters,
        morePosters: response.data.morePosters,
        postersPage: self.state.postersPage + 1
      })

    })
  }

  handleSelect(eventKey) {
    if(eventKey === "1") {
      this.setState({ activeKey: "1"})
    }
    else if (eventKey === "2") {
      this.setState({ activeKey: "2"})
    }
    else if (eventKey === "3") {
      this.setState({ activeKey: "3"})
    }
  }

  render() {

    if (
        this.state.movieName === undefined ||
        this.state.movieYear === undefined ||
        this.state.videos === undefined ||
        this.state.backdrops === undefined ||
        this.state.posters === undefined) {
      return (
        <Loading lang={this.props.lang} />
      )
    }

    const loader = <div key={0} className="loader">{labels[this.props.lang].loading}... </div>

    return <div>
      <Jumbotron className="media-header" fluid>
        <Container className="text-center">
          <h1>{this.state.movieName}</h1> <h3>({this.state.movieYear})</h3>
          <h5><Link to={`/movie/${ this.props.match.params.id }`}><i className="fas fa-arrow-circle-left"></i> { labels[this.props.lang].goBack }</Link></h5>
        </Container>
      </Jumbotron>
      <Nav className="justify-content-center vertical-align bg-light-gray media-nav" activeKey={this.state.activeKey} onSelect={k => this.handleSelect(k)} >
        { this.state.posters !== undefined &&
          <Nav.Item>
            <Nav.Link eventKey="1">Posters</Nav.Link>
          </Nav.Item>
        }
        { this.state.backdrops !== undefined &&
          <Nav.Item>
            <Nav.Link eventKey="2">Backdrops</Nav.Link>
          </Nav.Item>
        }
        { this.state.videos !== undefined &&
          <Nav.Item>
            <Nav.Link eventKey="3">{labels[this.props.lang].videos}</Nav.Link>
          </Nav.Item>
        }
      </Nav>
      <Container>
        
        { this.state.activeKey === "1" &&
          <InfiniteScroll
            pageStart={0}
            loadMore={this.loadPosters.bind(this)}
            hasMore={this.state.morePosters}
            loader={ loader }>
            <Row>
              { this.state.posters.map(function(content, i) { 
                return <Col key={i} lg="4">
                  <div className="galleryItem">
                    <div className="galleryContainer">
                      <Image className="image" src={ content.src } />
                      <a target='_blank' rel="noopener noreferrer" href={ content.href } className="overlay">
                        <div className="text"><i className="fas fa-search fa-5x"></i></div>
                      </a>
                    </div>
                  </div>
                </Col>
              })}
            </Row>
          </InfiniteScroll>
        }
        { this.state.activeKey === "2" &&
          <InfiniteScroll
            pageStart={0}
            loadMore={this.loadBackdrops.bind(this)}
            hasMore={this.state.moreBackdrops}
            loader={ loader }>
            <Row>
              { this.state.backdrops.map(function(content, i) { 
                return <Col key={i} lg="4">
                  <div className="galleryItem">
                    <div className="galleryContainer">
                      <Image className="image" src={ content.src } />
                      <a target='_blank' rel="noopener noreferrer" href={ content.href } className="overlay">
                        <div className="text"><i className="fas fa-search fa-5x"></i></div>
                      </a>
                    </div>
                  </div>
                </Col>
              })}
            </Row>
          </InfiniteScroll>
        }
        { this.state.activeKey === "3" &&
          <InfiniteScroll
            pageStart={0}
            loadMore={this.loadVideos.bind(this)}
            hasMore={this.state.moreVideos}
            loader={ loader }>
            <Row>
              { this.state.videos.map(function(content, i) { 
                return <Col key={i} lg="4">
                  <div className="galleryItem">
                    <div className="galleryContainer">
                      <Image className="image" src={ content.src } />
                      <a target='_blank' rel="noopener noreferrer" href={ content.href } className="overlay">
                        <div className="text"><i className="fas fa-play-circle fa-5x"></i></div>
                      </a>
                    </div>
                  </div>
                </Col>
              })}
            </Row>
          </InfiniteScroll>
        }
      </Container>
    </div>
  }
}
