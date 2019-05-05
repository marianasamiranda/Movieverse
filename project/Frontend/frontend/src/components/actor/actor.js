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

export default class Actor extends Component{

    constructor(props){
        super(props)
        this.get_actor_info(props.id)
        this.state = {}
    }

    get_actor_info(id){
    
        return Axios.get(backend + '/member/' + id).then(x => {

          console.log(x.data.biography)
          this.setState({
            biography: x.data.biography,
            birthdate: x.data.birthdate,
            birthplace: x.data.birthplace,
            gender: x.data.gender,
            image: x.data.image,
            imdb: x.data.imdb,
            name: x.data.name
          })
          console.log(this.state)

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
                <div className="container">
                    <div className="row">
                        <ActorAside
                            info = {actor_info}
                            photo = {this.state.image}
                            //photo="http://placehold.it/228x337"
                        >
                        </ActorAside>
                        
                        <ActorMain
                            // Para ecrâs pequenos
                            // photo="http://placehold.it/228x337"
                            // info = {actor_info}
                            // // ======================
                            // name="Tom Hanks"
                            // imdb="https://www.imdb.com/name/nm0000158/"
                            // biography="Thomas Jeffrey Hanks (born July 9, 1956) is an American actor, producer, writer and director. Hanks worked in television and family-friendly comedies, gaining wide notice in 1988's Big, before achieving success as a dramatic actor in several notable roles, including Andrew Beckett in Philadelphia, the title role in Forrest Gump, Commander James A. Lovell in Apollo 13, Captain John H. Miller in Saving Private Ryan, Joe Fox in You've Got Mail and Chuck Noland in Cast Away. Hanks won consecutive Best Actor Academy Awards, in 1993 for Philadelphia and in 1994 for Forrest Gump. U.S. domestic box office totals for his films exceed $3.9 billion."
                            // films="?"
                            
                            info = {actor_info}
                            photo = {this.state.photo}
                            // ======================
                            name={this.state.name}
                            imdb={this.state.imdb}
                            biography={this.state.biography}
                            films={this.state.films}
                        >
                        </ActorMain>
                    </div>
                </div>
            </div>
            // <div>
            //     <div className="container">
            //         <div className="row">
            //             <ActorAside
            //                 info = {actor_info}
            //                 photo="http://placehold.it/228x337"
            //             >
            //             </ActorAside>
                        
            //             <ActorMain
            //                 // Para ecrâs pequenos
            //                 photo="http://placehold.it/228x337"
            //                 info = {actor_info}
            //                 // ======================
            //                 name="Tom Hanks"
            //                 imdb="https://www.imdb.com/name/nm0000158/"
            //                 biography="Thomas Jeffrey Hanks (born July 9, 1956) is an American actor, producer, writer and director. Hanks worked in television and family-friendly comedies, gaining wide notice in 1988's Big, before achieving success as a dramatic actor in several notable roles, including Andrew Beckett in Philadelphia, the title role in Forrest Gump, Commander James A. Lovell in Apollo 13, Captain John H. Miller in Saving Private Ryan, Joe Fox in You've Got Mail and Chuck Noland in Cast Away. Hanks won consecutive Best Actor Academy Awards, in 1993 for Philadelphia and in 1994 for Forrest Gump. U.S. domestic box office totals for his films exceed $3.9 billion."
            //                 films="?"
            //             >
            //             </ActorMain>
            //         </div>
            //     </div>
            // </div>
        )
    }
}