import React, { Component } from 'react';
import { Alert, Form } from 'reactstrap';
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
            userID: "",
            password: "",
            loginFailed: false,
        }
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit() {
        fetch("/login?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                if (result.loginSuccess) {
                    this.props.history.push({
                        pathname: "/lobby",
                        state: {
                            username: this.state.userID,
                        }
                    });
                } else {
                    this.setState({ loginFailed: true })
                }
            })
    }

    toggleAlert() {
        this.setState({ loginFailed: !this.state.loginFailed })
    }

    render() {
        return (
            <div>
                <Alert color="danger" isOpen={this.state.loginFailed} toggle={this.toggleAlert.bind(this)}>Username/password not found! Please try again.</Alert>
                <Form onSubmit={this.onSubmit}>
                    <h1 style={{ textAlign: "center", border: "5px solid black" }}>Welcome to Omega Chess</h1>
                    <h2 style={{ textAlign: "center" }}>Returning User?</h2>
                    <h3 style={{ textAlign: "center" }}>Username:</h3>
                    <input onChange={(event) => this.setState({ userID: event.target.value })} type={"text"} placeholder={"Username Here"} />
                    <h3 style={{ textAlign: "center" }}>Password:</h3>
                    <input onChange={(event) => this.setState({ password: event.target.value })} type={"text"} placeholder={"Password Here"} />
                    <button onClick={this.onSubmit} type='button'>Login</button>
                    <h2 style={{ textAlign: "center" }}>New User? Create an account</h2>
                    <input type={"text"} placeholder={"New Account Username"} />
                    <input type={"text"} placeholder={"New Account Password"} />
                    <button>Create a new Account</button>
                </Form>
            </div>
        )}
}