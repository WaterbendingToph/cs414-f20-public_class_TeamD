import React, { Component } from "react";
import { Table } from 'reactstrap';

export default class OngoingMatches extends Component {
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
                        <tr>
                            <th scope="row">1</th>
                            <td>Go to Match</td>
                            <td>Dakota</td>
                            <td>Your Move</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Go to Match</td>
                            <td>Victor</td>
                            <td>Their Move</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Go to Match</td>
                            <td>Sai</td>
                            <td>Your Move</td>
                        </tr>
                    </tbody>
                </Table>
            </div>
        )
    }
}