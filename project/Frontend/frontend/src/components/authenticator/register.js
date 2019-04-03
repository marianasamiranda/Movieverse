import React, { Component } from 'react';
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import InputGroup from 'react-bootstrap/InputGroup'
import Datepicker from '../datepicker'
import ReactFlagsSelect from 'react-flags-select'

import 'react-flags-select/css/react-flags-select.css';

export default class Register extends Component  {
  render() {
    return (
      <Form>
        <InputGroup>
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend">@</InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control
            type="text"
            placeholder="Username"
            aria-describedby="inputGroupPrepend"
            required
          />
        </InputGroup>
        <p />
        <Form.Group controlId="formEmail">
          <Form.Control type="email" placeholder="Email" />
        </Form.Group>
        <Form.Group controlId="formName">
          <Form.Control type="name" placeholder="Name" />
        </Form.Group>
        <Form.Group controlId="formPassword">
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Form.Group controlId="formConfirmPassword">
          <Form.Control type="confirmPassword" placeholder="Confirm Password" />
          </Form.Group>
        <Form.Group style={{ 'margin-bottom':'0.7em' }} >
          <Datepicker />
          </Form.Group>
        <Row>
          <Col xs="12" sm="6" style={{ 'margin-bottom':'0.7em' }}>
          <div class="left-right">
            <span className="label-country">Country </span>
            <ReactFlagsSelect 
            searchable={true}
            placeholder=" "
            defaultCountry="PT"
            searchPlaceholder="Country"
            showSelectedLabel={false}
             />
          </div>
          </Col>
          <Col xs="12" sm="6">
            <Form.Control as="select">
            <option value="" disabled selected>Gender</option>
              <option>Other</option>
              <option>Female</option>
              <option>Male</option>
            </Form.Control>
          </Col>
        </Row>
        <Button className="bg-medium-gray float-right" variant="secondary" type="submit">
          Submit
        </Button>
        <br />
      </Form>
    )
  }
}
