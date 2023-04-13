import java.util.List;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (side == true) {
            this.symbol = "\u265B";
            this.letter = "Qw";
        } else {
            this.symbol = "\u2655";
            this.letter = "Qb";
        }
    }

    public List<ChessSquare> getPieceMoves() {
        List<ChessSquare> correctMoves = new ArrayList<ChessSquare>();
        int moveY = y;
        int moveX = x;

        while (true) {
            moveY++;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveY = y;
        while (true) {
            moveY--;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveY = y;
        while (true) {
            moveX++;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveX = x;
        while (true) {
            moveX--;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveX = x;

        while (true) {
            moveY++;
            moveX++;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveY = y;
        moveX = x;
        while (true) {
            moveY--;
            moveX++;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveY = y;
        moveX = x;
        while (true) {
            moveY++;
            moveX--;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveX = x;
        moveY = y;
        while (true) {
            moveY--;
            moveX--;
            if (movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (board[moveX][moveY] != null) {
                    break;
                }
            } else {
                break;
            }
        }
        moveX = x;
        moveY = y;

        return correctMoves;

    }

    public String toString(){
        return "Queen";
    }
}
