package blockchain;

import java.io.Serializable;

public class Block implements Serializable {

    private final String previousBlockHash;
    private String blockHash;
    private final String data;
    private final long id;
    private final long timestamp;
    private long magicNumber;
    public long seconds;

    public Block(String previousBlockHash, long id, long timestamp, String data) {
        this.data = data;
        this.previousBlockHash = previousBlockHash;
        this.id = id;
        this.timestamp = timestamp;
        this.blockHash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        return StringUtil.applySha256( previousBlockHash + id + data + timestamp + magicNumber);
    }

    public void mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!blockHash.substring(0, prefix).equals(prefixString)) {
            magicNumber++;
            blockHash = calculateBlockHash();
        }
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Block: " + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block:" + "\n" + previousBlockHash + "\n" +
                "Hash of the block: " + "\n" + blockHash + "\n" +
                "Block was generating for " + seconds +" seconds" + "\n";
    }

    public void setSeconds(Long startTime, Long endTime) {
        this.seconds = (endTime - startTime) / 1000;
    }
}
