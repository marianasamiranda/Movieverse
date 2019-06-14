import React, { Component } from 'react';
import Comment from './comment'
import ResizableTextarea from './resizable-text-area'
import '../../styles/Comment.css';
import Axios from 'axios';
import { backend, labels } from '../../var'
import { getToken } from '../../cookies'
import { getCurrentDate } from '../../utils';
import moment from 'moment';
import InfiniteScroll from 'react-infinite-scroller';


export default class DiscussionBox extends Component {

  constructor(props) {
    super(props);
    
    this.state = {
      movieId: this.props.movie,
      noAuth: this.props.noAuth,
      moreComments: false,
      currentPage: 0,
      comments: []
    }

    this.loadItems();
  }
  
  loadItems() {
    let self = this

    var header = {}
    var token = getToken()

    if(token !== undefined) {
      header = { headers: { Authorization: "Bearer " + token } }
    }

    Axios.get(backend + '/movie/' + this.state.movieId + '/comments/' + this.state.currentPage,
      header)
    .then(function(response) {
      let newComments = [];

      response.data.comments.forEach(function(comment) {
        comment["date"] = moment(comment.date).format("YYYY-MM-DD HH:mm")
        newComments.push(comment);
      });
      
      newComments = self.state.comments.concat(newComments)
      self.setState({
        comments: newComments,
        moreComments: response.data.moreComments,
        currentPage: self.state.currentPage + 1
      });
    }).catch((e) =>
      console.log(e)
    )
  }

  getComment = (newComment) => {

    var f = {}

    f['message'] = newComment
    f['date'] = getCurrentDate()

    let self=this

    Axios.post(backend + '/movie/' + this.state.movieId + '/comment',
      f,
      { headers: { Authorization: "Bearer " + getToken() } })
    .then(function(response) {
      
      let element = {
        'id': response.data.id,
        'userAvatar': response.data.userAvatar,
        'username': response.data.username,
        'date': response.data.date,
        'content': response.data.content,
        'likes': response.data.likes,
        'isLiked': false,
        'numberReplies': 0
      }

      self.setState(prevState => ({
        comments: [element, ...prevState.comments]
      }))
    }).catch((e) =>
      console.log(e)
    )
  }
  
  render() {

    const loader = <div key={0} className="loader">{labels[this.props.lang].loading}... </div>;

    return <div>
      <div className="comment-container" style={{ 'marginBottom': '30px'}}>
				<ResizableTextarea noAuth={ this.state.noAuth } callBackFromParent={ this.getComment } lang={this.props.lang} />
		  </div>
      <InfiniteScroll
        pageStart={0}
        loadMore={this.loadItems.bind(this)}
        hasMore={this.state.moreComments}
        loader={ loader }>
        {
          this.state.comments.map(comment => {
            return (
              <Comment key={ comment.id } id={ comment.id } profilepic={ `/avatars/` + comment.userAvatar } author={ comment.username } time={ comment.date } content={ comment.content } likes={ comment.likes } isLiked={ comment.isLiked } numberReplies={ comment.numberReplies } noAuth={ this.state.noAuth } lang={ this.props.lang } />
            )}
          )
        }
      </InfiniteScroll>
		</div>
	}
}