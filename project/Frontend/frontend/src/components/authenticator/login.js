import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Axios from 'axios'
import ReactLoading from 'react-loading'
import {backend} from '../../var'
import { setToken } from '../../cookies'
import { labels } from '../../var'

export default class Login extends Component  {
  constructor(props) {
    super(props)
    this.handleChange = this.handleChange.bind(this)
    this.handleButton = this.handleButton.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.state = {
      username: "",
      password: "",
      validated: false,
      fail: false,
      failMessage: '',
      loading: false,
      failKey: 0
    }
  }

  handleChange(e) {
    let m = {}
    m[e.target.name] = e.target.value
    this.setState(m)
  }

  handleButton(e) {
    this.setState({validated: true})
  }

  handleSubmit(e) {
    this.login()
    e.preventDefault();
  }

  login() {
    this.setState({loading: true})

    let data = {}
    data['username'] = this.state.username
    data['password'] = this.state.password

    Axios.post(backend + '/login', data).then(x =>{
      this.setState({
        fail: false,
        loading: false
      })
      setToken(x.data)
      this.props.handleSession(data['username'])
    })
    .catch(x => {
      this.setState({
        fail: true,
        failMessage: x.response.data,
        failKey: this.state.failKey + 1,
        loading: false
      })
    })
  }

  render() {
    return (
      <Form onSubmit={this.handleSubmit} validated={this.state.validated}>
        <InputGroup className="input-margin">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i className="fas fa-at"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control 
            type="text" 
            name="username" 
            placeholder={labels[this.props.lang].username} 
            onChange={this.handleChange} 
            pattern=".{3,30}" 
            title={labels[this.props.lang].usernamePattern} 
            required
          />
          <Form.Control.Feedback type="invalid">
          </Form.Control.Feedback>
        </InputGroup>
        <InputGroup className="input-margin">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i className="fas fa-key"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control 
            type="password" 
            name="password" 
            placeholder={labels[this.props.lang].password} 
            onChange={this.handleChange} 
            pattern=".{8,30}" 
            title={labels[this.props.lang].passwordPattern} 
            required
          />
        </InputGroup>
        <Row>
        {this.state.fail ? 
          <Col md="7" xs="6">
            <div className="error-message" key={this.state.failKey}>
              {labels[this.props.lang][this.state.failMessage]}
            </div>
          </Col> : ""
          }
          <Col className="text-center">
          {this.state.loading ? 
            <ReactLoading type="bars" color="#4d4d4d" width="40pt" height="29pt" className="float-right"/> :
            <Button 
              className="float-right"
              variant="secondary" 
              type="submit"
              onClick={this.handleButton}>
              {labels[this.props.lang].submit} 
            </Button>
          }
          </Col>
        </Row>
      </Form>
    )
  }
}

