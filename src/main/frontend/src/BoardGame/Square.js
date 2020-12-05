import React, {Component} from 'react';

export default class Square extends Component{

    constructor(props){
        super(props);
        this.state = {
            position: this.props.position
        }
        this.backgroundColor = props.backgroundColor;
        this.backgroundImage = null;
        this.setDisplayedPiece(this.props.color, this.props.piece)
        this.setDisplayedPiece = this.setDisplayedPiece.bind(this);
    }

    // ********************* NOTE TO PERSON IMPLEMENTING THE ON-CLICK *********************
    //                       WHITE IS USED AS A BACKGROUND COLOR FOR FILLER SQUARES TO DISPLAY BOARD CORRECTLY
    //                       IF THE BACKGROUND COLOR IS WHITE, THE ON-CLICK SHOULD NOT DO ANYTHING

    setDisplayedPiece(pieceColor, piece){
        switch (piece) {
            case "pawn":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/4/45/Chess_plt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/c/c7/Chess_pdt45.svg)";
                break;
            case "rook":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/7/72/Chess_rlt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/f/ff/Chess_rdt45.svg)";
                break;
            case "knight":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/7/70/Chess_nlt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/e/ef/Chess_ndt45.svg)";
                break;
            case "bishop":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/b/b1/Chess_blt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/9/98/Chess_bdt45.svg)";
                break;
            case "king":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/4/42/Chess_klt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/f/f0/Chess_kdt45.svg)";
                break;
            case "queen":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/1/15/Chess_qlt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/4/47/Chess_qdt45.svg)";
                break;
            case "champion":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/d/d1/Chess_zlt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/5/5e/Chess_zdt45.svg)";
                break;
            case "wizard":
                if (pieceColor === "white")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/8/85/Chess_wlt45.svg)";
                else if (pieceColor === "black")
                    this.backgroundImage = "url(https://upload.wikimedia.org/wikipedia/commons/7/7e/Chess_wdt45.svg)";
                break;
            default:
                break;
        }
    }

    render(){
        return (
            <div style = {{
                backgroundColor: this.props.backgroundColor,
                width: '50px',
                height: '50px',
                border: "2px solid",
                borderColor : this.props.backgroundColor,
                backgroundImage: this.backgroundImage}} onClick={this.props.onClick}>
            </div>);
    }
}
