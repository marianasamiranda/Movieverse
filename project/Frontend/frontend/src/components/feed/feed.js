import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Main from './main'
import Aside from './aside'
import '../../styles/Feed.css'

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