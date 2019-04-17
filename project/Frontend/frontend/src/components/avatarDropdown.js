import React, { Component } from 'react';
import Dropdown from 'react-bootstrap/Dropdown'
import { Link } from 'react-router-dom'
import { clearToken } from '../cookies'
import Axios from 'axios'
import { backend } from '../var'

class CustomToggle extends Component {
  constructor(props, context) {
    super(props, context);
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick(e) {
    e.preventDefault();
    this.props.onClick(e);
  }

  render() {
    return (
      <div onClick={this.handleClick}>
        {this.props.children}
      </div>
    );
  }
}

class CustomMenu extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = { value: '' };
  }

  render() {
    const {
      children,
      className,
      'aria-labelledby': labeledBy,
    } = this.props;

    return (
      <div className={className} aria-labelledby={labeledBy}>
        <ul className="list-unstyled">
          {React.Children.toArray(children)}
        </ul>
      </div>
    );
  }
}

export default class AvatarDropdown extends Component {

  constructor(props) {
    super(props)
    this.state = {
      show: false
    }
    this.handleChange = this.handleChange.bind(this)
    this.logout = this.logout.bind(this)
  }

  handleChange() {
    this.setState({
      show: !this.state.show
    })
  }

  logout() {
    const token = clearToken()
    Axios.post(backend + '/logout', {token: token}).then(x => {
      this.props.handleSession()
    })
  }

  render() {
    return (
      <Dropdown show={this.state.show}  alignRight className="dropdown-small-center" onClick={this.handleChange}>
        <Dropdown.Toggle as={CustomToggle}>
          <img src={this.props.img} className="navbar-user" alt="" />
        </Dropdown.Toggle>
        <Dropdown.Menu as={CustomMenu} >
          <p className="text-center" onClick={this.handleChange}>
            <Link to="/profile">
              <i className="far fa-user-circle fa-fw margin-right-10"></i>
              Profile
            </Link>
          </p>
            <Dropdown.Divider />
          <p className="text-center" onClick={this.handleChange}>
            <Link to="/" onClick={this.logout}>
              <i className="fas fa-door-open fa-fw margin-right-10"></i>
              Logout
            </Link>
          </p>
        </Dropdown.Menu>
      </Dropdown>
    )
  }
}