import React, {Component} from 'react';
import {forestgreen, black} from "color-name";

export default class Square extends Component{

    constructor(props){
        super(props);
        this.state = {
            piece : null,
            color : black,
        }
    }


    render(){
        return (
            <div style = {{
                width: '100px',
                height: '100px',
                border: "1px solid",
                borderColor : this.props.color } } >
            </div>);
    }
}
