import React from 'react';
import {labels} from '../../var'

export default function CompanyInfo(props){

    return(
        <div className="member-company-info">
            <div className="margin-bottom-10">
                <p className="info-label">{labels[props.lang].country}</p>
                <p className="info-value">{props.country ? props.country : '-'}</p>
            </div>
            <div className="margin-bottom-10">
                <p className="info-label">{labels[props.lang].headquarters}</p>
                <p className="info-value">{props.headquarters ? props.headquarters : '-'}</p>
            </div>
            <div className="">
                <p className="info-label">{labels[props.lang].homepage}</p>
                <a href={props.homepage}><u>{props.name}</u></a>
            </div>
        </div>
    );
}