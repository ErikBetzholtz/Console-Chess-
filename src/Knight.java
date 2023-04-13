import java.util.List;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (side == true) {
            this.symbol = "\u265E";
            this.letter = "Nw";
        } else {
            this.symbol = "\u2658";
            this.letter = "Nb";
        }
    }

    public List<ChessSquare> getPieceMoves() {
        List<ChessSquare> correctMoves = new ArrayList<ChessSquare>();
        int moveX = x + 1;
        int moveY = y + 2;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 1;
        moveY = y + 2;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x + 1;
        moveY = y - 2;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 1;
        moveY = y - 2;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x + 2;
        moveY = y + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x + 2;
        moveY = y - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 2;
        moveY = y + 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        moveX = x - 2;
        moveY = y - 1;
        if (movePossible(moveX, moveY)) {
            correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
        }
        return correctMoves;
    }

    public String toString(){
        return "Knight";
    }
}
