import React, { Component } from 'react';
import Comment from './comment'
import ResizableTextarea from './resizable-text-area'
import '../../styles/Comment.css';
import Axios from 'axios';
import { backend, labels } from '../../var'
import { getToken } from '../../cookies'
import { getCurrentDate } from '../../utils';
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
  }

  componentDidMount() {
    let self = this

    Axios.get(backend + '/movie/' + this.state.movieId + '/comments/' + this.state.currentPage)
    .then(function(response) {
      let newComments = [];

      response.data.comments.forEach(function(comment) {
        let dsplit = new Date(comment.date).toISOString().split(/[T:]/)
        let date = dsplit[0] + " " + dsplit[1] + ":" + dsplit[2];
        newComments.push(<Comment key={comment.id} profilepic={`/avatars/` + comment.userAvatar} author={comment.username} time={date} content={comment.content} likes={comment.likes} />);
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
    let nextPage = this.state.currentPage + 1
    this.setState({
      currentPage: nextPage
    });

  }
  
  loadItems() {
    let self = this

    Axios.get(backend + '/movie/' + this.state.movieId + '/comments/' + this.state.currentPage)
    .then(function(response) {
      let newComments = [];

      response.data.comments.forEach(function(comment) {
          let date = new Date(comment.date);
          date = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear()
          newComments.push(<Comment key={comment.id} profilepic={`/avatars/` + comment.userAvatar} author={comment.username} time={date} content={comment.content} likes={comment.likes} />);
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
      let element = <Comment key={response.data.id} profilepic={`/avatars/` + response.data.userAvatar} author={response.data.username} time={response.data.date} content={response.data.content} likes={response.data.likes} />;
      self.setState(prevState => ({
        comments: [element, ...prevState.comments ]
      }))
    }).catch((e) =>
      console.log(e)
    )

    this.forceUpdate()

  };
  
  render() {

    const loader = <div className="loader">{labels[this.props.lang].loading} ...</div>;

    return <div>
      <div className="comment-container" style={{ 'marginBottom': '30px'}}>
				<ResizableTextarea noAuth={ this.state.noAuth } callBackFromParent={ this.getComment } lang={this.props.lang} />
		  </div>
      <InfiniteScroll
        pageStart={0}
        loadMore={this.loadItems.bind(this)}
        hasMore={this.state.moreComments}
        loader={loader}>
        { this.state.comments }
      </InfiniteScroll>
		</div>
	}
}