import React, {Component} from 'react';
import OngoingMatches from './Ongoing Matches/OngoingMatches';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";

export default class Lobby extends Component{
    render() {
        return(
            <div>
                <h1>Welcome to the Lobby</h1>
                <CreateMatchBox />
                <a href={"/ongoingmatches/"}>Ongoing Matches</a>
            </div>
        )
    }
}