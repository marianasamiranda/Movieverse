import React, { Component } from 'react';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger'
import { badges_location } from '../../var';

const renderTooltip = (props, info) => (
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

export default class Badge extends Component {
  render() {
    return (
      <OverlayTrigger 
        placement="top" 
        delay={{ show: 10, hide: 100 }}
        overlay={(props) => renderTooltip(props, this.props.info)}>
        <img src={badges_location + this.props.img} 
            alt="badge"
            className={(this.props.collected ? "" : " faded")} />
      </OverlayTrigger>
    )
  }
}