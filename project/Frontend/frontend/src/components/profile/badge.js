import React, { Component } from 'react';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger'

const renderTooltip = props => (
  <div 
    {...props}
    style={{
      backgroundColor: 'rgba(0, 0, 0, 0.85)',
      padding: '2px 10px',
      color: 'white',
      borderRadius: 3,
      ...props.style,
    }}>
    {info}
  </div>
);

let info

export default class Badge extends Component {
  render() {
    info = this.props.info
    return (
      <OverlayTrigger 
        placement="top" 
        delay={{ show: 10, hide: 100 }} 
        overlay={renderTooltip}>
        <img src={require('../../img/badges/' + this.props.name + '.svg')} 
            alt="badge"
            className={(this.props.collected ? "" : " faded")} />
      </OverlayTrigger>
    )
  }
}