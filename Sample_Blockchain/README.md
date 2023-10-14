# Sample_Blockchain

## A simple application to create a functioning Blockchain

### Overview of Classes used

#### Block
  - A single Block of a Blockchain
  - Has 4 fields
    1. boolean first (true if it is the first Block of the blockchain)
    2. String prevHash (null if it is the first Block, else it has the HashCode of the previous Block)
    3. long timestamp (timestamp of creation)
    4. String data (a string of sample data)
  - has getter Methods but no setter (since created Blocks cannot be modified)

#### Chain
  - has a List<Block> as a field
  - Methods for blockchain functionality
    1. addBlock: adds a new Block to the chain and validates the chain
    2. hash: returns the hashCode of a Block
    3. validateChain: validates the complete blockchain and throws a custom InvalidChainException
    4. Rollback: used to Rollback to the last valid Block of the Chain

#### InvalidChainException
  - has the index of the last valid Block as a field (-1 if the first element was already corrupt)
  - getter for the index

#### Main
  - Creates an instance of Chain and a few sample blocks to be added to it
