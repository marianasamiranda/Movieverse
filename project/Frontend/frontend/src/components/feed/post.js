import React, { Component } from 'react';
import heart from '../../img/heart.svg';
import visibility from '../../img/visibility.svg';
import {avatars} from '../../var'
import moment from 'moment';
import {Link} from 'react-router-dom';
import ReactTextCollapse from 'react-text-collapse';
import {labels, screen_size_content_limit} from '../../var'

function ProfilePhoto(props){
  return (
    <div className="avatar">
      <img src={props.src} alt="" />
    </div>
  );
}

function PostHeader(props){
  let user = props.user
  let movie = props.movie
  let movieId = props.movieId
  let date = props.date
  let action = props.action
  let suffix = props.suffix
  

  return (
    <div className="flex post-header">
      <div className="vertical-align" style={{paddingRight:"10px"}}>
        <Link to={'/u/' + user}>
          <ProfilePhoto src={props.photo}></ProfilePhoto>
        </Link>
      </div>
      <div>
        <div className="vertical-align">
          <div>
            <div>
              <Link to={'/u/' + user}>
                <b className="red">
                  {user}
                </b>
              </Link>
              {' ' + action + ' '}
              <Link to={'/movie/' + movieId}>
                <b className="red">
                {movie}
                </b>
              </Link>
              &ensp;
              {suffix}
            </div>
            <div>
              {date}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

class PostBodyComment extends Component{

  constructor(props) {
    super(props);
    this.state = {
      content: props.content,
      content_length: props.content.length,
      lang: props.lang,
      show_more: false
    }
  }

  /**
   * Calculate & Update state of new dimensions
   */
  handleResize() {
    var limitContent, collapse_max_height, collapse_min_height;
    let wsize = window.innerWidth;

    for(var i = screen_size_content_limit.length - 1; i >= 0; i--){
      if(screen_size_content_limit[i]['wsize'] < wsize){
        limitContent = screen_size_content_limit[i]['limit']
        collapse_max_height = screen_size_content_limit[i]['maxh']
        collapse_min_height = screen_size_content_limit[i]['minh']
        break;
      }
    }

    if(this.state.content_length > limitContent){
      this.setState({
        show_more: true,
        collapse_max_height: collapse_max_height,
        collapse_min_height: collapse_min_height
      })
    }else{
      this.setState({
        show_more: false
      })
    }
  }

  /**
   * Add event listener
   */
  componentDidMount() {
    this.handleResize();
    window.addEventListener("resize", this.handleResize.bind(this));
  }

  /**
   * Remove event listener
   */
  componentWillUnmount() {
    window.removeEventListener("resize", this.handleResize.bind(this));
  }

  render() {

    let TEXT_COLLAPSE_OPTIONS = {
      collapse: false,
      collapseText: <p>... {labels[this.props.lang].show_more}</p>,
      expandText: <p>{labels[this.props.lang].show_less}</p>,
      minHeight: this.state.collapse_min_height,
      maxHeight: this.state.collapse_max_height,
      textStyle: {
        color: 'red'
      }
    }

    return (
      <div className="post-comment">
        {
          this.state.show_more?
            <ReactTextCollapse options={TEXT_COLLAPSE_OPTIONS}>
              <p>"{this.state.content}"</p>
            </ReactTextCollapse>
          :
          <p>"{this.state.content}"</p>
        }
        
      </div>
    );
  }

}

function PostBodyFavorite(props){
  
  return (
    <div className="vertical-align-column">
      <div className="container align-contents-center">
        <object style={{width: "40%", height:"100%"}} type="image/svg+xml" data={heart}>
          Your browser does not support SVG
        </object>
      </div>
      <div className="body-caption align-contents-center">
        Favorite
      </div>
    </div>
  );
}

function PostBodyView(props){
  
  return (
    <div className="vertical-align-column">
      <div className="container align-contents-center">
        <object style={{width: "40%", height:"100%"}} type="image/svg+xml" data={visibility}>
          Your browser does not support SVG
        </object>
      </div>
      <div className="body-caption align-contents-center">
        View
      </div>
    </div>
  );
}

function PostBodyRating(props){
  const feelings = ["BAD", "MEH", "OK", "GOOD", "WOW"];
  let rating = parseInt(props.rate);
  let feeling = feelings[rating - 1]
  const items = []

  
  for(var i = 0; i < 5; i++){
    if(i < rating){
      items.push(
        <td key={i}>
          <div className="gold-bgimg-star"></div>
        </td>)
    }else{
      items.push(
        <td key={i}>
          <div className="black-bgimg-star"></div>
        </td>)
    }
  }

  return (
    <div className="vertical-align-column">
      <div className="static-rating container">
        <table id="rating-table">
          <tr>
            {items}
          </tr>
        </table>
      </div>
      <div className="body-caption align-contents-center">
        {feeling}
      </div>
    </div>
  );
}

function PostBody(props){
  let content

  switch(props.type) {
    case "comment":
      content = (
        <PostBodyComment 
          content={props.data.comment}
          commentId={props.data.commentid}
          lang={props.lang}>
        </PostBodyComment>
      );
      break;
    case "favorite":
      content = (
        <PostBodyFavorite></PostBodyFavorite>
      );
      break;
    case "view":
      content = (
        <PostBodyView></PostBodyView>
      );
      break;
    case "view_rate":
      content = (
        <PostBodyRating 
          rate={props.data.rating}
        >
        </PostBodyRating>
      );
      break;
    default:
      break;
  }

  return (
    <div className="container flex">
      <div className="row full-width">
        <div className="col-4">
          <div className="movie-card-container">
            <Link to={'/movie/' + props.data.movieid}>
              <a href={props.poster['href']}><img className="movie-card" src={props.poster['src']} alt="" /></a>
            </Link>
          </div>
        </div>
        <div className="col-8">
          {content}
        </div>
      </div>
    </div>
  );
}

export default class Post extends Component {

  constructor(props){
    super(props)
    this.state = {
      data: props.data
    }
  }

  render() {
    var type, action, suffix, movieposter, date;
    var avatar = avatars + this.state.data.avatar;
    switch(this.state.data.type){
      case 0:
        type = "view_rate";
        action = labels[this.props.lang].view_rate;
        break;
      case 1:
        type = "view";
        action = labels[this.props.lang].view;
        break;
      case 2:
        type = "favorite";
        action = labels[this.props.lang].favorite_pre;
        suffix = labels[this.props.lang].favorite_suf;
        break;
      case 3:
        type = "comment";
        action = labels[this.props.lang].comment;
        break;
      default:
        break;
    }

    if (this.state.data.movieposter){
      let shortImage = "https://image.tmdb.org/t/p/w200" + this.state.data.movieposter
      let largeImage = "https://image.tmdb.org/t/p/w500" + this.state.data.movieposter
      movieposter = {"href": largeImage, "src": shortImage}
    }else{
      movieposter = {"href": "http://placehold.it/228x337", "src": "http://placehold.it/228x337"}
    }

    date = moment(this.state.data.timestmp).format('DD/MM/YYYY HH:mm');

    return (
      <div className="post-container">
          <PostHeader
            userId={this.state.data.userid}
            user={this.state.data.username}
            action={action}
            suffix={suffix}
            movieId={this.state.data.movieid}
            movie={this.state.data.moviename}
            date={"🕒 " + date}
            photo={avatar}>
          </PostHeader>
          <PostBody
            lang={this.props.lang}
            poster={movieposter}
            type={type}
            data={this.state.data}
            >
          </PostBody> 
      </div>
    )  
  }
}
