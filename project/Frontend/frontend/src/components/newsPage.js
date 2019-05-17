import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Axios from 'axios'
import CardColumns from 'react-bootstrap/CardColumns'
import { backend } from '../var'
import Loading from './aux_pages/loading'
import InfiniteScroller from 'react-infinite-scroller'
import '../styles/News.css'

export default class NewsPage extends Component {

  constructor(props) {
    super(props)
    this.state = {
      articles: [],
      cards: [],
      next: 1,
      loading: true,
      arrangedCards: []
    }
    this.loadMore = this.loadMore.bind(this)
  }

  componentDidMount() {
    document.title = "News | Movieverse"
    this.getArticles()
  }

  getArticles() {
    this.setState({
      loading: true
    })
    Axios.get(backend + '/news').then(x => {
      this.setState({
        articles: x.data,
        loading: false
      })
    })
  }

  buildCards(i) {
    let l = []
    this.state.articles.slice(12 * (i-1), 12 * i).forEach(x => {
      l.push(
        <Col xs="12" md="6" lg="4" key={this.state.articles.indexOf(x)}>
        <a href={x.link} key={this.state.articles.indexOf(x)}>
          <Card className="news-card" key={this.state.articles.indexOf(x)}>
            <Card.Img variant="top" src={x.image} />
            <Card.Body>
              <Card.Title className="card-title">
                {x.title}
              </Card.Title>
              <Card.Text>
                {x.description.slice(0, 160) + (x.description.length >= 160 ? '...' : "")}
              </Card.Text>
              <Card.Subtitle className="muted">
                {x.date}
              </Card.Subtitle>
            </Card.Body>
          </Card>
        </a>
        </Col>
      )
    })
    return l
  }

  loadMore() {
    const cards = this.state.cards.concat(this.buildCards(this.state.next))
    this.setState({
      cards: cards,
      next: this.state.next + 1,
    })
  }
  
  render() {
    return (
      <>
      {this.state.loading ?
        <Loading />
        :
        <>
          <Container className="news-container">
            <div className="title text-center">
              Movie News
            </div>
            {this.state.loading ? <Loading /> : ""}
          </Container>
          <Container>
            <InfiniteScroller
                pageStart={0}
                loadMore={this.loadMore}
                hasMore={this.state.cards.length < 50}>
              <Row>
                {this.state.cards}
              </Row>
            </InfiniteScroller>
          </Container>
        </>
      }
      </>
    )
  }

}