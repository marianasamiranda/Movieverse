import React from 'react';

export default function ActorAside(props){

    return (
        <div className="col-4">
            <div className="actor-aside">
                <div className="row">
                    <div className="col-xs-6 col-md-12">
                        <img className="img-ratio-fill margin-bottom-50" src={props.photo} alt="" />
                    </div>
                    <div className="col-xs-6 col-md-12">
                        {props.info}
                    </div>
                </div>
            </div>
        </div>
    )
}
  
