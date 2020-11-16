import React, { Component } from 'react';
import { Table } from 'reactstrap';

export default class OngoingMatches extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
            matches: [],
        }
    }

    componentDidMount() {
        fetch("/matches?userID=" + this.state.userID)
            .then(res => res.json())
            .then(result => {
                this.setState({ matches: result.matches })
            });
    }

    populateMatches() {
        if (this.state.matches.length !== 0) {
            let allMatches = this.state.matches.map(match => {
                return (
                    <div>
                        <td key={match}>{match} {match[0]} </td>
                        <td>Go to match</td>
                        <td key={match}>{match} {match[1]} </td>
                        <td key={match}>{match} {match[2]} </td>
                    </div>
                )
            });
            return (
                <tr>
                    { allMatches }
                </tr>
            )
        }
    }

    render() {
        // If-else to show loading if matches hasn't been loaded yet
        return (
            <div>
                <h2 style={{ textAlign: "center" }}>Ongoing Matches</h2>
                <Table>
                    <thead>
                        <tr>
                            <th>Match ID</th>
                            <th>Link</th>
                            <th>Opponent</th>
                            <th>Next Move</th>
                        </tr>
                    </thead>
                    <tbody>
                        { this.populateMatches() }
                    </tbody>
                </Table>
            </div>
        )
    }
}