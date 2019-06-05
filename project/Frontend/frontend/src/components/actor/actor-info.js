import React, { Component } from 'react';
import {labels} from '../../var'

export default function ActorInfo(props){
    let gender = props.gender === 'M'? labels[props.lang].male : labels[props.lang].female;

    return(
        <div className="actor-info">
            <div className="margin-bottom-10">
                <p className="info-lable">{labels[props.lang].gender}</p>
                <p className="info-value">{gender}</p>
            </div>
            <div className="margin-bottom-10">
                <p className="info-lable">{labels[props.lang].birthdate}</p>
                <p>{props.birthday}</p>
            </div>
            <div className="">
                <p className="info-lable">{labels[props.lang].birthplace}</p>
                <p className="info-value">{props.birthplace}</p>
            </div>
        </div>
    );
}