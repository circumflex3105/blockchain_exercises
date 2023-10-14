public class Main {
    public static void main(String[] args) {
        Chain blockchain = new Chain();
        blockchain.addBlock("Ich bin der Initialblock!");
        blockchain.addBlock("Ich bin der zweite Block!");
        blockchain.addBlock("Block mit Index 2!");
        blockchain.addBlock("Block mit Index 3!");
    }
}