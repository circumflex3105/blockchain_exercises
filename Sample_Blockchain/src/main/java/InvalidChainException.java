public class InvalidChainException extends Exception {
    final private int lastValidBlockIndex;
    public InvalidChainException(int lastValidBlockIndex) {
        super("Blockchain validation failed at index " + lastValidBlockIndex + 1);
        this.lastValidBlockIndex = lastValidBlockIndex;
    }

    public int getLastValidBlockIndex() {
        return lastValidBlockIndex;
    }
}
