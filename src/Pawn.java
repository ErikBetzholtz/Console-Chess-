import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Pawn extends Piece {

    private boolean canEnPassant;
    private ChessSquare enPassantSquare;

    public Pawn(Piece[][] board, int x, int y, boolean side) {
        super(board, x, y, side);
        if (side == true) {
            this.symbol = "\u265F";
            this.letter = "Pw";
        } else {
            this.symbol = "\u2659";
            this.letter = "Pb";
        }
        canEnPassant = false;
    }

    public List<ChessSquare> getPieceMoves() {
        int moveX;
        int moveY;
        List<ChessSquare> correctMoves = new ArrayList<ChessSquare>();

        if (y < 7 && side == true) {
            moveX = x;
            moveY = y + 1;
            if (board[moveX][moveY] == null && movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));

                if (moved == false && board[moveX][moveY + 1] == null && movePossible(moveX, moveY + 1)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 2));
                }
            }
            if (x < 7) {
                moveX = x + 1;
                moveY = y + 1;
                if (board[moveX][moveY] != null && movePossible(moveX, moveY)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                }
                if (canEnPassant && enPassantSquare.getLetterValue() == moveX + 1
                        && enPassantSquare.getNumber() == moveY + 1) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1, true));
                }
            }
            if (x > 0) {
                moveX = x - 1;
                moveY = y + 1;
                if (board[moveX][moveY] != null && movePossible(moveX, moveY)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                }
                if (canEnPassant && enPassantSquare.getLetterValue() == moveX + 1
                        && enPassantSquare.getNumber() == moveY + 1) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1, true));
                }
            }
        }
        if (y > 0 && side == false) {
            moveX = x;
            moveY = y - 1;
            if (board[moveX][moveY] == null && movePossible(moveX, moveY)) {
                correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                if (moved == false && board[moveX][moveY - 1] == null && movePossible(moveX, moveY - 1)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY));
                }
            }
            if (x < 7) {
                moveX = x + 1;
                moveY = y - 1;
                if (board[moveX][moveY] != null && movePossible(moveX, moveY)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                }
                if (canEnPassant && enPassantSquare.getLetterValue() == moveX + 1
                        && enPassantSquare.getNumber() == moveY + 1) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1, true));
                }
            }
            if (x > 0) {
                moveX = x - 1;
                moveY = y - 1;
                if (board[moveX][moveY] != null && movePossible(moveX, moveY)) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1));
                }
                if (canEnPassant && enPassantSquare.getLetterValue() == moveX + 1
                        && enPassantSquare.getNumber() == moveY + 1) {
                    correctMoves.add(new ChessSquare(moveX + 1, moveY + 1, true));
                }
            }
        }

        return correctMoves;
    }

    public void move(ChessSquare sq) {
        int prevY = y;

        super.move(sq);
        int newY = y;

        // Säger åt andra
        if (Math.abs(prevY - newY) == 2) {
            Pawn enPassantPawn;
            ChessSquare enPassantSquare;
            if (side) {
                enPassantSquare = new ChessSquare(x + 1, y, true);
            } else {
                enPassantSquare = new ChessSquare(x + 1, y + 2, true);
            }
            if (x < 7) {
                if (board[x + 1][y] instanceof Pawn) {
                    enPassantPawn = (Pawn) board[x + 1][y];
                    enPassantPawn.setEnPassant(true, enPassantSquare);
                }

            }
            if (x > 0) {
                if (board[x - 1][y] instanceof Pawn) {
                    enPassantPawn = (Pawn) board[x - 1][y];
                    enPassantPawn.setEnPassant(true, enPassantSquare);
                }
            }
        }

        if (sq.getEnPassantMove()) {
            int enPassantOffset;
            if (side) {
                enPassantOffset = -1;
            } else {
                enPassantOffset = 1;
            }
            board[sq.getLetterValue() - 1][sq.getNumber() - 1 + enPassantOffset] = null;
        }
        promotion(sq, side);

    }

    /**
     * Changes a pawn into a piece chosen by the player when reaching the final rank
     * of the board.
     * 
     * @param sq       The square which the pawn is moved to.
     * @param pawnSide The side of the pawn.
     */
    private void promotion(ChessSquare sq, boolean pawnSide) {

        boolean flag = true;
        int lastRank;
        if (pawnSide == true) {
            lastRank = 8;
        } else {
            lastRank = 1;
        }

        if (sq.getNumber() == lastRank) {
            System.out.println("Pick what piece to promote your pawn to by typing:");
            System.out.println("\"Q\" for Queen ");
            System.out.println("\"N\" for Knight");
            System.out.println("\"B\" for Bishop");
            System.out.println("\"R\" for Rook");

            Scanner input = new Scanner(System.in);

            while (flag) {
                String choice = input.nextLine();
                choice = choice.toUpperCase();
                if (choice.equals("Q") || choice.equals("N") || choice.equals("B") || choice.equals("R")) {
                    switch (choice) {
                        case "Q":
                            board[x][y] = new Queen(board, x, y, pawnSide);
                            flag = false;
                            break;
                        case "N":
                            board[x][y] = new Knight(board, x, y, pawnSide);
                            flag = false;
                            break;
                        case "B":
                            board[x][y] = new Bishop(board, x, y, pawnSide);
                            flag = false;
                            break;
                        case "R":
                            board[x][y] = new Rook(board, x, y, pawnSide);
                            flag = false;
                            break;
                    }

                } else {
                    System.out.println("Incorrect input, try again");
                }
            }
        }
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    /**
     * Sets the fields canEnPassant and enPassantSquare to the specified values.
     * 
     * @param canEnPassant    The value to be assigned to the field canEnPassant
     * @param enPassantSquare The value to be assigned to the field enPassantSquare
     */
    public void setEnPassant(boolean canEnPassant, ChessSquare enPassantSquare) {
        this.canEnPassant = canEnPassant;
        this.enPassantSquare = enPassantSquare;
    }

    public String toString(){
        return "Pawn";
    }

}
