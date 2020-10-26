import React, { Component, Form } from 'react';
import {
    // BrowserRouter as Router,
    // Switch,
    // Route,
    // Link,
    Redirect,
    // useHistory,
    // useLocation
  } from "react-router-dom";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: "Nick",
            password: "password",
        }
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit() {
        fetch("/login?user=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json)
            .then(result => {
                if (result.loginSuccess) {
                    <Redirect
                        to={{
                            pathname: "/lobby",
                        }}
                    />
                }
            )
    }

    render() {
        return (
            <Form>
                <h1 style={{ textAlign: "center", border: "5px solid black" }}>Welcome to Omega Chess</h1>
                <h2 style={{ textAlign: "center" }}>Returning User?</h2>
                <h3 style={{ textAlign: "center" }}>Username:</h3>
                <input onChange={(event) => this.setState({ user: event.target.value })} type={"text"} placeholder={"Username Here"} />
                <h3 style={{ textAlign: "center" }}>Password:</h3>
                <input onChange={(event) => this.setState({ password: event.target.value })} type={"text"} placeholder={"Password Here"} />
                <button onClick={this.onSubmit}>Login</button>
                <h2 style={{ textAlign: "center" }}>New User? Create an account</h2>
                <input type={"text"} placeholder={"New Account Username"} />
                <input type={"text"} placeholder={"New Account Password"} />
                <button>Create a new Account</button>
            </Form>
        )}
}