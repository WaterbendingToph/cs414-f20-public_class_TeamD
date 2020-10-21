import React, { Component } from "react";
import { Table } from 'reactstrap';

export default class OngoingMatches extends Component {
    constructor(props) {
        super(props)
        this.state = {
            movie: {}
        }
    }

    componentDidMount() {
        let name = decodeURI(this.props.match.params.id);
        fetch("/ongoingmatches/" + name)
            .then(res => res.json())
            .then(movie => this.setState({ movie }))
    }

    isMovieEmpty() {
        let movie = this.state.movie;
        return Object.keys(movie).length === 0;
    }

    renderHeader() {
        const date = new Date(this.state.movie.reviewDate);
        return (
            <Container id="reviewHeader">
                <div id="contents" className={style.header}>
                    <h1>{this.state.movie.title}</h1>
                    <Typography className={style.subtitle} variant="subtitle2">Released: {this.state.movie.year}</Typography>
                    <StarRating rating={this.state.movie.rating}
                        starRatedColor="rgb(218,165,32)"
                        starDimension="30px"
                    />
                    <Row className={style.author}>
                        <a href="/about"><h6>Sai Donepudi</h6></a>
                        <h6 style={{ paddingLeft: '10px' }}>{date.toDateString()}</h6>
                    </Row>
                </div>
                <img className={style.poster} src={this.state.movie.image} alt="movie still" />
            </Container>
        );
    }

    generateReview() {
        const movie = this.state.movie;
        try {
            var items = movie.review.split("\n");
            var ret = [];
            var count = 0;
            items.forEach((paragraph) => {
                ret.push(
                    <Row key={"paragraph" + count}>
                        <p>{paragraph}</p>
                        <br />
                    </Row>
                );
                count++;
            });
            return ret;
        }
        catch (e) {
            return [];
        }
    }

    renderBody() {
        return (
            <Container className={style.body} id="reviewBody">
                {this.generateReview()}
            </Container>
        );
    }

    render() {
        if (!this.isMovieEmpty()) {
            return (
                <div id="reviewPage">
                    <div id="review">
                        {this.renderHeader()}
                        {this.renderBody()}
                        <hr className={style.line} />
                    </div>
                    <Credits title={this.state.movie.title} year={this.state.movie.year} />
                </div>
            );
        }
        else {
            return (
                <Container id="loading">
                    <CircularProgress />
                </Container>
            )
        }
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