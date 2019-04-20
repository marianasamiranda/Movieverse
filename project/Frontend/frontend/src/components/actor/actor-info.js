import React, { Component } from 'react';

export default function ActorInfo(props){
    let gender = props.gender === 'M'? 'Male': 'Female';

    return(
        <div className="actor-info">
            <div className="margin-bottom-10">
                <p className="info-lable">Gender</p>
                <p className="info-value">{gender}</p>
            </div>
            <div className="margin-bottom-10">
                <p className="info-lable">Birthday</p>
                <p>{props.birthday}</p>
            </div>
            <div className="">
                <p className="info-lable">Birth Place</p>
                <p className="info-value">{props.birthplace}</p>
            </div>
        </div>
    );
}