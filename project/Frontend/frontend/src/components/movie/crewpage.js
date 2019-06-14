import React, { Component } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { backend, labels } from '../../var';
import Jumbotron from 'react-bootstrap/Jumbotron'
import { Link } from 'react-router-dom'
import Axios from 'axios';

export default class CrewPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      movieName: undefined,
      movieYear: undefined,
      activeKey: 1
    }
  }

  componentDidMount() {
    
    Axios.get(backend + '/movie/' + this.props.match.params.id + '/short')
    .then(y => {
      this.setState({
        movieName: y.data.name,
        movieYear: y.data.year
      });
    })
    .catch((e) =>
      console.log(e)
    )
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
    return <div>
      <Jumbotron className="media-header" fluid>
        <Container className="text-center">
          <h1>{this.state.movieName}</h1> <h3>({this.state.movieYear})</h3>
          <h5><Link to={`/movie/${ this.props.match.params.id }`}><i className="fas fa-arrow-circle-left"></i> { labels[this.props.lang].goBack }</Link></h5>
        </Container>
      </Jumbotron>
      <Nav className="justify-content-center vertical-align bg-light-gray media-nav" activeKey={this.state.activeKey} onSelect={k => this.handleSelect(k)} >
        <Nav.Item>
          <Nav.Link eventKey="1">{ labels[this.props.lang].cast }</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="2">{ labels[this.props.lang].crew }</Nav.Link>
        </Nav.Item>
      </Nav>
    </div>
  }
}