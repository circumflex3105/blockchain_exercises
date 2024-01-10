import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Chain {
    private final static Logger logger = LoggerFactory.getLogger(Chain.class);
    final private List<Block> blocks = new ArrayList<>();

    /**
     * Adds a new Block to the Chain (As first or following element)
     *
     * @param data Data to be added to the chain in a new Block
     */
    public void addBlock(String data) {
        if(blocks.isEmpty()) {
            blocks.add(new Block(data));
        } else {
            Block prevBlock = blocks.get(blocks.size() - 1);
            try {
                blocks.add(new Block(hash(prevBlock), data));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            }
        }
        try {
            validateChain();
        } catch (InvalidChainException ex) {
            rollback(ex.getLastValidBlockIndex());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
        logger.info("Block successfully added!");
    }

    /**
     * calculates a hash for a block
     *
     * @param block The block, that is to be hashed
     * @return a 256 character String hash
     * @throws NoSuchAlgorithmException if MessageDigest Class is changed
     */
    private String hash(Block block) throws NoSuchAlgorithmException {
        final String value = block.getData() + block.getPrevHash();
        final byte[] hash = MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
        return new String(hash, StandardCharsets.UTF_8);
    }

    /**
     * Validates the Hash Chain through the whole blockchain
     *
     * @throws InvalidChainException If a block is invalid
     * @throws NoSuchAlgorithmException if Java changes the MessageDigest functionality
     */
    private void validateChain() throws InvalidChainException, NoSuchAlgorithmException {
        if(!blocks.get(0).isFirst() || blocks.get(0).getPrevHash() != null) {
            logger.error("Validation of start block failed!");
            throw new InvalidChainException(-1);
        }
        for(int i = 1; i < blocks.size(); i++) {
            String currentBlockPrevHash = blocks.get(i).getPrevHash();
            String previousBlockHash = hash(blocks.get(i - 1));
            if(!currentBlockPrevHash.equals(previousBlockHash)) {
                logger.error("Validation failed at index {}!", i);
                throw new InvalidChainException(i - 1);
            }
        }
    }

    /**
     * Performs the rollback to last valid Block
     *
     * @param lastValidBlockIndex Index of last valid Block in Chain
     */
    private void rollback(int lastValidBlockIndex) {
        logger.warn("Rollback to last valid Block with index: {}...", lastValidBlockIndex);
        for(int i = blocks.size() - 1; i > lastValidBlockIndex; i--) {
            blocks.remove(i);
        }
        logger.info("Rollback complete.");
    }
}
