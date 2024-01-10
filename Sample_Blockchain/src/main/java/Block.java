public class Block {
    final private boolean first;
    final private String prevHash;
    final private long timestamp;
    final private String data;

    public Block(String data) {
        this(true, null, data);
    }

    public Block(String prevHash, String data) {
        this(false, prevHash, data);
    }

    private Block(boolean first, String prevHash, String data) {
        this.first = first;
        this.prevHash = prevHash;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public boolean isFirst() {
        return first;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getPrevHash() {
        return prevHash;
    }
}
