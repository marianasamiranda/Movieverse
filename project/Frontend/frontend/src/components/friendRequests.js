import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Flag from './flag'
import Axios from 'axios';
import {backend, avatars} from '../var'
import { getToken } from '../cookies';
import { PulseLoader } from 'react-spinners'
import { Link } from 'react-router-dom'

export default class FriendRequests extends Component {
  constructor(props) {
    super(props)
    this.state = {
      current: 'received',
      received: [],
      sent: [],
      loading: false
    }
    this.handleTab = this.handleTab.bind(this)
    this.loadRequests = this.loadRequests.bind(this)
    this.handleReceivedRequest = this.handleReceivedRequest.bind(this)
  }

  handleTab(t) {
    if (t !== this.state.current) {
      this.setState({
        current: t,
        loading: true
      })
      this.loadRequests(t)
    }
  }

  handleClose(t) {
    this.setState({
      loaded: false
    })
  }

  handleReceivedRequest(u, d) {
    Axios.put(backend + '/process-request', {username: u, decision: d},
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        this.setState({
          received: this.state.received.filter(x => x.username !== u)
        })
      }).catch(x => console.log(x.response.data)) //TODO remove
  }

  handleSentRequest(u) {
    Axios.put(backend + '/cancel-request', { username: u },
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        this.setState({
          sent: this.state.sent.filter(x => x.username !== u)
        })
      }).catch(x => console.log(x.response.data)) //TODO remove
  }

  //TODO load N at a time ?
  loadRequests(t) {
    Axios.get(backend + '/friend-requests?type=' + (t ? t : this.state.current),
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        this.setState({
          [this.state.current]: x.data,
          loaded: true,
          loading: false
        })
      }).catch(x => console.log(x.response.data)) //TODO remove
  }

  buildUserInfo() {
    let l = []
    this.state[this.state.current].forEach(x => {
      l.push(
        <Col xs="12" sm="6" lg="4" key={this.state[this.state.current].indexOf(x)} className="user-info-compact">
          <Row>
            <Col xs="3" className="text-center">
              <Link to={"/u/" + x.username}>
                <img className="avatar" src={avatars + x.avatar} alt="Avatar" />
              </Link>
            </Col>
            <Col xs="9">
              <Row>
                <Col xs="12"><i className="fas fa-at" />&nbsp;{x.username}</Col>
                <Col xs="12"><i className="fas fa-user" />&nbsp;{x.name}</Col>
                <Col xs="12"><Flag country={x.country} />{x.common} friends in common</Col>
              </Row>
            </Col>
          </Row>
          <Row>
            {this.state.current === 'received' ?
            <>
              <Col xs="auto">
                <Button variant="danger" className="button-slim" 
                  onClick={() => this.handleReceivedRequest(x.username, false)}>
                  ✖ Reject
                </Button>
              </Col>
              <Col>
                <Button xs="auto" variant="success" className="button-slim"
                  onClick={() => this.handleReceivedRequest(x.username, true)}>
                  ✓ Accept
                </Button>
              </Col>
            </>
            :
              <Col>
                <Button variant="danger" className="button-slim"
                  onClick={() => this.handleSentRequest(x.username)}>
                  ✖ Cancel
                </Button>
              </Col>
            }
          </Row>
        </Col>
      )
    })

    return l
  }

  render() {
    if (this.props.show && !this.state.loaded) {
      this.loadRequests()
    }

    return (
      <Modal show={this.props.show} size="lg" onHide={() => {this.handleClose(); this.props.handleShowRequests()}}>
        <Modal.Header closeButton>
          <Modal.Title>
            <i className="fas fa-user-friends" />&nbsp;
            Friend Requests
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col lg="3" sm="4" xs="6"
              className={"text-center tab-link " + (this.state.current === 'received' ? "selected" : "not-selected")}
              onClick={() => this.handleTab('received')}>
              Received
            </Col>
            <Col lg="3" sm="4" xs="6"
              className={"text-center tab-link " + (this.state.current === 'sent' ? "selected" : "not-selected")}
              onClick={() => this.handleTab('sent')}>
              Sent
            </Col>
          </Row>
          <Row>
            {this.state.loading ?
              <Col xs="12" className="text-center">
                <PulseLoader size={10} color="#333333" />
              </Col>
              : this.buildUserInfo()
            }
          </Row>
        </Modal.Body>
      </Modal>
    )
  }
}