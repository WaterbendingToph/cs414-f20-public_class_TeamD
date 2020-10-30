import React, {Component} from 'react';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";

export default class Lobby extends Component{
    goToGamePlay(){
        this.props.history.push("/game");
    }

    render() {
        return(
            <div>
                <h1>Welcome to the Lobby</h1>
                <CreateMatchBox toGame={this.goToGamePlay.bind(this)} />
                <a href={"/ongoingmatches/"}>Ongoing Matches</a>
            </div>
        )
    }
}