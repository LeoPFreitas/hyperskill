package blockchain;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockChain implements Serializable {

    private final List<Block> blockchain;

    public BlockChain() {
        this.blockchain = new ArrayList<>();
    }

    public void generateBlock(String data, int n) {
        if (this.blockchain.size() == 0) {
            Block block = new Block("0", 1L, new Date().getTime(), data);

            long startTime = System.currentTimeMillis();
            block.mineBlock(n);
            long endTime = System.currentTimeMillis();
            block.setSeconds(startTime, endTime);

            // validate blockchain and save
            if (validateBlockChain()) {
                this.blockchain.add(block);
                SerializationUtils.serialize(blockchain);
            }
        }

        Block last = blockchain.get(blockchain.size() - 1);
        Block block = new Block(last.getBlockHash(), last.getId() + 1L, new Date().getTime(), data);

        long startTime = System.currentTimeMillis();
        block.mineBlock(n);
        long endTime = System.currentTimeMillis();
        block.setSeconds(startTime, endTime);

        // validates the blockchain
        blockchain.add(block);
    }

    public boolean validateBlockChain() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i == 0 ? "0" : blockchain.get(i).getPreviousBlockHash();
            flag = blockchain.get(i).getBlockHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousBlockHash());
            if (!flag) break;
        }
        return flag;
    }

    /* GETTERS  AND SETTERS */

    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }



}
