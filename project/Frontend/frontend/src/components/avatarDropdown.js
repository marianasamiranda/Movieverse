import React, { Component } from 'react';
import Dropdown from 'react-bootstrap/Dropdown'

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
    this.handleClick = this.handleClick.bind(this);
    this.state = { value: '' };
  }

  handleClick(e) {
    //
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
  render() {
    return (
      <div>
        <Dropdown alignRight className="dropdown-small-center">
        <Dropdown.Toggle as={CustomToggle}>
          <img src={this.props.img} className="navbar-user" alt="" />
        </Dropdown.Toggle>
        <Dropdown.Menu as={CustomMenu} >
          <Dropdown.Item>
            <i className="far fa-user-circle fa-fw margin-right-10"></i>
            Profile
          </Dropdown.Item>
            <Dropdown.Divider />
          <Dropdown.Item>
            <i className="fas fa-door-open fa-fw margin-right-10"></i>
            Logout
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
      </div>
    )
  }
}