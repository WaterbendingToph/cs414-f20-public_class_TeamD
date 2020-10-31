import React, {Component} from 'react';
import CreateMatchBox from "./Create Match Box/CreateMatchBox";

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
            <div>
                <h1>Welcome to the Lobby, {this.state.username}!</h1>
                <CreateMatchBox toGame={this.goToGamePlay.bind(this)} />
                <a href={"/matches/"}>Ongoing Matches</a>
            </div>
        )
    }
}