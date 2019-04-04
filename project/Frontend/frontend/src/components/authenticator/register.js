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
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-at"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control
            type="text"
            placeholder="Username"
            aria-describedby="inputGroupPrepend"
            required
          />
        </InputGroup>
        <p />
        <InputGroup controlId="formEmail">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-envelope"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="email" placeholder="Email" />
        </InputGroup>
        <p />
        <InputGroup controlId="formName">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-user"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="name" placeholder="Name" />
        </InputGroup>
        <p />
        <InputGroup controlId="formPassword">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-key"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="password" placeholder="Password" />
        </InputGroup>
        <p />
        <InputGroup controlId="formConfirmPassword">
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-key"></i></InputGroup.Text>
          </InputGroup.Prepend>
          <Form.Control type="confirmPassword" placeholder="Confirm Password" />
        </InputGroup>
        <p />
        <InputGroup style={{ 'margin-bottom':'0.7em' }}>
          <Row noGutters='true'>
            <Col xs="2">
            <InputGroup.Prepend>
              <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-birthday-cake"></i></InputGroup.Text>
            </InputGroup.Prepend>
            </Col>
            <Col>
            <Datepicker />
            </Col>
          </Row>
        </InputGroup>
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
          <InputGroup>
          <InputGroup.Prepend>
            <InputGroup.Text id="inputGroupPrepend"><i class="fas fa-venus-mars"></i></InputGroup.Text>
          </InputGroup.Prepend>
              <Form.Control as="select">
              <option value="" disabled selected>Gender</option>
                <option>Other</option>
                <option>Female</option>
                <option>Male</option>
              </Form.Control>
          </InputGroup>
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
