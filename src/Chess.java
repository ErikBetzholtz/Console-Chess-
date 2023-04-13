import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {
        Piece[][] board = initBoard();
        boolean whiteToPlay = true;
        Piece pieceToMove;
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        boolean dispMode = chooseDispMode(input);
        while (!gameOver) { // Main game loop
            printBoard(board, dispMode);
            if (whiteToPlay) {
                System.out.println("White's turn");
            } else {
                System.out.println("Black's turn");
            }
            pieceToMove = choosePiece(whiteToPlay, board, input);
            System.out.println("Choose square to move piece to");
            ChessSquare moveTo = chooseSquare(input);

            if (pieceToMove.getLegalMoves().contains(moveTo)) {
                // Loop ser till att spelarens drag registreras som en passant om det är det
                for (ChessSquare move : pieceToMove.getLegalMoves()) {
                    if (move.equals(moveTo)) {
                        moveTo.setEnPassantMove(move.getEnPassantMove());
                    }
                }

                pieceToMove.move(moveTo);
                resetEnPassants(whiteToPlay, board);
                whiteToPlay = !whiteToPlay;
                if (King.isInCheckMate(whiteToPlay)) {
                    gameOver = true;
                    printBoard(board, dispMode);
                    System.out.println("Checkmate! Game over");
                } else if (King.isInStalemate(whiteToPlay)) {
                    gameOver = true;
                    printBoard(board, dispMode);
                    System.out.println("Stalemate! Game over");
                } else if (King.isInCheck(whiteToPlay)) {
                    System.out.println("Check!");
                }
            } else {
                System.out.println("Illegal move, try again");
            }

        }
        input.close();

    }

    /**
     * Handles user input for choosing a square by assuring the input has the
     * correct format.
     * 
     * @param input The scanner object through wich the user input is read.
     * @return The square chosen by the user.
     */
    public static ChessSquare chooseSquare(Scanner input) {

        String squareString;
        ChessSquare square = null;
        boolean squareChosen = false;
        while (!squareChosen) {
            System.out.print("Choose square using a letter A-H then a number 1-8(e.g. G5): ");
            squareString = input.nextLine();
            if (squareString.length() == 2) {
                squareString = squareString.toUpperCase();
                try {
                    square = new ChessSquare(Letters.valueOf(Character.toString(squareString.charAt(0))),
                            Character.getNumericValue(squareString.charAt(1)));
                    if (square.getNumber() > 8 || square.getNumber() < 1) {
                        throw new IllegalArgumentException();
                    }
                    squareChosen = true;

                } catch (IllegalArgumentException e) {
                    System.out.println("Incorrect input, try again");
                }

            } else {
                System.out.println("Incorrect input, try again");
            }

        }

        return square;
    }

    /**
     * Handles user input for choosing a piece to move by assuring the input has the
     * correct format and that the chosen square conatains an allied piece.
     * 
     * @param whiteToPlay The side of the current player.
     * @param board       The board containing all the pieces.
     * @param input       The scanner object through wich the user input is read.
     * @return The piece chosen by the user.
     */
    public static Piece choosePiece(boolean whiteToPlay, Piece[][] board, Scanner input) {

        boolean currentSide = whiteToPlay;
        boolean pieceChosen = false;
        int x = 0;
        int y = 0;

        while (!pieceChosen) {
            System.out.println("First choose which piece you wish to move");
            ChessSquare square = chooseSquare(input);
            x = square.getLetterValue() - 1;
            y = square.getNumber() - 1;
            if (board[x][y] != null && board[x][y].getSide() == currentSide) {
                pieceChosen = true;
                System.out.println("You chose " + board[x][y].toString());
            } else {
                System.out.println("You don't have a chess piece on this square");
            }

        }
        return board[x][y];
    }

    /**
     * Creates a new board and places all the pieces in their starting positions.
     * 
     * @return The new board with pieces in their starting positions.
     */
    private static Piece[][] initBoard() {
        Piece[][] board = new Piece[8][8];
        board[0][0] = new Rook(board, 0, 0, true);
        board[1][0] = new Knight(board, 1, 0, true);
        board[2][0] = new Bishop(board, 2, 0, true);
        board[3][0] = new Queen(board, 3, 0, true);
        board[4][0] = new King(board, 4, 0, true);
        board[5][0] = new Bishop(board, 5, 0, true);
        board[6][0] = new Knight(board, 6, 0, true);
        board[7][0] = new Rook(board, 7, 0, true);

        board[0][7] = new Rook(board, 0, 7, false);
        board[1][7] = new Knight(board, 1, 7, false);
        board[2][7] = new Bishop(board, 2, 7, false);
        board[3][7] = new Queen(board, 3, 7, false);
        board[4][7] = new King(board, 4, 7, false);
        board[5][7] = new Bishop(board, 5, 7, false);
        board[6][7] = new Knight(board, 6, 7, false);
        board[7][7] = new Rook(board, 7, 7, false);

        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(board, i, 1, true);
            board[i][6] = new Pawn(board, i, 6, false);
        }

        return board;
    }

    private static boolean chooseDispMode(Scanner input) {
        System.out.println("");
        System.out.println("Select what typ of display mode you want to play with.");
        System.out.println("");
        System.out.println("In letter-mode the white king looks like this: Kw");
        System.out.println("In symbol-mode the white king looks like this: \u265A");
        System.out.println("");
        boolean mode = false;
        boolean flag = true;
        while (flag) {

            System.out.println("Pick your preferred mode by typing:");
            System.out.println("\"L\" for letter-mode");
            System.out.println("\"S\" for symbol-mode");
            String choise = input.nextLine();
            choise = choise.toUpperCase();

            if (choise.equals("L") || choise.equals("S")) {

                switch (choise) {
                    case "L":
                        mode = false;
                        flag = false;
                        break;
                    case "S":
                        mode = true;
                        flag = false;
                        break;
                }
            } else {
                System.out.println("Incorrect input, try again");
            }

        }
        return mode;
    }

    /**
     * Prints the current state of the board to the console.
     * 
     * @param board The board to be printed.
     */
    private static void printBoard(Piece[][] board, boolean mode) {

        System.out.print("   ");
        for (int k = 1; k <= 8; k++) {
            System.out.printf(" %2d ", k);
        }
        System.out.println("");

        for (int i = 0; i < 8; i++) {
            System.out.print(Letters.getLetter(i + 1) + "  ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (mode) {
                        System.out.printf("|%2s ", board[i][j].getSymbol());
                    } else {
                        System.out.printf("|%2s ", board[i][j].getSign());
                    }
                } else {
                    System.out.printf("|%2s ", "");
                }
            }
            System.out.println("|  " + Letters.getLetter(i + 1));
        }

        System.out.print("   ");
        for (int k = 1; k <= 8; k++) {
            System.out.printf(" %2d ", k);
        }
        System.out.println("");
    }

    /**
     * Sets all fields canEnPassant to false and sets all fields enPassantSquare to
     * null for all pieces on a side.
     * 
     * @param side  The side of pieces to be affected.
     * @param board The board containing the pieces.
     */
    static void resetEnPassants(boolean side, Piece[][] board) {
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null && piece instanceof Pawn && piece.getSide() == side) {
                    Pawn pawn = (Pawn) piece;
                    pawn.setEnPassant(false, null);
                }
            }
        }
    }

}
