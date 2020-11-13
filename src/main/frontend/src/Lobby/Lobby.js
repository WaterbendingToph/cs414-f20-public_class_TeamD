import React, { Component } from 'react';
import { Button } from 'reactstrap';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";

export default class Lobby extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
        }
        this.ongoingMatches = this.ongoingMatches.bind(this);
    }

    goToGamePlay(){
          this.props.history.push("/game");
    }

    ongoingMatches() {
        fetch("/lobby?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                if (result.loginSuccess) {
                    this.props.history.push({
                        pathname: "/matches",
                        state: {
                            username: this.state.userID,
                            password: this.state.password,
                        }
                    });
                } else {
                    this.setState({ loginFailed: true })
                }
            })
    }

    render() {
        return(
            <div>
                <h1>Welcome to the Lobby, {this.state.userID}!</h1>
                <CreateMatchBox toGame={this.goToGamePlay.bind(this)} />
                <Button onClick={this.ongoingMatches} type='button'>Ongoing Matches</Button>
            </div>
        )
    }
}