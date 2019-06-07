import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Jumbotron from 'react-bootstrap/Jumbotron'
import Nav from 'react-bootstrap/Nav';
import { backend, labels } from '../../var'
import Gallery from './gallery';
import Axios from 'axios';
import Loading from '../aux_pages/loading'
import '../../styles/MoviePage.css'


export default class MediaPage extends Component {

  constructor(props) {
    super(props);

    this.state = {
      movieName: undefined,
      movieYear: undefined,
      selected_tab: undefined,
      activeKey: 1,
      videos: undefined,
      backdrops: undefined,
      posters: undefined
    }
  }

  componentDidMount() {
    
    Axios.get(backend + '/movie/' + this.props.match.params.id + '/short')
    .then(y => {
      this.setState({
        movieName: y.data.name,
        movieYear: y.data.year
      });

    }).then(k => {
      Axios.get(backend + '/media/' + this.props.match.params.id)
      .then(x => {
        var tmp_videos = [];
        var tmp_backdrops = [];
        var tmp_posters = [];
        x.data.videos.map((video) =>
          tmp_videos.push({
            'href': 'https://www.youtube.com/watch?v=' + video,
            'src': 'http://img.youtube.com/vi/' + video + '/0.jpg'
          })
        )
        x.data.backdrops.map((backdrop) =>
          tmp_backdrops.push({
            'href': 'https://image.tmdb.org/t/p/original/' + backdrop,
            'src': 'https://image.tmdb.org/t/p/w500_and_h282_face/' + backdrop
          })
        )
        x.data.posters.map((poster) =>
          tmp_posters.push({
            'href': 'https://image.tmdb.org/t/p/original/' + poster,
            'src': 'https://image.tmdb.org/t/p/w500/' + poster
          })
        )

        this.setState({
          videos: tmp_videos,
          backdrops: tmp_backdrops,
          posters: tmp_posters
        });
        this.setState({
          selected_tab: <Gallery type='image' data={this.state.posters} />
        })
      });
    })
  }


  handleSelect(eventKey) {
    if(eventKey === "1") {
      this.setState({ activeKey: "1", selected_tab: <Gallery type='image' data={this.state.posters} />})
    }
    else if (eventKey === "2") {
      this.setState({ activeKey: "2", selected_tab: <Gallery type='image' data={this.state.backdrops} />})
    }
    else if (eventKey === "3") {
      this.setState({ activeKey: "3", selected_tab: <Gallery type='video' data={this.state.videos} />})
    }
  }

  render() {

    if (
        this.state.movieName === undefined &&
        this.state.movieYear === undefined &&
        this.state.videos === undefined &&
        this.state.backdrops === undefined &&
        this.state.posters === undefined) {
      return (
        <Loading lang={this.props.lang} />
      )
    }

    return <div>
      <Jumbotron className="media-header" fluid>
        <Container className="text-center">
          <h1>{this.state.movieName}</h1> <h3>({this.state.movieYear})</h3>
          <h5><a href={`/movie/${ this.props.match.params.id }`}><i className="fas fa-arrow-circle-left"></i> { labels[this.props.lang].goBack }</a></h5>
        </Container>
      </Jumbotron>
      <Nav className="justify-content-center vertical-align bg-light-gray media-nav" activeKey={this.state.activeKey} onSelect={k => this.handleSelect(k)} >
        <Nav.Item>
          <Nav.Link eventKey="1">Posters</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="2">Backdrops</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="3">{labels[this.props.lang].videos}</Nav.Link>
        </Nav.Item>
      </Nav>
      {this.state.selected_tab}
    </div>
  }
}
