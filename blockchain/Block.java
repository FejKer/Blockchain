package blockchain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Block implements Comparable<Block> {
    private static Long lastId = 1L;
    private Long id;
    private final Long timestamp;
    private final String previousBlock;
    private final String thisBlock;
    private final Long magicNumber;
    private final Long secondsToGenerate;
    private final Miner foundByMiner;
    private final int zeros;
    private List<String> messages;

    public Block(long timestamp, long magicNumber, long secondsToGenerate, String thisBlock, Miner foundByMiner) {
        this.timestamp = timestamp;
        this.previousBlock = Blockchain.getLastBlockValue();
        this.magicNumber = magicNumber;
        this.secondsToGenerate = secondsToGenerate;
        this.thisBlock = thisBlock;
        this.foundByMiner = foundByMiner;
        this.zeros = Blockchain.getZeros();
        messages = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = lastId++;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousBlock() {
        return previousBlock;
    }

    public String getThisBlock() {
        return thisBlock;
    }

    public static Long getLastId() {
        return lastId;
    }

    public Long getSecondsToGenerate() {
        return secondsToGenerate;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void printInfo() {
        System.out.println("Block:");
        System.out.println("Created by miner" + foundByMiner.getMinerId());
        System.out.println("miner" + foundByMiner.getMinerId() + " gets 100 VC");
        System.out.println("Id: " + id);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Magic number: " + magicNumber);
        System.out.println("Hash of the previous block: \n" + previousBlock);
        System.out.println("Hash of the block: \n" + thisBlock);
        if (messages.isEmpty()) {
            System.out.println("Block data: no messages");
        } else {
            System.out.println("Block data:");
            messages.forEach(System.out::println);
        }
        System.out.println("Block was generating for " + secondsToGenerate + " seconds");
        validateMiningTime();
        System.out.println();
    }

    private void validateMiningTime() {
        if (secondsToGenerate > 60) {
            System.out.println("N was decreased by 1");
        } else if (secondsToGenerate < 10) {
            System.out.println("N was increased to " + (zeros + 1));
        } else {
            System.out.println("N stays the same");
        }
    }

    @Override
    public int compareTo(Block o) {
        return id.compareTo(o.getId());
    }

}
