import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import MovieCard from '../movie-card'
import logo from '../../img/logo.png'
import Axios from 'axios'
import CompanyMain from './company-main'
import CompanyAside from './company-aside'
import CompanyInfo from './company-info'
import {backend, labels} from '../../var'
import '../../styles/MemberCompany.css'
import Loading from '../aux_pages/loading'

export default class Company extends Component{

    constructor(props){
        super(props)
        this.state = {
            id: props.match.params.id,
            showLoader: true
        }
        this.get_company_info.bind(this)
    }

    componentWillMount() { 
        this.get_company_info(this.state.id) 
    };

    async get_company_info(id){
        return await Axios.get(backend + '/company/' + id).then(x => {
          console.log(x)
          this.setState({
            showLoader: false,
            description: x.data.description,
            homepage: x.data.homepage,
            headquarters: x.data.headquarters,
            country: x.data.country,
            image: x.data.image,
            name: x.data.name,
            movies: x.data.movies,
            moreMovies: x.data.moreMovies
          })
        })
        .catch(x => {alert("Erro")})
    }

    render(){

        let company_info = (
            <CompanyInfo
                name={this.state.name}
                country={this.state.country}
                headquarters={this.state.headquarters}
                homepage={this.state.homepage}
                lang={this.props.lang}
            >
            </CompanyInfo>
        )

        return (

            <div>
                {
                    this.state.showLoader ? 
                        <Loading lang={this.props.lang}/>
                    : (
                        <div className="container">
                            <div className="row">
                                <CompanyAside
                                    info={company_info}
                                    photo={this.state.image}
                                >
                                </CompanyAside>
                                
                                <CompanyMain
                                    info={company_info}
                                    photo={this.state.image}
                                    // ======================
                                    name={this.state.name}
                                    description={this.state.description}
                                    movies={this.state.movies}
                                    moreMovies={this.state.moreMovies}
                                    id={this.state.id}
                                    lang={this.props.lang}
                                >
                                </CompanyMain>
                            </div>
                        </div>
                    )
                }
                
            </div>
        )
    }
}