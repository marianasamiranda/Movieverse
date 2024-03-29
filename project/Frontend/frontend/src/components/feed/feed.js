import React, { Component } from 'react';
import { getToken } from '../../cookies'
import NoAuthError from '../aux_pages/noAuthError'
import Container from 'react-bootstrap/Container';
import Main from './main'
import Aside from './aside'
import '../../styles/Feed.css'
import Axios from 'axios'
import Loading from '../aux_pages/loading'
import {backend} from '../../var'

export default class Feed extends Component {
    
    constructor(props){
      super(props)
      this.state = {
        user: props.user
      }
      this.getFeedInfo.bind(this)
    }

    componentWillMount() { 
      this.getFeedInfo(this.state.user) 
    };

    async getFeedInfo(user) {
      const token = getToken()
      let query = ""
  
      return Axios.get(backend + '/user/feed' + query, 
          { headers: { Authorization: "Bearer " + token } }).then(x => {
        
        this.setState({
          upcomings: x.data['upcomingMovies'],
          feedEntries: x.data['feedEntries'],
          moreEntries: x.data['moreEntries'],
          user: x.data['self'] ? undefined : user
        })
      })
      .catch(x => {
        if (x.response.data === 'Wrong token') {
          this.setState({
            noAuth: true
          })
        }
        else {
          this.setState({
            noUser: true
          })
        }
      })
    }

    render() {
      if (this.state.noAuth) {
        return (
          <NoAuthError lang={this.props.lang}/>
        )
      }
      else if (!this.state.upcomings) {
        return (
          <Loading lang={this.props.lang}/>
        )
      }

      return (

        <div>
            <Container className="flex">
                <Main
                  user={this.state.user}
                  lang={this.props.lang}
                  entries={this.state.feedEntries}
                  moreEntries={this.state.moreEntries}
                >
                </Main>
          
                <Aside
                  movies={this.state.upcomings}
                  lang={this.props.lang}
                >
                </Aside>
            </Container>
        </div>
        )
    }
  }