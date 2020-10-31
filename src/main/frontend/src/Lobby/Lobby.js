import React, {Component} from 'react';
import OngoingMatches from './Ongoing Matches/OngoingMatches';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";

export default class Lobby extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: this.props.location.state.username,
        }
    }

    render() {
        return(
            <div>
                <h1>Welcome to the Lobby, {this.state.username}!</h1>
                <CreateMatchBox />
                <a href={"/matches/"}>Ongoing Matches</a>
            </div>
        )
    }
}