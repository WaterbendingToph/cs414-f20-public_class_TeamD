import React, {Component} from 'react';

export default class Login extends Component{
    render() {
        return(
            <div>
                <h1 style={ {textAlign: "center", border: "5px solid black"} }>Welcome to Omega Chess</h1>
                <h2 style={ {textAlign: "center"} }>Returning User?</h2>
                <h3 style={ {textAlign: "center"} }>Username:</h3>
                <h3 style={ {textAlign: "center"} }>Password:</h3>
            </div>
        )
    }
}