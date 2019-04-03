import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

export default class Login extends Component  {
  render() {
    return (
      <Form>
        <Form.Group controlId="formBasicEmail">
          <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Button className="bg-medium-gray float-right" variant="secondary" type="submit">
          Submit
        </Button>
        <br/>
      </Form>

    )
  }
}

