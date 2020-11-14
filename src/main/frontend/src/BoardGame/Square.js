import React, {Component} from 'react';

export default class Square extends Component{

    constructor(props){
        super(props);
        this.backgroundColor = props.backgroundColor;
    }


    render(){
        return (
            <div style = {{
                backgroundColor: this.props.backgroundColor,
                width: '50px',
                height: '50px',
                border: "2px solid",
                borderColor : this.props.backgroundColor } } >
            </div>);
    }
}
