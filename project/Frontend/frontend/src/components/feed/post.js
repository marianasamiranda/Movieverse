import React, { Component } from 'react';
import heart from '../../img/heart.svg';
import visibility from '../../img/visibility.svg';
import {avatars} from '../../var'
import moment from 'moment';

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
  let date = props.date
  let action = props.action
  let suffix = props.suffix

  // if (props.action === "comment")
  //   action = " has commented on "
  // else if (props.action === "view")
  //   action = " has watched "
  // else if (props.action === "rate")
  //   action = " has rated "
  // else if (props.action === "favorite")
  //   action = " has favourited "
    

  return (
    <div className="flex post-header">
      <div className="vertical-align" style={{paddingRight:"10px"}}>
        <ProfilePhoto src={props.photo}></ProfilePhoto>
      </div>
      <div>
        <div className="vertical-align">
          <div>
            <div>
              <b className="red">{user}</b>{action}<b className="red">{movie}</b>{suffix}
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
      comment_class: "body-text",
      comment_style: {},
      show_more_style: {display: "block"}
    }
  }

  show_more = (e) => {
    this.setState({
      comment_style: {maxHeight: 'none'},
      show_more_style: {display: 'none'} 
    })
  }

  render() {
    return (
      <div className="post-comment">
          <p id="comment" className={this.state.comment_class} style={this.state.comment_style}>"{this.state.content}"</p>
          <p id="show_more" className="red" onClick={this.show_more} style={this.state.show_more_style}>Show more</p>
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
          content={props.data.content}>
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
      <div className="row">
        <div className="col-4">
          <div className="movie-card-container">
            <a href={props.poster['href']}><img className="movie-card" src={props.poster['src']} alt="" /></a>
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
        action = " has viewed and rated ";
        break;
      case 1:
        type = "view";
        action = " has watched ";
        break;
      case 2:
        type = "favorite";
        action = " has added ";
        suffix = " to Favorites";
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
            user={this.state.data.username}
            action={action}
            suffix={suffix}
            movie={this.state.data.moviename}
            date={"🕒 " + date}
            photo={avatar}>
          </PostHeader>
          <PostBody
            poster={movieposter}
            type={type}
            data={this.state.data}
            >
          </PostBody> 
          {/* <PostBody
            poster="http://placehold.it/228x337"
            type="favorite"
            >
          </PostBody> */}
          {/* <PostBody
            poster="http://placehold.it/228x337"
            type="comment"
            // content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad"
            content="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut e adjkkkljk minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            >
          </PostBody> */}
      </div>
    )  
  }
}