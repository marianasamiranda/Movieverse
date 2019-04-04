import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import InputGroup from 'react-bootstrap/InputGroup'

export default class Login extends Component  {
  render() {
    return (
      <Form>
        <InputGroup controlId="formBasicEmail">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-envelope"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="email" placeholder="Email" />
        </InputGroup>
        <p />
        <InputGroup controlId="formBasicPassword">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-key"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="password" placeholder="Password" />
        </InputGroup>
        <p />
        <Button className="float-right" variant="secondary" type="submit">
          Submit
        </Button>
        <br/>
      </Form>

    )
  }
}

