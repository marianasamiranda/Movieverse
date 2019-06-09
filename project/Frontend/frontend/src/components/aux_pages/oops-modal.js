import React, { Component } from 'react';
import Modal from 'react-bootstrap/Modal'
import ohNo from '../../img/monster-ohno.svg'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Image from 'react-bootstrap/Image'


export default class OopsModal extends Component {
  render() {
    return <Modal show={this.props.showModal} onHide={this.props.handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Oops :'(</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Row>
          <Col lg="5" md="5" sm="5" xs="5">
            <Image src={ohNo} />
          </Col>
          <Col lg="7" md="7" sm="7" xs="7" className="my-auto">
            { this.props.message }
          </Col>
        </Row>
      </Modal.Body>
    </Modal>
  }
}