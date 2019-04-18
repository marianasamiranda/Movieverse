import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import logo from '../img/logo-256-nav.png'
import AvatarDropdown from './avatarDropdown'
import {LinkContainer} from 'react-router-bootstrap'
import {Link} from 'react-router-dom'
import Axios from 'axios'
import {backend, avatars} from './../var'
import { getToken } from '../cookies';

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

    if (this.props.logged && !this.props.avatar) {
      this.getAvatar()
    }

    this.state = {
      selected: undefined,
      avatar: undefined
    }
    this.handleChange = this.handleChange.bind(this)
  }

  getAvatar() {
    Axios.get(backend + "/avatar", { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      this.setState({
        avatar: avatars + x.data
      })
    })
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
                img={this.props.avatar ? this.props.avatar : this.state.avatar}
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