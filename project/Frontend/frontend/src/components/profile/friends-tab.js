import React, { Component } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'
import {avatars, labels, backend} from '../../var'
import { getToken } from '../../cookies'
import {Link} from 'react-router-dom'
import Axios from 'axios'

const limit = 12

export default class FriendsCard extends Component {
  constructor(props) {
    super(props)
    this.state = this.resetedState()
    this.showMore = this.showMore.bind(this)
  }

  resetedState() {
    return {
      loading: false,
      friends: this.buildCards(this.props.friends),
      current: 1,
      showMore: this.props.friends.length === 24
    }
  }

  componentDidUpdate(prevProps) {
    if (this.props.user !== prevProps.user)
      this.setState(this.resetedState())
  }

  buildCards(friends) {
    let l = []
    const i = this.state ? this.state.friends.length : 0
    friends.forEach(x =>
      l.push(
        <Col xs="12" sm="6" md="4" lg="12" key={i + friends.indexOf(x)} className="friend margin-bottom-10">
          <Link to={"/u/" + x.username}>
            <Row>
              <Col xs="3" className="text-right">
                <img src={avatars + x.avatar} alt="Avatar" />
              </Col>
              <Col xs="9">
                <p>{x.username}</p>
              </Col>
            </Row>
          </Link>
        </Col>
      )
    )
    return l
  }

  showMore() {
    if (this.state.current * limit === this.state.friends.length) {
      this.setState({
        loading: true
      })
      const query = 'friends?begin=' + this.state.current * limit + '&limit=24'
      Axios.get(backend + '/user/friends/' + query,
        { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
          this.setState({
            friends: this.state.friends.concat(this.buildCards(x.data)),
            current: this.state.current + 1,
            loading: false,
            showMore: x.data.length === 24
          })
        }).catch(x => console.log(x.response.data))
    }
    else {
      this.setState({
        current: this.state.current + 1,
        showMore: ((this.state.current + 1) * limit > this.state.friends.length ? false : true)
      })
    }
  }

  render() {
    return (
      <div className="info-card">
        <Row>
          <Col className="card-title selected">
            {labels[this.props.lang].friends}
          </Col>
        </Row>
        <Row className="box">
          {this.state.friends.slice(0, limit * this.state.current)}
        </Row>
        {this.props.friends.length > 0 ?
          <Button
            variant="secondary"
            size="sm"
            className="button-slim"
            disabled={this.state.loading || !this.state.showMore}
            onClick={this.showMore}
          >
            {!this.state.loading && this.state.showMore ? labels[this.props.lang].showMore : ""}
            {this.state.loading ? labels[this.props.lang].loading : ""}
            {!this.state.showMore ? labels[this.props.lang].noMoreResults : ""}
          </Button>
          : <p className="text-center">{labels[this.props.lang].noFriends}</p>}
      </div>
    )
  }
}