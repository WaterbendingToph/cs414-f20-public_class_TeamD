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
            let allMatches = this.state.matches.map(match =>
                    <tr key={match}>
                        <td> {match[0]} </td>
                        <td>Go to match</td> 
                        <td> {match[1]} </td>
                        <td> {match[2]} </td>
                    </tr>
            )
            return (
                <>
                    { allMatches }
                </>
            )
        }
    }

    render() {
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