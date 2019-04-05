import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class Login extends Component  {
  constructor(props) {
    super(props)
    this.handleChange = this.handleChange.bind(this)
    this.handleButton = this.handleButton.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.state = {
      username: "",
      password: "",
      validated: false
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
    let m = this.state
    delete m.validated
    this.setState({validated: false})
    this.props.login(m)
    e.preventDefault();
  }

  render() {
    return (
      <Form onSubmit={this.handleSubmit} validated={this.state.validated}>
        <InputGroup>
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i className="fas fa-envelope"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control 
            type="text" 
            name="username" 
            placeholder="Username" 
            onChange={this.handleChange} 
            pattern=".{3,30}" 
            title="Username must have between 3 and 30 chars" 
              required
          />
          <Form.Control.Feedback type="invalid">
          </Form.Control.Feedback>
        </InputGroup>
        <p />
        <InputGroup>
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i className="fas fa-key"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control 
            type="password" 
            name="password" 
            placeholder="Password" 
            onChange={this.handleChange} 
            pattern=".{9,30}" 
            title="Password must have between 8 and 30 chars" 
            required
          />
        </InputGroup>
        <p />
        <Row>
          <Col md="8" xs="6">
            <div className="error-message">
              {this.props.loginFail ? this.props.errorMessage : ""}
            </div>
          </Col>
          <Col>
            <Button 
              className="float-right" 
              variant="secondary" 
              type="submit" 
              onClick={this.handleButton}>
              Submit
            </Button>
          </Col>
        </Row>
      </Form>

    )
  }
}

