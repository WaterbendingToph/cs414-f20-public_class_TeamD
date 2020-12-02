import React, { Component } from 'react';
import { Button, Table } from 'reactstrap';

export default class OngoingMatches extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
            matches: [],
        }
        this.onSubmit = this.onSubmit.bind(this);
        this.populateMatches = this.populateMatches.bind(this);
        this.goToMatch = this.goToMatch.bind(this);
    }

    componentDidMount() {
        fetch("/matches?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                this.setState({ matches: result.matches })
            });
    }

    populateMatches() {
        if (this.state.matches.length < 1) {
            return (
                <>
                    You have no ongoing matches to display.
                </>
            )
        } else {
            let allMatches = this.state.matches.map(match =>
                    <tr key={match}>
                        <td> {match[0]} </td>
                        <td>
                            <Button onClick={this.goToMatch(match[0])} type='link'>Go To Match</Button>
                        </td> 
                        <td> {match[1]} </td>
                        <td> {match[2]} </td>
                    </tr>
            )
            return (
                <>
                    <thead>
                        <tr>
                            <th>Match ID</th>
                            <th>Opponent</th>
                            <th>Next Move</th>
                        </tr>
                    </thead>
                    <tbody>
                        { allMatches } 
                    </tbody>
                </>
            )
        }
    }

    onSubmit() {
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

    goToMatch(gameID) {
        fetch("/matches?userID=" + this.state.userID + "&password=" + this.state.password)
            .then(res => res.json())
            .then(result => {
                this.props.history.push({
                    pathname: "/game",
                    state: {
                        userID: this.state.userID,
                        password: this.state.password,
                        gameID: gameID,
                    }
                });
            })
    }

    render() {
        return (
            <div>
                <h2 style={{ textAlign: "center" }}>Ongoing Matches</h2>
                <Button onClick={ this.onSubmit } type='button'>Return to Lobby</Button>
                <Table>
                    { this.populateMatches() }
                </Table>
            </div>
        )
    }
}