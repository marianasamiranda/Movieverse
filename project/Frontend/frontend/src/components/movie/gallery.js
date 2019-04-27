import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'

export default class Gallery extends Component {
    constructor(props) {
        super(props);
    }

    createGallery = () => {
        let gallery = []
        let i = 0

        if(this.props.type == 'video') {
            this.symbol = <i className="fas fa-play-circle fa-5x"></i>;
        }
        else if (this.props.type == 'image') {
            this.symbol = <i className="fas fa-search fa-5x"></i>;
        }

        while (i+1 < this.props.data.length) {
            gallery.push(<Row>
                <Col lg="6">
                    <div className="galleryItem">
                        <div className="galleryContainer">
                            <Image className="image" src={this.props.data[i].src} />
                            <a target='_blank' href={this.props.data[i].href} className="overlay">
                                <div className="text">{this.symbol}</div>
                            </a>
                        </div>
                    </div>
                </Col>
                <Col lg="6">
                    <div className="galleryItem">
                        <div className="galleryContainer">
                            <Image className="image" src={this.props.data[i].src} />
                            <a target='_blank' href={this.props.data[i].href} className="overlay">
                                <div className="text">{this.symbol}</div>
                            </a>
                        </div>
                    </div>
                </Col>
            </Row>)
            i++;
        }
        if (i < this.props.data.length) {
            gallery.push(<Row>
                <Col lg="6">
                    <div className="galleryItem">
                        <div className="galleryContainer">
                            <Image className="image" src={this.props.data[i].src} />
                            <a target='_blank' href={this.props.data[i].href} className="overlay">
                                <div className="text">{this.symbol}</div>
                            </a>
                        </div>
                    </div>
                </Col>
            </Row>)
        }
        return gallery
    }

    render() {
        return <Container>
            {this.createGallery()}
        </Container>
    
    }
}