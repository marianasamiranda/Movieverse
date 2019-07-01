import React, { Component } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { backend, labels } from '../../var';
import Jumbotron from 'react-bootstrap/Jumbotron'
import { Link } from 'react-router-dom'
import Axios from 'axios';
import InfiniteScroll from 'react-infinite-scroller';
import Row from 'react-bootstrap/Row'
import Loading from '../aux_pages/loading';
import Col from 'react-bootstrap/Col'
import MovieCard from '../movie-card'

export default class CrewPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      movieName: undefined,
      movieYear: undefined,
      castPage: 0,
      crewPage: 0,
      cast: undefined,
      crew: undefined,
      moreCast: false,
      moreCrew: false,
      activeKey: "1"
    }

    this.loadCast()
    this.loadCrew()
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

  loadCast() {
    let self = this

    Axios.get(backend + '/movie/' + this.props.match.params.id + '/cast?page=' + this.state.castPage)
    .then(function(response) {

      self.setState({
        cast: self.state.cast === undefined ? response.data.members : self.state.cast.concat(response.data.members),
        moreCast: response.data.moreMembers,
        castPage: self.state.castPage + 1
      })

    })
  }

  loadCrew() {
    let self = this

    Axios.get(backend + '/movie/' + this.props.match.params.id + '/crew?page=' + this.state.crewPage)
    .then(function(response) {

      self.setState({
        crew: self.state.crew === undefined ? response.data.members : self.state.crew.concat(response.data.members),
        moreCrew: response.data.moreMembers,
        crewPage: self.state.crewPage + 1
      })

    })
  }

  handleSelect(eventKey) {
    if(eventKey === "1") {
      this.setState({ activeKey: "1" })
    }
    else if (eventKey === "2") {
      this.setState({ activeKey: "2" })
    }
  }

  render() {
    const loader = <div key={0} className="loader">{labels[this.props.lang].loading}... </div>;

    if (
      this.state.cast === undefined &&
      this.state.crew === undefined) {
      return (
        <Loading lang={this.props.lang} />
      )
    }

    return <div>
      <Jumbotron className="media-header" fluid>
        <Container className="text-center">
          <h1>{this.state.movieName}</h1> <h3>({this.state.movieYear})</h3>
          <h5><Link to={`/movie/${ this.props.match.params.id }`}><i className="fas fa-arrow-circle-left"></i> { labels[this.props.lang].goBack }</Link></h5>
        </Container>
      </Jumbotron>
      <Nav className="justify-content-center vertical-align bg-light-gray" style={{ 'marginBottom': '1rem' }} activeKey={this.state.activeKey} onSelect={k => this.handleSelect(k)} >
        <Nav.Item>
          <Nav.Link eventKey="1">{ labels[this.props.lang].cast }</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="2">{ labels[this.props.lang].crew }</Nav.Link>
        </Nav.Item>
      </Nav>
      
      { this.state.activeKey === "1" && this.state.cast !== undefined && 
        <Container>
          <InfiniteScroll
            pageStart={0}
            loadMore={this.loadCast.bind(this)}
            hasMore={this.state.moreCast}
            loader={ loader }>
            <Row>
              { this.state.cast.map(function(member, i) {
                return <Col key={i} lg="3" md="3" xs="6">
                    <Link to={`/member/${member.memberId}`}>
                      <MovieCard small img={`https://image.tmdb.org/t/p/w200/${member.memberImage}`} title={member.memberName} info={member.memberRole} />
                    </Link>
                  </Col>
                })
              }
            </Row>
          </InfiniteScroll>
        </Container>
      }
      { this.state.activeKey === "2" && this.state.crew !== undefined && 
        <Container>
          <InfiniteScroll
            pageStart={0}
            loadMore={this.loadCrew.bind(this)}
            hasMore={this.state.moreCrew}
            loader={ loader }>
            <Row>
              { this.state.crew.map(function(member, i) {
                return <Col key={i} lg="3" md="3" xs="6">
                    <Link to={`/member/${member.memberId}`}>
                      <MovieCard small img={`https://image.tmdb.org/t/p/w200/${member.memberImage}`} title={member.memberName} info={member.memberRole} />
                    </Link>
                  </Col>
                })
              }
            </Row>
          </InfiniteScroll>
        </Container>
      }
    </div>
  }
}