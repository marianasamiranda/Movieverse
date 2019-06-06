import React, { Component } from 'react';
import imdb from '../../img/imdb.svg';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import MovieCard from '../movie-card'
import HorizontalSlider from '../horizontal-slider';
import InfiniteScroll from 'react-infinite-scroller';
import {backend, labels} from '../../var'
import Axios from 'axios'


export default class MemberMain extends Component{

    constructor(props){
        super(props)

        this.state = {
            id: props.id,
            currentPage: 0,
            name: props.name,
            imdb: props.imdb,
            photo: props.photo,
            biography: props.biography,
            info: props.info,
            moviesNotRendered: props.movies.slice(8),
            moviesRendered: props.movies.slice(0,8),
            moreMovies: props.moreMovies,
            backdrops: props.backdrops
        }
        this.loadItems.bind(this)
    }

    loadItems(page) {

        if(this.state.moviesNotRendered.length > 8){
            let nextMovies = this.state.moviesNotRendered.slice(0,8);
            var movies = this.state.moviesRendered
            nextMovies.map( (movie) => {
                movies.push(movie)
            } )

            this.setState({
                moviesRendered: movies,
                moviesNotRendered: this.state.moviesNotRendered.slice(8)
            })

        }else{
            var url = backend + '/member-movies/' + this.state.id + "/";

            var nextPage = this.state.currentPage + 1
            
            Axios.get( url + nextPage).then(x => {
    
                var movies = this.state.moviesRendered;
                var nextMovies = x.data.movies.slice(0,8)


                nextMovies.map( (movie) => {
                    movies.push(movie)
                } )
                
                this.setState({
                    moviesRendered: movies,
                    moviesNotRendered: this.state.moviesNotRendered.concat(x.data.movies.slice(8)),
                    moreMovies: x.data.moreMovies,
                    currentPage: nextPage
                })
              });
        } 
    }

    render(){
        const loader = <div className="loader">{labels[this.props.lang].loading} ...</div>;

        return (
        <div className="member-company-main col-xs-12 col-md-8">
            <div>
                <h1 className="member-company-name">{this.state.name}</h1>
                <a href={this.state.imdb}>
                    <img style={{width: "auto", height:"30px", verticalAlign:"baseline"}} src={imdb} alt=""/>
                </a>
            </div>
            <div id="member-company-info-main">
                <Container className="margin-bottom-30">
                    <Row>
                        <Col className="padding-l-0 flex-align-center">
                            <img className="img-ratio-fill" src={this.state.photo} alt="" />
                        </Col>
                        <Col className="padding-r-0 flex-align-center">
                            {this.state.info}
                        </Col>
                    </Row>
                </Container>
            </div>
            <div>
                <h4 className="title-member-company-18">
                    {labels[this.props.lang].biography}
                </h4>
                <hr className="section-divider light-gray"></hr>
                <p className="font-15pt gray">{this.state.biography}</p>
            </div>
            <div style={{paddingBottom:"20px"}}>
                <h4 className="title-member-company-18">
                    {labels[this.props.lang].photoGallery}
                </h4>
                <hr className="section-divider light-gray"></hr>
                <HorizontalSlider more="/media" content={this.state.backdrops} loadMore={false}/>
            </div>
            <div>
                <h4 className="title-member-company-18">
                    {labels[this.props.lang].relatedMovies}
                </h4>
                <hr className="section-divider light-gray"></hr>
                <Container id="member-company-movies">
                    <InfiniteScroll
                        pageStart={0}
                        loadMore={this.loadItems.bind(this)}
                        hasMore={this.state.moreMovies}
                        loader={loader}>
                        <Row>
                            {
                                this.state.moviesRendered.map(element => {
                                return(
                                    <Col xs="6" sm="3" >
                                        <MovieCard img={"https://image.tmdb.org/t/p/w200/" + element.poster} title={element.name} info={element.role} class="max-height-100"/>
                                    </Col>)
                            })}
                        </Row>
                    </InfiniteScroll>
                </Container>
            </div>
        </div>
        ) 
    }
}