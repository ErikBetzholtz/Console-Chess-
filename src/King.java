import java.util.List;
import java.util.ArrayList;

public class King extends Piece {

    public King(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (this.side == true) {
            this.symbol = "\u265A";
            this.letter = "Kw";
        } else {
            this.symbol = "\u2654";
            this.letter = "Kb";
        }
    }

    public List<ChessSquare> getPieceMoves() {
        List<ChessSquare> correctMoves = new ArrayList<ChessSquare>();
        int moveY = y;
        int moveX = x;

        moveY = y + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveY = y - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveY = y;
        moveX = x + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveY = y + 1;
        moveX = x + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveY = y - 1;
        moveX = x + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveY = y + 1;
        moveX = x - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 1;
        moveY = y - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }

        if (!King.isInCheck(side)) {

            boolean clearDown = true;
            boolean clearUp = true;
            if (moved == false) {
                for (int i = x + 1; i < 7; i++) {

                    if (board[i][y] != null) {
                        clearDown = false;
                    }
                }
                if (board[7][y] != null && !board[7][y].getMoved() && clearDown) {
                    correctMoves.add(new ChessSquare(7, y + 1));
                }
                for (int i = x - 1; i > 0; i--) {
                    if (board[i][y] != null) {
                        clearUp = false;
                    }
                }
                if (board[0][y] != null && !board[0][y].getMoved() && clearUp) {
                    correctMoves.add(new ChessSquare(3, y + 1));
                }
            }
        }
        return correctMoves;

    }

    public void move(ChessSquare sq) {
        if (moved == false) {
            if (sq.getLetterValue() == 7) {
                board[7][y].move(new ChessSquare(6, y + 1));
            }
            if (sq.getLetterValue() == 3) {
                board[0][y].move(new ChessSquare(4, y + 1));
            }
        }
        super.move(sq);
    }

    public void fakeMove(ChessSquare sq) {
        if (moved == false && x == 4) { // Code for moving the rook if castling is performed.
            if (sq.getLetterValue() == 7) {
                board[7][y].fakeMove(new ChessSquare(6, y + 1));
            }
            if (sq.getLetterValue() == 3) {
                board[0][y].fakeMove(new ChessSquare(4, y + 1));
            }
        } else if (moved == false && x == 2 && sq.getLetterValue() == 5) {
            board[x + 1][y].fakeMove(new ChessSquare(1, y + 1));
        } else if (moved == false && x == 6 && sq.getLetterValue() == 5) {
            board[x - 1][y].fakeMove(new ChessSquare(8, y + 1));
        }

        super.fakeMove(sq);
    }

    /**
     * Determines if the king of a given side is in check.
     * 
     * @param side The side of the king to control.
     * @return Boolean indicating if the king is in check.
     */
    public static boolean isInCheck(boolean side) {
        King king = null;
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece instanceof King && piece.side == side) {
                    king = (King) piece;
                }
            }
        }

        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null && side != piece.getSide() && !(piece instanceof King)
                        && piece.getPieceMoves().contains(king.getSquare())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the king of a given side is in check mate.
     * 
     * @param side The side of the king to control.
     * @return Boolean indicating if the king is in check mate.
     */
    public static boolean isInCheckMate(boolean side) {
        if (!King.isInCheck(side)) {
            return false;
        }
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null && piece.getSide() == side && !piece.getLegalMoves().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines if the king of a given side is in a stalemate.
     * 
     * @param side The side of the king to control.
     * @return Boolean indicating if the king is in a stalemate.
     */
    public static boolean isInStalemate(boolean side) {
        if (King.isInCheck(side)) {
            return false;
        }
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null && piece.getSide() == side && !piece.getLegalMoves().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        return "King";
    }
}
