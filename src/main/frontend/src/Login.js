import React, {Component} from 'react';

export default class Login extends Component{
    constructor(props){
        super(props);
        this.state = {
            userID: "Nick",
            password: "password",
        }
    }

    render() {
        return(
            <div>
                <h1 style={ {textAlign: "center", border: "5px solid black"} }>Welcome to Omega Chess</h1>
                <h2 style={ {textAlign: "center"} }>Returning User?</h2>
                <h3 style={ {textAlign: "center"} }>Username:</h3>
                <input type={"text"} placeholder={"Username Here"}/>
                <h3 style={ {textAlign: "center"} }>Password:</h3>
                <input type={"text"} placeholder={"Password Here"}/>
                <button>Login</button>
                <h2 style={ {textAlign: "center"} }>New User? Create an account</h2>
                <input type={"text"} placeholder={"New Account Username"}/>
                <input type={"text"} placeholder={"New Account Password"}/>
                <button>Create a new Account</button>
            </div>
        )
    }
}