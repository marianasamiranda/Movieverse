import React, { Component } from 'react';
import {labels} from '../../var'

export default function MemberInfo(props){
    let gender = props.gender === 'M'? labels[props.lang].male : labels[props.lang].female;
    let birthday = props.birthday === null ? labels[props.lang].undefined : props.birthday
    let birthplace = props.birthplace === null ? labels[props.lang].undefined : props.birthplace

    return(
        <div className="member-company-info">
            <div className="margin-bottom-10">
                <p className="info-lable">{labels[props.lang].gender}</p>
                <p className="info-value">{gender }</p>
            </div>
            <div className="margin-bottom-10">
                <p className="info-lable">{labels[props.lang].birthdate}</p>
                <p className="info-value">{birthday}</p>
            </div>
            <div className="">
                <p className="info-lable">{labels[props.lang].birthplace}</p>
                <p className="info-value">{birthplace}</p>
            </div>
        </div>
    );
}