import React, { Component } from 'react';
import Dropdown from 'react-bootstrap/Dropdown'
import { Link } from 'react-router-dom'
import { clearToken } from '../cookies'
import Axios from 'axios'
import { backend, labels } from '../var'
import FriendRequests from './friendRequests'
import onClickOutside from 'react-onclickoutside'

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

class AvatarDropdown extends Component {

  constructor(props) {
    super(props)
    this.props.getAvatar()
    this.state = {
      show: false,
      showRequests: false
    }
    this.handleChange = this.handleChange.bind(this)
    this.logout = this.logout.bind(this)
    this.handleShowRequests = this.handleShowRequests.bind(this)
  }

  handleChange() {
    this.setState({
      show: !this.state.show,
    })
  }

  handleShowRequests() {
    this.setState({
      showRequests: !this.state.showRequests
    })
  }

  handleClickOutside(){
    this.setState({
      show: false,
    })
  }

  logout() {
    const token = clearToken()
    Axios.post(backend + '/logout', {}, { headers: { Authorization: "Bearer " + token } }).then(x => {
      this.props.handleSession()
    })
  }

  clearNotification() {
    this.props.clearNotification()
  }

  render() {
    return (
      <>
      <Dropdown show={this.state.show}  alignRight className="dropdown-small-center" onClick={this.handleChange}>
        <Dropdown.Toggle as={CustomToggle}>
          <img src={this.props.img} className="navbar-user" alt="" />
          {this.props.requests ? (<mark className="notification-circle" />) : ""} />
        </Dropdown.Toggle>
        <Dropdown.Menu as={CustomMenu} >
          <p className="text-center" onClick={() => {this.handleChange(); this.props.handleExpand()}}>
            <Link to="/profile">
              <i className="far fa-user-circle fa-fw margin-right-10" />
               {labels[this.props.lang].profile}
            </Link>
          </p>
          <p className="text-center" onClick={() => { this.handleShowRequests();this.handleChange(); this.props.handleExpand()}}>
            <Link to="#">
              <i className="far fa-user-circle fa-fw margin-right-10" />
                {labels[this.props.lang].requests}
            </Link>
          </p>
            <Dropdown.Divider />
          <p className="text-center" onClick={() => {this.handleChange(); this.props.handleExpand()}}>
            <Link to="/" onClick={this.logout}>
              <i className="fas fa-door-open fa-fw margin-right-10" />
                {labels[this.props.lang].logout}
            </Link>
          </p>
        </Dropdown.Menu>
      </Dropdown>
      <FriendRequests 
        show={this.state.showRequests} 
        handleShowRequests={this.handleShowRequests} 
        lang={this.props.lang} 
        clearNotification={this.props.clearNotification}
        />
      </>
    )
  }
}

export default onClickOutside(AvatarDropdown)