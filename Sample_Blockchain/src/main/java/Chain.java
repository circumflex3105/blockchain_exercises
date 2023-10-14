import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Chain {
    private final static Logger logger = LoggerFactory.getLogger(Chain.class);
    final private List<Block> blocks = new ArrayList<>();

    public void addBlock(String data) {
        if(blocks.isEmpty()) {
            blocks.add(new Block(data));
        } else {
            Block prevBlock = blocks.get(blocks.size() - 1);
            blocks.add(new Block(hash(prevBlock), data));
        }
        try {
            validateChain();
        } catch (InvalidChainException ex) {
            Rollback(ex.getLastValidBlockIndex());
        }
        logger.info("Block successfully added!");
    }

    private String hash(Block block) {
        return String.valueOf(block.hashCode());
    }

    private void validateChain() throws InvalidChainException {
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

    private void Rollback(int lastValidBlockIndex) {
        logger.warn("Rollback to last valid Block with index: {}...", lastValidBlockIndex);
        for(int i = blocks.size() - 1; i > lastValidBlockIndex; i--) {
            blocks.remove(i);
        }
        logger.info("Rollback complete.");
    }
}
