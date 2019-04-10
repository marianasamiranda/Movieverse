import React, { Component } from 'react';

export default class Flag extends Component{
  render() {
    const name = this.props.country.toUpperCase()
    return (
      <img className="flag-icon" src={require('../img/flags/' + name + '.png')} />
    )
  }
}