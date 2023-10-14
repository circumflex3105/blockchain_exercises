public class Block {
    final private boolean first;
    final private String prevHash;
    final private long timestamp;
    final private String data;

    public Block(String data) {
        this.first = true;
        this.prevHash = null;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public Block(String prevHash, String data) {
        this.first = false;
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
