import React, {Component} from 'react';

export default class Login extends Component{
    render() {
        return(
            <div>
                <h2 style={{textAlign: "center"} }>Login Component Loaded! YAY!</h2>
                <a href={"/lobby/"}>Go to the Lobby</a>
            </div>
        )
    }
}