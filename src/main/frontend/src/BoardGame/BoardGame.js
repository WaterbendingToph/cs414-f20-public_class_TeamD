import React, {Component} from 'react';
import Square from "./Square";

const orange = '#ffc002';
const blue = '#8e6a00';
const white = '#ffffff';


export default class BoardGame extends Component{

    constructor(props) {
        super(props);
        this.state = {
            row0: this.setupDefaultWizardRowWhite(),
            row1: this.setupDefaultBackRowWhite(),
            row2: this.setupPawnsRow9(),
            row3: this.setupBlankRow1(),
            row4: this.setupBlankRow2(),
            row5: this.setupBlankRow1(),
            row6: this.setupBlankRow2(),
            row7: this.setupBlankRow1(),
            row8: this.setupBlankRow2(),
            row9: this.setupPawnsRow2(),
            row10: this.setupDefaultBackRowBlack(),
            row11: this.setupDefaultWizardRowBlack(),
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
            gameID: this.props.location.state.gameID,
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
                <td><Square backgroundColor={white}/></td>
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
                <td><Square backgroundColor={white}/></td>
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

    setupPawnsRow9() {
        return(
            <tr>
                <td><Square backgroundColor={white}/></td>
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

    setupPawnsRow2() {
        return(
            <tr >
                <td><Square backgroundColor={white}/></td>
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
                <td><Square backgroundColor={white}/></td>
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
                <td><Square backgroundColor={white}/></td>
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

    render() {
        return (
            <table className="App" style={{width:"auto"}} align={'center'}>
                <tbody>
                    {this.state.row11}
                    {this.state.row10}
                    {this.state.row9}
                    {this.state.row8}
                    {this.state.row7}
                    {this.state.row6}
                    {this.state.row5}
                    {this.state.row4}
                    {this.state.row3}
                    {this.state.row2}
                    {this.state.row1}
                    {this.state.row0}
                </tbody>
            </table>
        );
    }
}
