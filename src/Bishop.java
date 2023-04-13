import java.util.List;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (side == true) {
            this.symbol = "\u265D";
            this.letter = "Bw";
        } else {
            this.symbol = "\u2657";
            this.letter = "Bb";
        }
    }

    public List<ChessSquare> getPieceMoves() {
        List<ChessSquare> correctMoves = new ArrayList<ChessSquare>();
        int moveY = y;
        int moveX = x;

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

        return correctMoves;

    }

    public String toString(){
        return "Bishop";
    }

}
