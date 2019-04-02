import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import logo from '../img/logo-256-nav.png'
import NavLink from 'react-bootstrap/NavLink';

class NavBarLink extends Component {
  render() {
    return (
      <NavLink href={this.props.url}>
        <div className={"navbar-item" + (this.props.selected ? " navbar-item-selected" : "")}>
          {this.props.name}
        </div>
      </NavLink>
    )
  }
}

//links: {name, url, selected}
export default class NavBar extends Component {
  render() {
    let links = []
    this.props.links.forEach(x => {
      links.push(
        <NavBarLink name={x.name} url={x.url} selected={x.selected}
          key={this.props.links.indexOf(x)} />
      )
    })

    return (
      <Navbar expand="lg" className="sticky-top">
        <Container>
          <img src={logo} className="navbar-logo" alt="Homepage" />
          <Navbar.Toggle aria-controls="basic-navbar-nav" className="navbar-dark" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ml-auto">
              {links}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    )
  }
}