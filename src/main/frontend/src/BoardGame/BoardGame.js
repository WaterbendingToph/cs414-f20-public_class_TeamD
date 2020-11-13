import React, {Component} from 'react';
import Square from "./Square";

const green = '#065535';
const blue = '#050627';


export default class BoardGame extends Component{

    constructor(props) {
        super(props);
        this.state = {
            row1: this.setupDefaultRow1(),
            row2: this.setupPawnsRow2(),
            row3: this.setupBlankRow1(),
            row4: this.setupBlankRow2(),
            row5: this.setupBlankRow1(),
            row6: this.setupBlankRow2(),
            row7: this.setupBlankRow1(),
            row8: this.setupBlankRow2(),
            row9: this.setupPawnsRow9(),
            row10: this.setupDefaultRow10()
        }
        this.setupDefaultRow1 = this.setupDefaultRow1.bind(this);
        this.setupPawnsRow2 = this.setupPawnsRow2.bind(this);
        this.setupBlankRow1 = this.setupBlankRow1.bind(this);
        this.setupBlankRow2 = this.setupBlankRow2.bind(this);
        this.setupPawnsRow9 = this.setupPawnsRow9.bind(this);
        this.setupDefaultRow10 = this.setupDefaultRow10.bind(this);
    }

    setupDefaultRow1() {
        return (
            <tr >
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
            </tr>
        );

    }

    setupDefaultRow10() {
        return(
            <tr>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
            </tr>

        );
    }

    setupPawnsRow2() {
        return(
            <tr>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
            </tr>
        );
    }

    setupPawnsRow9() {
        return(
            <tr >
            <td><Square backgroundColor={green} /></td>
            <td><Square backgroundColor={blue} /></td>
            <td><Square backgroundColor={green} /></td>
            <td><Square backgroundColor={blue} /></td>
            <td><Square backgroundColor={green} /></td>
            <td><Square backgroundColor={blue} /></td>
            <td><Square backgroundColor={green} /></td>
            <td><Square backgroundColor={blue} /></td>
            <td><Square backgroundColor={green} /></td>
            <td><Square backgroundColor={blue} /></td>
        </tr>
        );
    }

    setupBlankRow1(){
        return (
            <tr >
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={green} /></td>
                <td><Square backgroundColor={blue} /></td>
            </tr>
        );
    }

    setupBlankRow2(){
        return(
            <tr>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={green}/></td>
            </tr>

        );
    }

    render(){
        return (
            <div className="App">
                <h1>Currently the default chessboard setup!</h1>
                {this.state.row1}
                {this.state.row2}
                {this.state.row3}
                {this.state.row4}
                {this.state.row5}
                {this.state.row6}
                {this.state.row7}
                {this.state.row8}
                {this.state.row9}
                {this.state.row10}
            </div>
        );
    }
}
