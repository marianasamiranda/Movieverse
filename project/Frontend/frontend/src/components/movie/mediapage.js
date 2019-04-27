import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Jumbotron from 'react-bootstrap/Jumbotron'
import Nav from 'react-bootstrap/Nav';
import Gallery from './gallery';
import '../../styles/MoviePage.css'


export default class MediaPage extends Component {

  constructor(props) {
    super(props);
    this.photos = [
      {
        src: 'https://image.tmdb.org/t/p/w500_and_h282_face/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg',
        href: 'https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg'
      },
      {
        src: 'https://image.tmdb.org/t/p/w500_and_h282_face/iSskYDN193CJfTbSpFfscSwZ9Sx.jpg'
      },
      {
        src: 'https://image.tmdb.org/t/p/w500_and_h282_face/iSskYDN193CJfTbSpFfscSwZ9Sx.jpg'
      },
      {
        src: 'https://image.tmdb.org/t/p/w500_and_h282_face/iSskYDN193CJfTbSpFfscSwZ9Sx.jpg'
      },
      {
        src: 'https://image.tmdb.org/t/p/w500_and_h282_face/iSskYDN193CJfTbSpFfscSwZ9Sx.jpg'
      }
    ]
    this.videos = [
      {
        src: 'https://img.youtube.com/vi/SYb-wkehT1g/0.jpg',
        href: 'https://www.youtube.com/watch?v=SYb-wkehT1g'
      },
      {
        src: 'https://img.youtube.com/vi/SYb-wkehT1g/0.jpg'
      },
      {
        src: 'https://img.youtube.com/vi/SYb-wkehT1g/0.jpg'
      },
      {
        src: 'https://img.youtube.com/vi/SYb-wkehT1g/0.jpg'
      },
      {
        src: 'https://img.youtube.com/vi/SYb-wkehT1g/0.jpg'
      }
    ]
    this.state = {
      selected_tab: <Gallery type='video' data={this.videos} />,
      activeKey: 1
    }
  }

  handleSelect(eventKey) {
    if(eventKey==1) {
      this.setState({ activeKey: 1, selected_tab: <Gallery type='video' data={this.videos} />})
    }
    else {
      this.setState({ activeKey: 2, selected_tab: <Gallery type='image' data={this.photos} />})
    }
  }

  render() {

    return <div>
      <Jumbotron className="media-header" fluid>
        <Container className="text-center">
          <h1>The Favourite</h1> <h3>(2018)</h3>
          <h5><a href=""><i className="fas fa-arrow-circle-left"></i> Go Back</a></h5>
        </Container>
      </Jumbotron>
      <Nav className="justify-content-center vertical-align bg-light-gray media-nav" activeKey={this.state.activeKey} onSelect={k => this.handleSelect(k)} >
        <Nav.Item>
          <Nav.Link eventKey={1}>Videos</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey={2}>Backdrops</Nav.Link>
        </Nav.Item>
      </Nav>
      {this.state.selected_tab}
    </div>
  }
}
