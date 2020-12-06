import React, {Component} from 'react';
import Square from "./Square";
import { Grid } from "@material-ui/core";
import { CircleLoader } from "react-spinners";
import style from "./BoardGame.module.css";

const yellow = '#ffc002';
const orange = '#8e6a00';
const white = '#ffffff';


export default class BoardGame extends Component{

    constructor(props) {
        super(props);
        this.state = {
            userID: this.props.location.state.userID,
            password: this.props.location.state.password,
            gameID: this.props.location.state.gameID,
            searching: this.props.location.state.searching,
            players: this.props.location.state.players,
            timers: [],
            start_search_date: new Date(),
            yourTurn: null,
            pieceSelected: "",
            board: [],
            wizardSpots: [],
            playerColor: ""
        }
        this.setupDefaultWizardRowWhite = this.setupDefaultWizardRowWhite.bind(this);
        this.setupDefaultWizardRowBlack = this.setupDefaultWizardRowBlack.bind(this);
        this.pingForNewMatch = this.pingForNewMatch.bind(this);
        this.getWhoseTurn = this.getWhoseTurn.bind(this);
    }

    componentDidMount(){
        this.getBoard();
        this.getWhoseTurn();
        this.getPlayerColor();
    }

    getPlayerColor(){
        fetch("/getPlayerColor?gameID=" + this.state.gameID + "&current="+ this.state.userID)
            .then(res => res.text())
            .then(color => this.setState({playerColor: color}));
    }

    pingForNewMatch(current, players){
        const d = this.state.start_search_date; 
        fetch("/pingForNewMatch?current="+ current +"&players=" + players+"&date=" + d.toISOString().split('T')[0]+' '+d.toTimeString().split(' ')[0])
            .then(res => res.json())
            .then(data => {
                if(data.isNewMatchCreated){
                    this.setState({searching: false, gameID: data.gameID});
                }
                else{
                    try{
                        let pastTimers = this.state.timers;
                        let timer = setTimeout(this.pingForNewMatch, 3*1000, current, players);
                        pastTimers.push(timer)
                        this.setState({timers: pastTimers});
                    }
                    catch(error){
                        if(error instanceof TypeError)
                            setTimeout(this.pingForNewMatch, 3*1000, current, players);
                    }
                }
            });
    }

    getWhoseTurn() {
        const gameID = this.state.gameID;
        fetch("/getWhoseTurn?gameID=" + gameID)
            .then(res => res.text() )
            .then(result => {
                if (result === this.state.userID)
                    this.setState({yourTurn: true});

            });
    }

    setBoard(board, addOns={}){
        let wizardSpots = new Array(4).fill("");
        let actualBoard = new Array(10).fill("").map(() => new Array(10).fill(""));
        Object.keys(board).forEach(piece => {
            let column = board[piece].charCodeAt(0) - 97;
            let row = parseInt(board[piece].charAt(1), 10);
            if(board[piece].charAt(0) === 'w')
                wizardSpots[row-1] = piece;
            else{
                row = 9 - row;
                actualBoard[row][column] = piece;
            }
        });
        let newState = {board: actualBoard, wizardSpots};
        let combined = {...newState, ...addOns};
        this.setState(combined);
    }

    getBoard(addOns={}) {
        const gameID = this.state.gameID;
        fetch("/getBoardState?gameID=" + gameID)
            .then(res => res.text() )
            .then(board => {
                board = JSON.parse(board);
                this.setBoard(board, addOns);
            })
    }

    clearTimers(){
        this.state.timers.forEach( timer => {
            clearTimeout(timer);
        })
    }

    getWhiteSpace(){
        return(
            <>
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
            </>
        );
    }

    setupDefaultWizardRowWhite(){
        let w2 = (<Square backgroundColor={orange} onClick={this.squareClick.bind(this, orange, false, "w2")}/>);
        let w1 = (<Square backgroundColor={yellow} onClick={this.squareClick.bind(this, yellow, false, "w1")}/>);
        if(this.state.wizardSpots.length > 0){
            if(this.state.wizardSpots[1] !== ""){
                let piece = this.state.wizardSpots[1].toLowerCase();
                piece = piece.substring(0, piece.length-1);
                piece = piece.split(" ")
                w2 = (<Square backgroundColor={orange} color={piece[0]} piece={piece[1]} onClick={this.squareClick.bind(this, orange, true, "w2")}/>);
            }
            if(this.state.wizardSpots[0] !== ""){
                let piece = this.state.wizardSpots[0].toLowerCase();
                piece = piece.substring(0, piece.length-1);
                piece = piece.split(" ")
                w1 = (<Square backgroundColor={yellow} color={piece[0]} piece={piece[1]} onClick={this.squareClick.bind(this, yellow, false, "w1")}/>);
            }
        }
        return(
            <tr>
                <td>{w1}</td>
                {this.getWhiteSpace()}
                <td>{w2}</td>
            </tr>
        );
    }

