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
      console.log(user)
      const token = getToken()
      let query = ""
      if (user) {
        query = "?username=" + user
      }
  
      return Axios.get(backend + '/user/feed' + query, 
          { headers: { Authorization: "Bearer " + token } }).then(x => {
        
        console.log(x.data)
        console.log(x.data['upcomingMovies'])
        console.log(x.data['feedEntries'])
        console.log(x.data['moreEntries'])
        this.setState({
          upcomings: x.data['upcomingMovies'],
          feedEntries: x.data['feedEntries'],
          moreEntries: x.data['moreEntries'],
          user: x.data['self'] ? undefined : user
        })
        console.log(x.data)
      })
      .catch(x => {
        console.log(x)
        if (x.response.data === 'Wrong token') {
          this.setState({
            noAuth: true
          })
        }
        else {
          console.log(x)
          this.setState({
            noUser: true
          })
        }
      })
    }

    render() {
      if (this.state.noAuth) {
        return (
          <NoAuthError/>
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
                  lang={this.props.lang}
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