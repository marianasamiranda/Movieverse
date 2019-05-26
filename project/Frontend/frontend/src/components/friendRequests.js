import React, { Component } from 'react';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Flag from './flag'
import Axios from 'axios';
import {backend, avatars, labels} from '../var'
import { getToken } from '../cookies';
import { PulseLoader } from 'react-spinners'
import { Link } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'

export default class FriendRequests extends Component {
  constructor(props) {
    super(props)
    this.state = {
      current: 'received',
      received: [],
      sent: [],
      loading: false,
      feedback: undefined
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

  //accept or ignore received request
  handleReceivedRequest(u, d) {
    Axios.put(backend + '/process-request', {username: u, decision: d},
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        this.setState({
          received: this.state.received.filter(x => x.username !== u)
        })

        let feedback = '🔔 ' + labels[this.props.lang].requestFrom + ' ' + u + ' '
        if (d === false)
          feedback += labels[this.props.lang].rejected.toLowerCase()
        else
          feedback += labels[this.props.lang].accepted.toLowerCase()
        toast(feedback, { containerId: 'toast'});
      })
  }

  //cancel sent request
  handleSentRequest(u) {
    Axios.put(backend + '/cancel-request', { username: u },
      { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
        this.setState({
          sent: this.state.sent.filter(x => x.username !== u),
        })

        toast('🔔 ' + labels[this.props.lang].requestTo + ' ' + u + ' ' + 
                labels[this.props.lang].canceled.toLowerCase(), 
          { containerId: 'toast'})
      })
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
      })
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
                <Col xs="12"><Flag country={x.country} />{x.common} {labels[this.props.lang].friendsInCommon}</Col>
              </Row>
            </Col>
          </Row>
          <Row>
            {this.state.current === 'received' ?
            <>
              <Col xs="auto">
                <Button variant="danger" className="button-slim" 
                  onClick={() => this.handleReceivedRequest(x.username, false)}>
                  {'✖ ' + labels[this.props.lang].reject}
                </Button>
              </Col>
              <Col>
                <Button xs="auto" variant="success" className="button-slim"
                  onClick={() => this.handleReceivedRequest(x.username, true)}>
                  {'✓ ' + labels[this.props.lang].accept }
                </Button>
              </Col>
            </>
            :
              <Col>
                <Button variant="danger" className="button-slim"
                  onClick={() => this.handleSentRequest(x.username)}>
                  {'✖ ' + labels[this.props.lang].cancel}
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
            {labels[this.props.lang].friendsRequests}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col lg="3" sm="4" xs="6"
              className={"text-center tab-link " + (this.state.current === 'received' ? "selected" : "not-selected")}
              onClick={() => this.handleTab('received')}>
              {labels[this.props.lang].received}
            </Col>
            <Col lg="3" sm="4" xs="6"
              className={"text-center tab-link " + (this.state.current === 'sent' ? "selected" : "not-selected")}
              onClick={() => this.handleTab('sent')}>
              {labels[this.props.lang].sent}
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
        <ToastContainer
          position="top-right"
          autoClose={7000}
          hideProgressBar
          newestOnTop
          closeOnClick
          rtl={false}
          pauseOnVisibilityChange
          draggable
          pauseOnHover
          containerId={'toast'}
          
        />
      </Modal>
    )
  }
}