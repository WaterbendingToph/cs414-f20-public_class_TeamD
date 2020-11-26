import React, { Component } from 'react';
import { Button } from 'reactstrap';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";
import { Grid } from '@material-ui/core';
import InviteBox from "./InviteBox/InviteBox";
import style from "./Lobby.module.css";

export default class Lobby extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
        }
        this.ongoingMatches = this.ongoingMatches.bind(this);
    }

    goToGamePlay(wait = true, players=[], id=null){
        fetch("/searchForNewMatch?current="+this.state.userID)
            .then(res => res.json())
            .then(data => {
                if(data.searching === true){
                    this.props.history.push({
                        pathname: "/game",
                        state:{
                            searching: wait,
                            userID: this.state.userID,
                            password: this.props.location.state.password,
                            players: players,
                            gameID: id
                        }
                    });
                }
            }
        );
    }

    ongoingMatches() {
        fetch("/lobby?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                if (result.loginSuccess) {
                    this.props.history.push({
                        pathname: "/matches",
                        state: {
                            userID: this.state.userID,
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
            <Grid>
                <Grid className={style.Header} item>
                    <h1>Welcome to the Lobby, {this.state.userID}!</h1>
                    <InviteBox current={this.state.userID} toGame={this.goToGamePlay.bind(this)}/>
                </Grid>
                <Grid item>
                    <CreateMatchBox currentUser={this.state.userID} toGame={this.goToGamePlay.bind(this)} />
                    <Button onClick={this.ongoingMatches} type='button'>Ongoing Matches</Button>
                </Grid>
            </Grid>

        )
    }
}