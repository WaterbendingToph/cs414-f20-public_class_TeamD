import React, {Component} from 'react';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";
import { Grid } from '@material-ui/core';
import InviteBox from "./InviteBox/InviteBox";
import style from "./Lobby.module.css";

export default class Lobby extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: this.props.location.state.username,
        }
    }

    goToGamePlay(){
          this.props.history.push("/game");
    }

    render() {
        return(
            <Grid>
                <Grid className={style.Header} item>
                    <h1>Welcome to the Lobby, {this.state.username}!</h1>
                    <InviteBox />
                </Grid>
                <Grid item>
                    <CreateMatchBox currentUser={this.state.username} toGame={this.goToGamePlay.bind(this)} />
                    <a href={"/matches/"}>Ongoing Matches</a>
                </Grid>
            </Grid>
        )
    }
}