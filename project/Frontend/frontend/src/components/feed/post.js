import React, { Component } from 'react';
import heart from '../../img/heart.svg';
import visibility from '../../img/visibility.svg';
import {avatars} from '../../var'
import moment from 'moment';
import {Link} from 'react-router-dom';
import ReactTextCollapse from 'react-text-collapse';
import {labels} from '../../var'

function ProfilePhoto(props){
  return (
    <div className="avatar">
      <img src={props.src} alt="" />
    </div>
  );
}

function PostHeader(props){
  let user = props.user
  let userId = props.userId
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
              {action}
              <Link to={'/movie/' + movieId}>
                <b className="red">
                {movie}
                </b>
              </Link>
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
      lang: props.lang,
    }
  }

  render() {
    let TEXT_COLLAPSE_OPTIONS = {
      collapse: false,
      collapseText: <p>... {labels[this.props.lang].show_more}</p>,
      expandText: <p>{labels[this.props.lang].show_less}</p>,
      minHeight: 78,
      maxHeight: 180,
      textStyle: {
        color: 'red'
      }
    }

    return (
      <div className="post-comment">
        <ReactTextCollapse options={TEXT_COLLAPSE_OPTIONS}>
          <p>"{this.state.content}"</p>
        </ReactTextCollapse>
      </div>
    );
  }
  // render() {
  //   return (
  //     <div className="post-comment">
  //         <p id="comment" className={this.state.comment_class} style={this.state.comment_style}>"{this.state.content}"</p>
  //         <p id="show_more" className="red" onClick={this.show_more} style={this.state.show_more_style}>Show more</p>
  //       </div>
  //   );
  // }

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
        <td>
          <div className="gold-bgimg-star"></div>
        </td>)
    }else{
      items.push(
        <td>
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
    console.log(this.state.data.rating)
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

    date = moment(this.state.data.timestmp).format('MM/DD/YYYY h:mm');

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