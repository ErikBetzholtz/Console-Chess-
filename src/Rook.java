import java.util.List;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (side == true) {
            this.symbol = "\u265C";
            this.letter = "Rw";
        } else {
            this.symbol = "\u2656";
            this.letter = "Rb";
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

        return correctMoves;

    }

    public String toString(){
        return "Rook";
    }
}
