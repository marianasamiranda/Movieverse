import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import logo from '../img/logo-256-nav.png'
import AvatarDropdown from './avatarDropdown'
import {LinkContainer} from 'react-router-bootstrap'
import {Link} from 'react-router-dom'

class NavBarLink extends Component {
  render() {
    return (
      <LinkContainer to={this.props.url} onClick={this.props.onClick}>
        <div className={"navbar-item" + (this.props.selected ? " navbar-item-selected" : "")}>
          {this.props.name}
        </div>
      </LinkContainer>
    )
  }
}

//links: {name, url, selected}
export default class NavBar extends Component {

  constructor(props) {
    super(props)
    this.state = {
      selected: undefined
    }
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange(n) {
    this.setState({selected: n})
  }

  render() {
    let links = []
    this.props.links.forEach(x => {
      if (!x.logged || (x.logged && this.props.logged)) {
        links.push(
          <NavBarLink name={x.name} url={x.url} selected={this.state.selected === x.name}
            key={this.props.links.indexOf(x)} onClick={() => this.handleChange(x.name)} />
        )
      }
    })

    return (
      <Navbar expand="lg" className="sticky-top">
        <Container>
          <Link to="/" className="navbar-logo">
            <img src={logo} alt="Homepage" onClick={() => this.handleChange(undefined)} />
          </Link>
          <Navbar.Toggle aria-controls="basic-navbar-nav" className="navbar-dark" />
          <Navbar.Collapse id="basic-navbar-nav text-center">
            <Nav className="ml-auto align-items-center">
              {links}
              {this.props.logged ?
              <AvatarDropdown 
                img={require('../img/monster.png')} 
                onClick={() => this.handleChange(undefined)} 
                handleSession={this.props.handleSession} />
                : ""
              }
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    )
  }
}