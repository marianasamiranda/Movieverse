import React, { Component } from 'react';
import Axios from 'axios'
import CompanyMain from './company-main'
import CompanyAside from './company-aside'
import CompanyInfo from './company-info'
import {backend} from '../../var'
import '../../styles/MemberCompany.css'
import Loading from '../aux_pages/loading'
import NotFoundError from '../aux_pages/notFoundError'

export default class Company extends Component{

    constructor(props){
        super(props)
        this.state = {
            id: props.match.params.id,
            showLoader: true,
            error: false
        }
        this.get_company_info.bind(this)
    }

    componentWillMount() { 
        this.get_company_info(this.state.id) 
    };

    async get_company_info(id){
        return await Axios.get(backend + '/company/' + id).then(x => {
          this.setState({
            showLoader: false,
            description: x.data.description,
            homepage: x.data.homepage,
            headquarters: x.data.headquarters,
            country: x.data.country,
            image: 'https://image.tmdb.org/t/p/w500/' + x.data.image,
            name: x.data.name,
            movies: x.data.movies,
            moreMovies: x.data.moreMovies
          })
          
        })
        .catch(x => this.setState({
            error: true
        }))
    }

    render(){

        if (this.state.error) {
            return <NotFoundError lang={this.props.lang} />
        }

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