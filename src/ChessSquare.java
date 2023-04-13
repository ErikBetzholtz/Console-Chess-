public class ChessSquare {
    private int number;
    private Letters letter;
    private boolean enPassantMove;

    public ChessSquare(Letters letter, int number) {
        this.letter = letter;
        this.number = number;
        this.enPassantMove = false;
    }

    public ChessSquare(Letters letter, int number, boolean enPassantMove) {
        this.letter = letter;
        this.number = number;
        this.enPassantMove = enPassantMove;
    }

    public ChessSquare(int letterValue, int number) throws IllegalArgumentException {
        this.letter = Letters.getLetter(letterValue);
        this.number = number;
        this.enPassantMove = false;
    }

    public ChessSquare(int letterValue, int number, boolean enPassantMove) throws IllegalArgumentException {
        this.letter = Letters.getLetter(letterValue);
        this.number = number;
        this.enPassantMove = enPassantMove;
    }

    public int getNumber() {
        return number;
    }

    public int getLetterValue() {
        return letter.getValue();
    }

    public Letters getLetter() {
        return letter;
    }

    public boolean getEnPassantMove() {
        return enPassantMove;
    }

    public void setEnPassantMove(boolean enPassantMove) {
        this.enPassantMove = enPassantMove;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessSquare)) {
            return false;
        }
        ChessSquare sq = (ChessSquare) o;
        return (sq.getLetterValue() == letter.getValue() && sq.getNumber() == number);
    }
}
