import java.util.List;
import java.util.Iterator;

public abstract class Piece {
    protected static Piece[][] board;
    protected int x;
    protected int y;
    protected boolean side;
    protected ChessSquare currentSquare;
    protected String symbol; // Unicode for the specific piece.
    protected String letter;
    protected boolean moved;

    public Piece(Piece[][] board, int x, int y, boolean side) {
        moved = false;
        Piece.board = board;
        this.x = x;
        this.y = y;
        this.side = side;
        currentSquare = new ChessSquare(x + 1, y + 1);
    }

    /**
     * Moves a piece to the specified square on the board and sets its field moved
     * to true.
     * 
     * @param sq The square to move the piece to.
     */
    public void move(ChessSquare sq) {
        int newX = sq.getLetter().getValue() - 1;
        int newY = sq.getNumber() - 1;
        board[newX][newY] = this;
        board[x][y] = null;
        x = newX;
        y = newY;
        currentSquare = sq;
        moved = true;
    }

    /**
     * Moves a piece to the specified square on the board without setting its field
     * move to true.
     * 
     * @param sq The square to move the piece to.
     */
    public void fakeMove(ChessSquare sq) {
        int newX = sq.getLetter().getValue() - 1;
        int newY = sq.getNumber() - 1;
        board[newX][newY] = this;
        board[x][y] = null;
        x = newX;
        y = newY;
        currentSquare = sq;
    }

    public boolean getSide() {
        return side;
    }
    public abstract String toString();

    /**
     * Calculates all possible naive moves available to a piece.
     * 
     * @return A list of moves.
     */
    abstract List<ChessSquare> getPieceMoves();

    /**
     * Determines all legal moves available to a piece. Such moves do not result in
     * the king being in check.
     * 
     * @return A list of legal moves.
     */
    public List<ChessSquare> getLegalMoves() {
        List<ChessSquare> legalMoves = getPieceMoves();
        Iterator iter = legalMoves.iterator();
        while (iter.hasNext()) {
            ChessSquare sq = (ChessSquare) iter.next();
            int enPassantOffset = 0;
            if (sq.getEnPassantMove()) {
                if (side) {
                    enPassantOffset = -1;
                } else {
                    enPassantOffset = 1;
                }
            }
            boolean removed = false;
            // Tillåter inte en kung att gå in i en motståndarkungens område
            // Kollades tidigare i King.isInCheck(), men flyttades hit för att undvika
            // stackoverflowexception
            if (this instanceof King) {
                King opposingKing = null;
                for (Piece[] pieces : board) {
                    for (Piece piece : pieces) {
                        if (piece instanceof King && piece.getSide() != side) {
                            opposingKing = (King) piece;
                        }
                    }
                }
                if (opposingKing.getPieceMoves().contains(sq) && !removed) {
                    iter.remove();
                    removed = true;
                }
            }

            ChessSquare previousSquare = this.getSquare();
            Piece takenPiece = board[sq.getLetterValue() - 1][sq.getNumber() - 1 + enPassantOffset];
            fakeMove(sq);

            if (King.isInCheck(side) && !removed) {
                iter.remove();
                removed = true;
            }
            fakeMove(previousSquare);
            board[sq.getLetterValue() - 1][sq.getNumber() - 1 + enPassantOffset] = takenPiece;
        }
        return legalMoves;
    }

    /**
     * Determines if a specific move is possible for a piece. A move is considered
     * possible if the square to move to is either empty or of the opposing players
     * color. It should also not result in the current players king being in check.
     * 
     * @param xBoardindex The x value for location of the move.
     * @param yBoardIndex The y value for location of the move.
     * @return Boolean indicating if the move is possible.
     */
    protected boolean movePossible(int xBoardIndex, int yBoardIndex) {

        if (xBoardIndex <= 7 && yBoardIndex <= 7 && xBoardIndex >= 0 && yBoardIndex >= 0) {
            if (board[xBoardIndex][yBoardIndex] == null || board[xBoardIndex][yBoardIndex].getSide() != side) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return The current square of a piece.
     */
    public ChessSquare getSquare() {
        return currentSquare;
    }

    /**
     * @return A string containing unicode for the symbol of a piece.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return Boolean indicating if a piece has been moved.
     */
    public boolean getMoved() {
        return moved;
    }

    public String getSign() {
        return letter;
    }

    /**
     * Sets the field moved to the specified value.
     * 
     * @param moved The value to be assigned to the field moved.
     */
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

}
