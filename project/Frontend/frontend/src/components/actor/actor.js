import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import MovieCard from '../movie-card'
import logo from '../../img/logo.png'
import Axios from 'axios'
import ActorMain from './actor-main'
import ActorAside from './actor-aside'
import ActorInfo from './actor-info'
import {backend} from '../../var'
import '../../styles/Actor.css'
import Loading from '../aux_pages/loading'

export default class Actor extends Component{

    constructor(props){
        super(props)
        this.state = {
            id: props.id,
            showLoader: true
        }
        this.get_actor_info.bind(this)
    }

    componentWillMount() { 
        this.get_actor_info(this.props.id) 
    };

    async get_actor_info(id){

        return await Axios.get(backend + '/member/' + id).then(x => {

          this.setState({
            showLoader: false,
            biography: x.data.biography,
            birthdate: x.data.birthdate,
            birthplace: x.data.birthplace,
            gender: x.data.gender,
            image: x.data.image,
            imdb: x.data.imdb,
            name: x.data.name,
            movies: x.data.movies
          })
        })
        .catch(x => {alert("WTFF")})
    }

    render(){

        let actor_info = (
            // <ActorInfo
            //     gender="M"
            //     birthday="1956-07-09"
            //     birthplace="Concord, California, USA"
            // >
            // </ActorInfo>
            <ActorInfo
                gender={this.state.gender}
                birthday={this.state.birthdate}
                birthplace={this.state.birthplace}
            >
            </ActorInfo>
        )

        return (

            <div>
                {
                    this.state.showLoader ? 
                        <Loading/>
                    : (
                        <div className="container">
                            <div className="row">
                                <ActorAside
                                    info = {actor_info}
                                    photo = {this.state.image}
                                >
                                </ActorAside>
                                
                                <ActorMain
                                    info = {actor_info}
                                    photo = {this.state.image}
                                    // ======================
                                    name={this.state.name}
                                    imdb={this.state.imdb}
                                    biography={this.state.biography}
                                    movies={this.state.movies}
                                    id={this.state.id}
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