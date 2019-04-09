import React, { Component } from 'react';
import Login from './login'
import Register from './register'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'


export default class Authenticator extends Component  {

  constructor(props) {
    super(props)
    this.state = { state: 'login' }
    this.handleLogin = this.handleLogin.bind(this)
    this.handleRegister = this.handleRegister.bind(this)
  }

  handleLogin(event) {
    this.setState({state: 'login'})

  }

  handleRegister(event) {
    this.setState({state: 'register'})
  }

  render() {
    let form
    let colorLogin
    let colorRegister
    if (this.state.state === 'login') {
        form = <Login />
        colorLogin = 'dark-gray'
        colorRegister = 'light-gray'
    }
    else {
       form = <Register />
       colorLogin = 'light-gray'
       colorRegister = 'dark-gray'
    }
    return (
      <div className="authenticator">
        <Row className="justify-content-center">
          <Col className={"authenticator-tab " + colorLogin} xs="4" onClick={this.handleLogin}>
            <center className="cursor-hover">LOGIN</center>
          </Col>
          <Col xs="1" className="light-gray">
            <center>|</center>
          </Col>
          <Col className={"authenticator-tab " + colorRegister} xs="5" onClick={this.handleRegister}>
            <center className="cursor-hover">REGISTER</center>
          </Col>
        </Row>
        <p></p>
        {form}
      </div>
    )
  }
}
