import React, { Component } from 'react';
import MovieCard from '../movie-card'
import {labels} from '../../var'

export default class Aside extends Component {

  constructor(props){
    super(props)
    this.state = {
      movies: props.movies,
    }
  }

  render() {
    return (
      <div className="aside-container">
        <div id="aside-feed-title">
          {labels[this.props.lang].newReleasesU}
        </div>
        <div className="aside">
          <div className="row">
              <div className="col-6 aside-col">
                {this.state.movies.length > 0?
                  <MovieCard 
                    img={"https://image.tmdb.org/t/p/w200/" + this.state.movies[0].poster}
                    title={this.state.movies[0].name}
                    info={this.state.movies[0].release} 
                    id={this.state.movies[0].id}/>
                  :""
                }
              </div>
              <div className="col-6 aside-col">
                {this.state.movies.length > 1?
                  <MovieCard 
                    img={"https://image.tmdb.org/t/p/w200/" + this.state.movies[1].poster}
                    title={this.state.movies[1].name}
                    info={this.state.movies[1].release} 
                    id={this.state.movies[1].id}/>
                    :""
                  }
              </div>
          </div>
          <div className="row">
              <div className="col-6 aside-col">
                {this.state.movies.length > 2?
                  <MovieCard 
                    img={"https://image.tmdb.org/t/p/w200/" + this.state.movies[2].poster}
                    title={this.state.movies[2].name}
                    info={this.state.movies[2].release} 
                    id={this.state.movies[2].id}/>
                    :""
                  }
              </div>
              <div className="col-6 aside-col">
                {this.state.movies.length > 3?
                  <MovieCard 
                    img={"https://image.tmdb.org/t/p/w200/" + this.state.movies[3].poster}
                    title={this.state.movies[3].name}
                    info={this.state.movies[3].release}
                    id={this.state.movies[3].id}/>
                    :""
                  }
              </div>
            </div>
          </div>
          {/* <div className="row">
              <div className="col-6 aside-col">
                <MovieCard img="http://placehold.it/228x337" title="Alita: Battle Angel" info="(dd/mm/yyyy)" />
              </div>
              <div className="col-6 aside-col">
                <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
              </div>
          </div>
          <div className="row">
              <div className="col-6 aside-col">
                <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
              </div>
              <div className="col-6 aside-col">
                <MovieCard img="http://placehold.it/228x337" title="Movie Title" info="(dd/mm/yyyy)" />
              </div>
            </div>
          </div> */}
      </div>
    )  
  }
}