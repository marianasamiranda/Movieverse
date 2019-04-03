import React, { Component } from 'react';
import Login from './login'
import Register from './register'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'


export default class Authenticator extends Component  {

  constructor(props) {
    super(props)
    this.state = { state: 'login' }
    this.login = this.login.bind(this)
    this.register = this.register.bind(this)
  }

  login(event) {
    this.setState({state: 'login'})

  }

  register(event) {
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
          <Col className={"authenticator-tab " + colorLogin} xs="4" onClick={this.login}>
            <center>LOGIN</center>
          </Col>
          <Col xs="1" className="light-gray">
            <center>|</center>
          </Col>
          <Col className={"authenticator-tab " + colorRegister} xs="5" onClick={this.register}>
            <center>REGISTER</center>
          </Col>
        </Row>
        <p></p>
        {form}
      </div>
    )
  }
}
