import React, { Component } from 'react';
import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import logo from '../img/logo-256-nav.png'
import AvatarDropdown from './avatarDropdown'
import {LinkContainer} from 'react-router-bootstrap'
import {Link} from 'react-router-dom'
import Axios from 'axios'
import {backend, avatars} from './../var'
import { getToken } from '../cookies'
import Flag from './flag'

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
      avatar: undefined,
      expanded: false
    }
    this.handleChange = this.handleChange.bind(this)
    this.handleExpand = this.handleExpand.bind(this)
    this.handleSession = this.handleSession.bind(this)
    this.getAvatar = this.getAvatar.bind(this)
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
    this.handleExpand()
  }

  handleExpand(e) {
    if (e) {
      this.setState({
        expanded: !this.state.expanded
      })
    }
    else {
      this.setState({
        expanded: false
      })
    }
  }

  handleSession(e) {
    this.setState({
      avatar: undefined
    })
    this.props.handleSession()
  }

  render() {
    let links = []
    this.props.links.forEach(x => {
      if (!x.logged || (x.logged && this.props.logged)) {
        links.push(
          <NavBarLink name={x.name} url={x.url} selected={this.state.selected === x.name} 
            handleExpand={this.handleExpand} key={this.props.links.indexOf(x)} 
            onClick={() => this.handleChange(x.name)} />
        )
      }
    })

    return (
      <Navbar collapseOnSelect expand="lg" sticky="top" variant="dark"
        expanded={this.state.expanded}  onToggle={this.handleExpand}>
        <Container>
          <Link to="/" className="navbar-logo">
            <img src={logo} alt="Homepage" onClick={() => this.handleChange(undefined)} />
          </Link>
          <Navbar.Toggle aria-controls="basic-navbar-nav" className="navbar-dark" />
          <Navbar.Collapse id="basic-navbar-nav text-center">
            <Nav className="ml-auto align-items-center">
              {links}
              <NavDropdown className="nav-dropdown" title={<Flag language country={this.props.lang} />}>
                <NavDropdown.Item onSelect={() => this.props.changeLanguage('en')}>
                  <Flag language country='en'/> English
                </NavDropdown.Item>
                <NavDropdown.Item onSelect={() => this.props.changeLanguage('pt')}>
                  <Flag language country='pt'/> Português
                </NavDropdown.Item>
              </NavDropdown>
              {this.props.logged ?
              <AvatarDropdown
                img={this.props.avatar ? this.props.avatar : this.state.avatar}
                handleExpand={() => this.handleChange(undefined)} 
                handleSession={this.handleSession}
                getAvatar={this.getAvatar} 
                lang={this.props.lang}
                />
                : ""
              }
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    )
  }
}