import React, { Component } from 'react';
import imdb from '../../img/imdb.svg';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import MovieCard from '../movie-card'
import HorizontalSlider from '../horizontal-slider';
import InfiniteScroll from 'react-infinite-scroller';
import {backend} from '../../var'
import Axios from 'axios'


export default class ActorMain extends Component{

    constructor(props){
        super(props)

        this.state = {
            id: props.id,
            currentPage: 0,
            hasMoreItems: props.movies != [] ? true: false,
            name: props.name,
            imdb: props.imdb,
            photo: props.photo,
            biography: props.biography,
            info: props.info,
            movies: props.movies
        }
        this.loadItems.bind(this)
    }

    loadItems(page) {

        var url = backend + '/member-movies/' + this.state.id + "/";

        var nextPage = this.state.currentPage + 1
        
        Axios.get( url + nextPage).then(x => {

            var movies = this.state.movies;

            if (x.data.length > 0){

                x.data.map( (movie) => {
                    movies.push(movie)
                } )
                
                this.setState({
                    movies: movies,
                    currentPage: nextPage
                })
            }else{
                this.setState({
                    hasMoreItems: false
                })
            }
          });
    }

    render(){
        const loader = <div className="loader">Loading ...</div>;

        const backdrops = [
            {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
            {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
            {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"},
            {"href": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg", "src": "https://image.tmdb.org/t/p/original/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg"}
        ]

        return (
        <div className="actor-main col-xs-12 col-md-8">
            <div>
                <h1 className="actor-name">{this.state.name}</h1>
                <a href={this.state.imdb}>
                    <img style={{width: "auto", height:"30px", verticalAlign:"baseline"}} src={imdb} alt=""/>
                </a>
            </div>
            <div id="actor-info-main">
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
                <h4 className="title-actor-18">
                    Biography
                </h4>
                <hr className="section-divider light-gray"></hr>
                <p className="font-15pt gray">{this.state.biography}</p>
            </div>
            <div style={{paddingBottom:"20px"}}>
                <h4 className="title-actor-18">
                    Photo Gallery
                </h4>
                <hr className="section-divider light-gray"></hr>
                <HorizontalSlider more="/media" content={backdrops}/>
            </div>
            <div>
                <h4 className="title-actor-18">
                    Related Movies
                </h4>
                <hr className="section-divider light-gray"></hr>
                <Container id="actor-movies">
                    <InfiniteScroll
                        pageStart={0}
                        loadMore={this.loadItems.bind(this)}
                        hasMore={this.state.hasMoreItems}
                        loader={loader}>
                        <Row>
                            {
                                this.state.movies.map(element => {
                                return(
                                    <Col xs="6" sm="3" >
                                        <MovieCard img={"https://image.tmdb.org/t/p/w200/" + element.poster} title={element.name} info={element.role}/>
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