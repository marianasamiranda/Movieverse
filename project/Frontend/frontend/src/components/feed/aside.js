import React, { Component } from 'react';
import MovieCard from '../movie-card'

//TODO

export default class Aside extends Component {
  render() {
    return (
      <div className="aside-container">
        <div id="aside-feed-title">
          NEW RELEASES
        </div>
        <div className="aside">
          <div className="row">
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
          </div>
      </div>
    )  
  }
}