import React, { Component } from 'react';
import imdb from '../../img/imdb.svg';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Container from 'react-bootstrap/Container'
import MovieCard from '../movie-card'


export default function ActorMain(props){
    return (
      <div className="actor-main col-xs-12 col-md-8">
        <div>
            <h1 className="actor-name">{props.name}</h1>
            <a href={props.imdb}>
                <img style={{width: "auto", height:"30px", verticalAlign:"baseline"}} src={imdb} alt=""/>
            </a>
        </div>
        <div id="actor-info-main">
            <Container className="margin-bottom-30">
                <Row>
                    <Col className="padding-l-0 flex-align-center">
                        <img className="img-ratio-fill" src={props.photo} alt="" />
                    </Col>
                    <Col className="padding-r-0 flex-align-center">
                        {props.info}
                    </Col>
                </Row>
            </Container>
        </div>
        <div>
            <h4 className="title-actor-18">
                Biography
            </h4>
            <hr className="section-divider light-gray"></hr>
            <p className="font-15pt gray">{props.biography}</p>
        </div>
        <div>
            <h4 className="title-actor-18">
                Related Movies
            </h4>
            <hr className="section-divider light-gray"></hr>
            <Container id="actor-movies">
                <Row>
                    <Col xs="6" sm="3" >
                        <MovieCard img="http://placehold.it/228x337" title="Saving Private Ryan" info="(Captain John H. Miller)"/>
                    </Col>
                    <Col xs="6" sm="3" >
                        <MovieCard img="http://placehold.it/228x337" title="Toy Story" info="(Woody (Voice))"/>
                    </Col>
                    <Col xs="6" sm="3" >
                        <MovieCard img="http://placehold.it/228x337" title="Catch Me If You Can" info="(Carl Hanratty)"/>
                    </Col>
                    <Col xs="6" sm="3">
                        <MovieCard img="http://placehold.it/228x337" title="Toy Story 2" info="(Woody (Voice))"/>
                    </Col>
                    <Col xs="6" sm="3" >
                        <MovieCard img="http://placehold.it/228x337" title="The Green Mile" info="(Paul Edgecomb)"/>
                    </Col>
                    <Col xs="6" sm="3" >
                        <MovieCard img="http://placehold.it/228x337" title="Toy Story 3" info="(Woody (Voice))"/>
                    </Col>
                </Row>
            </Container>
        </div>
      </div>
    )  
}