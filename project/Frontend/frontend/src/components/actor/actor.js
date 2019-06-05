import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import MovieCard from '../movie-card'
import logo from '../../img/logo.png'
import Axios from 'axios'
import ActorMain from './actor-main'
import ActorAside from './actor-aside'
import ActorInfo from './actor-info'
import {backend, labels} from '../../var'
import '../../styles/Actor.css'
import Loading from '../aux_pages/loading'

export default class Actor extends Component{

    constructor(props){
        super(props)
        this.state = {
            id: props.match.params.id,
            showLoader: true
        }
        this.get_actor_info.bind(this)
    }

    componentWillMount() { 
        this.get_actor_info(this.state.id) 
    };

    async get_actor_info(id){

        return await Axios.get(backend + '/member/' + id).then(x => {

          var backdrops = []  
          x.data.backdrops.map(path => {
            let shortImage = "https://image.tmdb.org/t/p/w200" + path
            let largeImage = "https://image.tmdb.org/t/p/w500" + path
            backdrops.push({"href": largeImage, "src": shortImage})
          })

          this.setState({
            showLoader: false,
            biography: x.data.biography,
            birthdate: x.data.birthdate,
            birthplace: x.data.birthplace,
            gender: x.data.gender,
            image: x.data.image,
            imdb: x.data.imdb,
            name: x.data.name,
            movies: x.data.movies,
            moreMovies: x.data.moreMovies,
            backdrops: backdrops
          })
        })
        .catch(x => {alert("Erro")})
    }

    render(){

        let actor_info = (
            <ActorInfo
                gender={this.state.gender}
                birthday={this.state.birthdate}
                birthplace={this.state.birthplace}
                lang={this.props.lang}
            >
            </ActorInfo>
        )

        return (

            <div>
                {
                    this.state.showLoader ? 
                        <Loading lang={this.props.lang}/>
                    : (
                        <div className="container">
                            <div className="row">
                                <ActorAside
                                    info={actor_info}
                                    photo={this.state.image}
                                >
                                </ActorAside>
                                
                                <ActorMain
                                    info={actor_info}
                                    photo={this.state.image}
                                    // ======================
                                    name={this.state.name}
                                    imdb={this.state.imdb}
                                    biography={this.state.biography}
                                    backdrops={this.state.backdrops}
                                    movies={this.state.movies}
                                    moreMovies={this.state.moreMovies}
                                    id={this.state.id}
                                    lang={this.props.lang}
                                >
                                </ActorMain>
                            </div>
                        </div>
                    )
                }
                
            </div>
        )
    }
}