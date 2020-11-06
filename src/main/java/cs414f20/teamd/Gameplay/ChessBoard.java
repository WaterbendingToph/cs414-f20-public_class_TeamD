package cs414f20.teamd.Gameplay;

import java.io.*;
import cs414f20.teamd.Gameplay.ChessPiece.Color;

public class ChessBoard {
    /**
     * This class stores the state of a board and its pieces.
     */

    private ChessPiece[][] board;

    public ChessBoard() {
        /**
         * Initializes an 8x8 chess board with no pieces on it.
         */
    }

    public void initialize() {
        /**
         * Initializes the board to the standard chess opening state with indexing
         * based on standard chess notation (e.g. "e5"). Uses the placePiece method
         * to correctly position each piece.
         * 
         * @see placePiece
         */
        Rook whiteRook1 = new Rook(this, Color.WHITE);
        Rook whiteRook2 = new Rook(this, Color.WHITE);
        Bishop whiteBishop1 = new Bishop(this, Color.WHITE);
        Bishop whiteBishop2 = new Bishop(this, Color.WHITE);
        Knight whiteKnight1 = new Knight(this, Color.WHITE);
        Knight whiteKnight2 = new Knight(this, Color.WHITE);
        Queen whiteQueen = new Queen(this, Color.WHITE);
        King whiteKing = new King(this, Color.WHITE);
        Pawn whitePawn1 = new Pawn(this, Color.WHITE);
        Pawn whitePawn2 = new Pawn(this, Color.WHITE);
        Pawn whitePawn3 = new Pawn(this, Color.WHITE);
        Pawn whitePawn4 = new Pawn(this, Color.WHITE);
        Pawn whitePawn5 = new Pawn(this, Color.WHITE);
        Pawn whitePawn6 = new Pawn(this, Color.WHITE);
        Pawn whitePawn7 = new Pawn(this, Color.WHITE);
        Pawn whitePawn8 = new Pawn(this, Color.WHITE);
        
        Rook blackRook1 = new Rook(this, Color.BLACK);
        Rook blackRook2 = new Rook(this, Color.BLACK);
        Bishop blackBishop1 = new Bishop(this, Color.BLACK);
        Bishop blackBishop2 = new Bishop(this, Color.BLACK);
        Knight blackKnight1 = new Knight(this, Color.BLACK);
        Knight blackKnight2 = new Knight(this, Color.BLACK);
        Queen blackQueen = new Queen(this, Color.BLACK);
        King blackKing = new King(this, Color.BLACK);
        Pawn blackPawn1 = new Pawn(this, Color.BLACK);
        Pawn blackPawn2 = new Pawn(this, Color.BLACK);
        Pawn blackPawn3 = new Pawn(this, Color.BLACK);
        Pawn blackPawn4 = new Pawn(this, Color.BLACK);
        Pawn blackPawn5 = new Pawn(this, Color.BLACK);
        Pawn blackPawn6 = new Pawn(this, Color.BLACK);
        Pawn blackPawn7 = new Pawn(this, Color.BLACK);
        Pawn blackPawn8 = new Pawn(this, Color.BLACK);

        this.placePiece(whiteRook1, "a1");
        this.placePiece(whiteKnight1, "b1");
        this.placePiece(whiteBishop1, "c1");
        this.placePiece(whiteKing, "d1");
        this.placePiece(whiteQueen, "e1");
        this.placePiece(whiteBishop2, "f1");
        this.placePiece(whiteKnight2, "g1");
        this.placePiece(whiteRook2, "h1");
        this.placePiece(whitePawn1, "a2");
        this.placePiece(whitePawn2, "b2");
        this.placePiece(whitePawn3, "c2");
        this.placePiece(whitePawn4, "d2");
        this.placePiece(whitePawn5, "e2");
        this.placePiece(whitePawn6, "f2");
        this.placePiece(whitePawn7, "g2");
        this.placePiece(whitePawn8, "h2");
        
        this.placePiece(blackRook1, "a8");
        this.placePiece(blackKnight1, "b8");
        this.placePiece(blackBishop1, "c8");
        this.placePiece(blackKing, "d8");
        this.placePiece(blackQueen, "e8");
        this.placePiece(blackBishop2, "f8");
        this.placePiece(blackKnight2, "g8");
        this.placePiece(blackRook2, "h8");
        this.placePiece(blackPawn1, "a7");
        this.placePiece(blackPawn2, "b7");
        this.placePiece(blackPawn3, "c7");
        this.placePiece(blackPawn4, "d7");
        this.placePiece(blackPawn5, "e7");
        this.placePiece(blackPawn6, "f7");
        this.placePiece(blackPawn7, "g7");
        this.placePiece(blackPawn8, "h7");
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        /**
         * Returns the chess piece at the given position on the board. If the given
         * position is illegal because the string contains illegal characters or 
         * represents a position outside the board, throws an IllegalPositionException.
         * 
         * @param position  The two-character representation of the position, where
         *                  the first character is a lowercase a-h representing the column and the
         *                  second character is a number 1-8 representing the row.
         * @return          a ChessPiece object representing the type of piece at the position
         * @throws          IllegalPositionException
         * @see             ChessPiece
         */
        
        ChessPiece currentPiece;

        // Ensure that the position passed in is on the board (i.e. it is legal characters)
        if (position.charAt(0) >= 'a' && position.charAt(0) <= 'h' && position.charAt(1) >= '1'
                && position.charAt(1) <= '8') {
            
            int row = position.charAt(1) - 49;
            int column = position.charAt(0) - 97;
            currentPiece = this.board[row][column];
        } else {
            throw new IllegalPositionException("This is not a legal position.");
        }
        
        return currentPiece;
    }

