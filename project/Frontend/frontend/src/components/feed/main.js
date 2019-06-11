import React, { Component } from 'react';
import Post from './post'
import { backend, labels } from '../../var';
import InfiniteScroll from 'react-infinite-scroller';
import Axios from 'axios'
import { getToken } from '../../cookies'
import NothingToPresent from '../aux_pages/nothingToPresent'

//TODO

export default class Main extends Component {

  constructor(props){
    super(props)
    console.log(props)
    this.state = {
      user: props.user,
      currentPage: 0,
      entriesNotRendered: props.entries.slice(8),
      entriesRendered: props.entries.slice(0,8),
      moreEntries: props.moreEntries,
    }
    this.loadItems.bind(this)
  }

  loadItems(page) {

    if(this.state.entriesNotRendered.length > 8){
        let nextEntries = this.state.entriesNotRendered.slice(0,8);
        var entries = this.state.entriesRendered
        nextEntries.map( (entry) => {
            entries.push(entry)
        } )

        this.setState({
            entriesRendered: entries,
            entriesNotRendered: this.state.entriesNotRendered.slice(8)
        })

    }else{
        const token = getToken()
        var url = backend + '/feed-entries/';

        var nextPage = this.state.currentPage + 1
        
        let query = "?page=" + nextPage
        Axios.get( url + query, 
          { headers: { Authorization: "Bearer " + token } }).then(x => {

            var entries = this.state.entriesRendered;
            var nextEntries = x.data.entries.slice(0,8)


            nextEntries.map( (entry) => {
                entries.push(entry)
            } )
            
            this.setState({
                entriesRendered: entries,
                entriesNotRendered: this.state.entriesNotRendered.concat(x.data.entries.slice(8)),
                moreEntries: x.data.moreEntries,
                currentPage: nextPage
            })
        });
    } 
  }

  render() {
    const loader = <div className="loader">{labels[this.props.lang].loading} ...</div>;
    // let entries = []
    
    // this.state.entries.forEach(element => {
    //   entries.push(
    //     <Post
    //       data = {element}
    //     >
    //     </Post>
    //   )
    // });

    return (
      <div className="main">
          <div id="main-feed-title">
            FEED
          </div>
          {
            this.state.entriesRendered.length === 0 ?
              <NothingToPresent lang={this.props.lang}/> :
              <InfiniteScroll
                  pageStart={0}
                  loadMore={this.loadItems.bind(this)}
                  hasMore={this.state.moreEntries}
                  loader={loader}>
                  {
                    this.state.entriesRendered.map(element => {
                      return(
                        <Post
                          data = {element}
                        >
                        </Post>
                      )
                    })
                  }
              </InfiniteScroll>
          }
          {/* <div className="feed-container" style={{display: 'flex', justifyContent: 'center',}}>
            <input type="button" className="button" value={labels[this.props.lang].loadMore + "  ..."}></input>
          </div> */}
          
      </div>
    )  
  }
}