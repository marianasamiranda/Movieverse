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
import { getToken, clearToken } from '../cookies'
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
    this.clearNotification = this.clearNotification.bind(this)
  }

  getAvatar() {
    Axios.get(backend + "/user/avatar", { headers: { Authorization: "Bearer " + getToken() } }).then(x => {
      this.setState({
        img: avatars + x.data.img,
        requests: x.data.requests
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

  clearNotification() {
    this.setState({
      requests: false
    })
  }

  logout() {
    const token = clearToken()
    Axios.post(backend + '/logout', {}, { headers: { Authorization: "Bearer " + token } }).then(x => {
      this.props.handleSession()
    })
  }

  render() {
    let links = []
    this.props.links.forEach(x => {
      if (!x.logged || (x.logged && this.props.logged)) {
        links.push(
          <NavBarLink name={x.name} url={x.url} selected={this.state.selected === x.name} 
            handleExpand={this.handleExpand} key={this.props.links.indexOf(x)} 
            onClick={() => (!x.logout) ? this.handleChange(x.name) : this.logout()}
            />
        )
      }
    })

    const languages = [
      { name: 'English', code: 'en' },
      { name: 'Português', code: 'pt' },
      { name: 'Francais', code: 'fr' },
      { name: 'Español', code: 'es' },
      { name: 'Deutsch', code: 'de' },
      { name: 'Italiano', code: 'it' },
      { name: 'Nederlands', code: 'nl' },
      { name: 'ελληνικά', code: 'el' },
      { name: 'Pусский', code: 'ru' },
      { name: 'हिंदी', code: 'hi' },
      { name: 'Bahasa indonesia', code: 'id' },
      { name: '日本人の', code: 'ja' },
      { name: '한국어', code: 'ko' },
      { name: '中国', code: 'zh_cn' },
      { name: 'العربية', code: 'ar' },
    ]

    let langDropdownItems = []
    languages.forEach(x => {
      langDropdownItems.push(                
        <NavDropdown.Item onSelect={() => this.props.changeLanguage(x.code)} key={languages.indexOf(x)}>
          <Flag language country={x.code}/> 
          {x.name}
        </NavDropdown.Item>
      )
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
                {langDropdownItems}
              </NavDropdown>
              {this.props.logged ?
              <AvatarDropdown
                img={this.props.avatar ? this.props.avatar : this.state.img}
                handleExpand={() => this.handleChange(undefined)} 
                handleSession={this.handleSession}
                getAvatar={this.getAvatar} 
                lang={this.props.lang}
                requests={this.state.requests}
                updatedReceived={this.updateReceived}
                clearNotification={this.clearNotification}
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