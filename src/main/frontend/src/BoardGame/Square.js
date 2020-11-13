import React, {Component} from 'react';

export default class Square extends Component{

    constructor(props){
        super(props);
        this.state = {
            piece : null,
            backgroundColor : null,
        }
    }


    render(){
        const color_ = this.props.backgroundColor;
        return (
            <div style = {{
                backgroundColor: color_,
                width: '75px',
                height: '75px',
                border: "2px solid",
                borderColor : this.props.backgroundColor } } >
            </div>);
    }
}
