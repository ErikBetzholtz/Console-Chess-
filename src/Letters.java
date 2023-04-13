public enum Letters {
    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

    private int value;

    private Letters(int value) {
        this.value = value;
    }

    /**
     * @return The value of a letter
     */
    public int getValue() {
        return value;
    }

    /**
     * Converts integer to corresponding letter and retrieves it.
     * 
     * @param number The integer to be converted.
     * @return The letter for specified integer.
     * @throws IllegalArgumentException
     */
    public static Letters getLetter(int number) throws IllegalArgumentException {
        for (Letters letter : Letters.values()) {
            if (letter.getValue() == number) {
                return letter;
            }
        }
        throw new IllegalArgumentException();
    }
}
