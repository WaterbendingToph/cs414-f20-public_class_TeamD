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
            registrationSucceeded: false,
            registrationFailed: false,
        }
        this.onSubmit = this.onSubmit.bind(this);
        this.registerAccount = this.registerAccount.bind(this);
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

    toggleLoginAlert() {
        this.setState({ loginFailed: !this.state.loginFailed })
    }

    registerAccount() {
        fetch("/register?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                if (result.registrationSuccess) {
                    this.setState({ registrationSucceeded: true })
                } else {
                    this.setState({ registrationFailed: true })
                }
            })
    }

    toggleRegistrationSuccessAlert() {
        this.setState({
            registrationSucceeded: !this.state.registrationSucceeded,
        })
    }

    toggleRegistrationFailedAlert() {
        this.setState({
            registrationFailed: !this.state.registrationFailed,
        })
    }

    render() {
        return (
            <div>
                <Alert color="danger" isOpen={this.state.loginFailed} toggle={this.toggleLoginAlert.bind(this)}>Username/password not found! Please try again.</Alert>
                <Alert color="success" isOpen={this.state.registrationSucceeded} toggle={this.toggleRegistrationSuccessAlert.bind(this)}>Successfully registered! Please login below.</Alert>
                <Alert color="danger" isOpen={this.state.registrationFailed} toggle={this.toggleRegistrationFailedAlert.bind(this)}>Registration Failed! This could be due to an unavailable username or invalid password. Please try again.</Alert>
                <Form onSubmit={this.onSubmit}>
                    <h1 style={{ textAlign: "center", border: "5px solid black" }}>Welcome to Omega Chess</h1>
                    <h2 style={{ textAlign: "center" }}>Returning User?</h2>
                    <h3 style={{ textAlign: "center" }}>Username:</h3>
                    <input onChange={(event) => this.setState({ userID: event.target.value })} type={"text"} placeholder={"Username Here"} />
                    <h3 style={{ textAlign: "center" }}>Password:</h3>
                    <input onChange={(event) => this.setState({ password: event.target.value })} type={"text"} placeholder={"Password Here"} />
                    <button onClick={this.onSubmit} type='button'>Login</button>
                    <h2 style={{ textAlign: "center" }}>New User? Create an account</h2>
                    <input onChange={(event) => this.setState({ userID: event.target.value })} type={"text"} placeholder={"New Account Username"} />
                    <input onChange={(event) => this.setState({ password: event.target.value })} type={"text"} placeholder={"New Account Password"} />
                    <button onClick={this.registerAccount} type='button'>Create Account</button>
                </Form>
            </div>
        )}
}