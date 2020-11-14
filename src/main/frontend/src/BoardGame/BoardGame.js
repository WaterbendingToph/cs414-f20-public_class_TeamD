import React, {Component} from 'react';
import Square from "./Square";

const orange = '#a85e00';
const blue = '#001883';
const white = '#ffffff'


export default class BoardGame extends Component{

    constructor(props) {
        super(props);
        this.state = {
            row0: this.setupDefaultWizardRowWhite(),
            row1: this.setupDefaultBackRowWhite(),
            row2: this.setupPawnsRow2(),
            row3: this.setupBlankRow1(),
            row4: this.setupBlankRow2(),
            row5: this.setupBlankRow1(),
            row6: this.setupBlankRow2(),
            row7: this.setupBlankRow1(),
            row8: this.setupBlankRow2(),
            row9: this.setupPawnsRow9(),
            row10: this.setupDefaultBackRowBlack(),
            row11: this.setupDefaultWizardRowBlack()
        }
        this.setupDefaultWizardRowWhite = this.setupDefaultWizardRowWhite.bind(this);
        this.setupDefaultBackRowWhite = this.setupDefaultBackRowWhite.bind(this);
        this.setupPawnsRow2 = this.setupPawnsRow2.bind(this);
        this.setupBlankRow1 = this.setupBlankRow1.bind(this);
        this.setupBlankRow2 = this.setupBlankRow2.bind(this);
        this.setupPawnsRow9 = this.setupPawnsRow9.bind(this);
        this.setupDefaultBackRowBlack = this.setupDefaultBackRowBlack.bind(this);
        this.setupDefaultWizardRowBlack = this.setupDefaultWizardRowBlack.bind(this);
    }

    setupDefaultWizardRowWhite(){
        return(
            <tr>
                <td><Square backgroundColor={orange} color={"white"} piece={"wizard"}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"wizard"}/></td>
            </tr>
        );
    }

    setupDefaultWizardRowBlack(){
        return (
            <tr>
                <td><Square backgroundColor={blue} color={"black"} piece={"wizard"}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={white}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"wizard"}/></td>
            </tr>
        );
    }

    setupDefaultBackRowWhite() {
        return (
            <tr >
                <td><Square backgroundColor={orange} color={"white"} piece={"champion"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"rook"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"knight"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"bishop"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"queen"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"king"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"bishop"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"knight"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"rook"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"champion"}/></td>
            </tr>
        );

    }

    setupDefaultBackRowBlack() {
        return(
            <tr>
                <td><Square backgroundColor={blue} color={"black"} piece={"champion"}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"rook"}/></td>
                <td><Square backgroundColor={blue} color={"black"} piece={"knight"}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"bishop"}/></td>
                <td><Square backgroundColor={blue} color={"black"} piece={"queen"}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"king"}/></td>
                <td><Square backgroundColor={blue} color={"black"} piece={"bishop"}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"knight"}/></td>
                <td><Square backgroundColor={blue} color={"black"} piece={"rook"}/></td>
                <td><Square backgroundColor={orange} color={"black"} piece={"champion"}/></td>
            </tr>

        );
    }

    setupPawnsRow2() {
        return(
            <tr>
                <td><Square backgroundColor={blue} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={blue} color={"white"} piece={"pawn"}/></td>
                <td><Square backgroundColor={orange} color={"white"} piece={"pawn"}/></td>
            </tr>
        );
    }

    setupPawnsRow9() {
        return(
            <tr >
            <td><Square backgroundColor={orange} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={blue} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={orange} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={blue} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={orange} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={blue} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={orange} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={blue} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={orange} color={"black"} piece={"pawn"}/></td>
            <td><Square backgroundColor={blue} color={"black"} piece={"pawn"}/></td>
        </tr>
        );
    }

    setupBlankRow1(){
        return (
            <tr >
                <td><Square backgroundColor={orange} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={orange} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={orange} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={orange} /></td>
                <td><Square backgroundColor={blue} /></td>
                <td><Square backgroundColor={orange} /></td>
                <td><Square backgroundColor={blue} /></td>
            </tr>
        );
    }

    setupBlankRow2(){
        return(
            <tr>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={orange}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={orange}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={orange}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={orange}/></td>
                <td><Square backgroundColor={blue}/></td>
                <td><Square backgroundColor={orange}/></td>
            </tr>

        );
    }

    render(){
        return (
            <div className="App">
                <h1>Currently the default chessboard setup!</h1>
                <div align={'center'}>{this.state.row0}</div>
                <div align={'center'}>{this.state.row1}</div>
                <div align={'center'}>{this.state.row2}</div>
                <div align={'center'}>{this.state.row3}</div>
                <div align={'center'}>{this.state.row4}</div>
                <div align={'center'}>{this.state.row5}</div>
                <div align={'center'}>{this.state.row6}</div>
                <div align={'center'}>{this.state.row7}</div>
                <div align={'center'}>{this.state.row8}</div>
                <div align={'center'}>{this.state.row9}</div>
                <div align={'center'}>{this.state.row10}</div>
                <div align={'center'}>{this.state.row11}</div>
            </div>
        );
    }
}
