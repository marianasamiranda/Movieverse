import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import MovieCard from './movie-card'
import logo from '../img/logo.png'
import Main from './main'
import Aside from './aside'
import '../styles/Feed.css'

export default class Feed extends Component {
    render() {
      return (
        <div>
            <Container className="flex">
                <Main>

                </Main>

                <Aside>

                </Aside>
            </Container>
        </div>
        )
    }
  }