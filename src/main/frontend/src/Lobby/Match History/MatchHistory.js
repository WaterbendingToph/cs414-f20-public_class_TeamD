import React, { Component } from 'react';
import { Button, Table } from 'reactstrap';

export default class MatchHistory extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
            history: [],
        }
        this.returnToLobby = this.returnToLobby.bind(this);
        this.populateHistory = this.populateHistory.bind(this);
    }

    componentDidMount() {
        fetch("/history?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                this.setState({ history: result.history })
            });
    }

    populateHistory() {
        if (this.state.history.length < 1) {
            return (
                <>
                    You have no completed matches to display.
                </>
            )
        } else {
            let matches = this.state.history.map(match =>
                    <tr key={match}>
                        <td> {match[0]} </td>
                        <td> {match[1]} </td>
                    </tr>
            )
            return (
                <>
                    <thead>
                        <tr>
                            <th>Match ID</th>
                            <th>Opponent</th>
                        </tr>
                    </thead>
                    <tbody>
                        { matches } 
                    </tbody>
                </>
            )
        }
    }

    returnToLobby() {
        fetch("/login?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                if (result.loginSuccess) {
                    this.props.history.push({
                        pathname: "/lobby",
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
        return (
            <div>
                <h2 style={{ textAlign: "center" }}>Match History</h2>
                <Button onClick={ this.returnToLobby } type='button'>Return to Lobby</Button>
                <Table>
                    { this.populateHistory() }
                </Table>
            </div>
        )
    }
}