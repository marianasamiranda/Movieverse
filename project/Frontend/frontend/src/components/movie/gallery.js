import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'

export default class Gallery extends Component {

  constructor(props) {
    super(props);

    this.state = {
      columnSize: Math.floor(12/this.props.numberColumns)
    }
  }
    
  render() {

    let symbol;

    if(this.props.type === 'video') {
      symbol = <i className="fas fa-play-circle fa-5x"></i>;
    }
    else if (this.props.type === 'image') {
      symbol = <i className="fas fa-search fa-5x"></i>;
    }
    
    let self=this

    return <Container>
      <Row>
        { this.props.data.map(function(content, i) { 
          return <Col key={i} lg={self.state.columnSize}>
            <div className="galleryItem">
              <div className="galleryContainer">
                <Image className="image" src={ content.src } />
                <a target='_blank' rel="noopener noreferrer" href={ content.href } className="overlay">
                  <div className="text">{ symbol }</div>
                </a>
              </div>
            </div>
          </Col>
        })}
      </Row>
    </Container>
  }
}