    public boolean placePiece(ChessPiece piece, String position) {
        /**
         * Tries to place the given piece at the given position. Returns true if
         * the piece was successfully placed or false if the position is already
         * occupied by the same player, or if the move was illegal.
         * 
         * @param  piece    ChessPiece requested to be moved
         * @param  position The two-character identifier of the requested position
         * @return True     If the move is legal and able to be completed
         * @return False    If the position is already occupied by the current player
         * @return False    If the move is illegal because the requested position
         *                  is not on the board or contains illegal characters.
         */

        int row = position.charAt(1) - 49;
        int column = position.charAt(0) - 97;

        // Ensure that the move is legal (i.e. the position is on the board)
        if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
        	
        	// Check if there is already a piece in the requested position
        	// If there is a piece already in this position, ensure it is not the same color
        	if (this.board[row][column] != null) {
        		ChessPiece obstructingPiece = this.board[row][column];
        		if (obstructingPiece.color == piece.color) {
        			return false;
        		}
        	}
        	
        	// Use ChessPiece method to set new position of the piece
        	try {
            	// Account for initial placement of the pieces (when there is no "from")
            	if (piece.row == -1) {
            		piece.setPosition(position);
            		this.board[row][column] = piece;
            	} else {
	            	// Check this piece's legal moves before setting new position
	            	if (piece.legalMoves().contains(position)) {
	            		this.board[piece.row][piece.column] = null;
	            		piece.setPosition(position);
	            		this.board[row][column] = piece;
	            		return true;
	            	}
            	}
			} catch (IllegalPositionException e) {
				e.printStackTrace();
				return false;
			}
        }

        return false;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        /**
         * Checks if moving the piece from fromPosition to toPosition is a legal
         * move. Legality is defined by the rules of this particular variant of 
         * chess. If the move is legal, it executes the move, changing the value
         * of the board as needed. Otherwise, an IllegalMoveException is thrown.
         * 
         * @param  fromPosition a two-character identifier of the original position
         * @param  toPosition   a two-character identifier of the new position requested
         * @throws IllegalMoveException 
         */

        ChessPiece pieceToMove;
    	// Ensure fromPosition is legal
		try {
			pieceToMove = getPiece(fromPosition);
		} catch (IllegalPositionException e) {
			e.printStackTrace();
			return;
		}
		
		// Ensure fromPosition is not empty
    	if (pieceToMove == null) {
    		throw new IllegalMoveException("No piece to move.");
    	}
    	
    	// Change String position into a row and column for the board
        int fromRow = pieceToMove.row;
        int fromColumn = pieceToMove.column;
        int toRow = (int)(toPosition.charAt(1) - 49);
        int toColumn = (int)(toPosition.charAt(0) - 97);
    	
    	// Ensure the move is legal for that piece. If it is, make the move.
        // If this moves captures a piece, the reference will be replaced on the board,
        // so no further action is required to "capture" the piece here.
    	if (pieceToMove.legalMoves().contains(toPosition)) {
    		this.board[fromRow][fromColumn] = null;
            this.board[toRow][toColumn] = pieceToMove;
            pieceToMove.row = toRow;
            pieceToMove.column = toColumn;
    	} else {
    		throw new IllegalMoveException("Requested move is illegal.");
    	}
    }

    @Override
    public String toString() {
        /*
         * ----------------------------DO NOT EDIT----------------------------
         * Displays the chess board in a user-friendly format. This method was
         * provided by the instructor via Canvas and should not be modified.
         */

        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<7; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<7; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 7; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 8; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<7; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }
    
    public static void main(String args[]) {
        /*
         * This main method is provided for testing purposes only.
         */
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
    }
}