    setupDefaultWizardRowBlack(){
        let w4 = (<Square backgroundColor={orange} onClick={this.squareClick.bind(this, orange, false, "w4")}/>);
        let w3 = (<Square backgroundColor={yellow} onClick={this.squareClick.bind(this, yellow, false, "w3")}/>);
        if(this.state.wizardSpots.length > 0){
            if(this.state.wizardSpots[3] !== ""){
                let piece = this.state.wizardSpots[2].toLowerCase();
                piece = piece.substring(0, piece.length-1);
                piece = piece.split(" ")
                w4 = (<Square backgroundColor={orange} color={piece[0]} piece={piece[1]} onClick={this.squareClick.bind(this, orange, true, "w4")}/>);
            }
            if(this.state.wizardSpots[2] !== ""){
                let piece = this.state.wizardSpots[3].toLowerCase();
                piece = piece.substring(0, piece.length-1);
                piece = piece.split(" ")
                w3 = (<Square backgroundColor={yellow} color={piece[0]} piece={piece[1]} onClick={this.squareClick.bind(this, yellow, true, "w3")}/>);
            }
        }
        return (
            <tr>
                <td>{w4}</td>
                {this.getWhiteSpace()}
                <td>{w3}</td>
            </tr>
        );
    }

    squareClick(color, isPiece, position=""){
        if(color !== white && this.state.yourTurn){
            if(this.state.pieceSelected === "" && isPiece)
                this.setState({pieceSelected: position});
            else if(this.state.pieceSelected.length === 2 && !isPiece){
                fetch("/move?gameID="+ this.state.gameID+ "&from=" + this.state.pieceSelected + "&to=" + position)
                    .then(res => res.json())
                    .then(data => {
                        if(data.error === ""){
                            this.getBoard({yourTurn: false});
                        }
                        this.setState({pieceSelected: ""});
                    })
                    .then(() => window.location.reload(false));
            }
            else
                this.setState({pieceSelected: ""});

            // if(isPiece)
            //     console.log("Some piece clicked!");
            // else
            //     console.log("Empty square clicked!");
            // console.log("@ position: " + position);
        }
    }

    renderBoard(){
        let rowN = 0;
        let boardDisplay = [];
        this.state.board.forEach(row =>{
            let color = orange;
            if(rowN%2 === 0)
                color = yellow;

            let colN = 0;
            row = row.map(piece => {
                let position = String.fromCharCode(97+colN)+ (9-rowN);
                colN++;
                if(color === orange)
                    color = yellow;
                else
                    color = orange;
                if(piece === ""){
                    return (<td><Square backgroundColor={color} onClick={this.squareClick.bind(this, color, false, position)}/></td>);
                }
                else{
                    piece = piece.toLowerCase();
                    piece = piece.substring(0, piece.length-1);
                    piece = piece.split(" ");
                    return (<td><Square backgroundColor={color} color={piece[0]} piece={piece[1]} onClick={this.squareClick.bind(this, color, true, position)}/></td>);
                }
            });

            boardDisplay.push(
                <tr key={9-rowN}>
                    <td key={"a"+(9-rowN)}><Square backgroundColor={white}/></td>
                    {row}
                    <td key={"j"+(9-rowN)}><Square backgroundColor={white}/></td>
                </tr>
            );
            rowN++;
        });
        return(
            <>
                {boardDisplay}
            </>
        );
    }

    render(){
        if(this.state.searching === true){
            this.pingForNewMatch(this.state.userID, this.state.players.join(","));
            return(
                <div>
                    <h2>Waiting for other players to join...</h2>
                    <Grid className={style.Spinner}>
                        <CircleLoader size={100} color={"yellow"}/>
                    </Grid>
                </div>
            );
        }
        else{
            if(this.state.board.length > 0){
                let turn = <h1>It is your turn, {this.state.userID}</h1>;
                if (!this.state.yourTurn)
                    turn = <h1>It is not your turn, {this.state.userID}</h1>

                let color = <h1>Your color is: {this.state.playerColor}</h1>;
                return (
                    <Grid>
                        {turn}
                        {color}
                        <table className="App" style={{width: "auto"}} align={'center'}>
                            <tbody>
                                {this.setupDefaultWizardRowBlack()}
                                {this.renderBoard()}
                                {this.setupDefaultWizardRowWhite()}
                            </tbody>
                        </table>
                    </Grid>
                );
            }
            else{
                return(<h1>Loading...</h1>)
            }
        }
    }
}
