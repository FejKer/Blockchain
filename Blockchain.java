package blockchain;

import java.util.*;

public class Blockchain {
    private static final Blockchain blockchain = new Blockchain();
    private static Set<Block> blocks;
    private static String lastBlockValue;
    private static int zeros;
    private static List<String> messages;
    private static final Object lock = new Object();

    private Blockchain() {
        zeros = 0;
        lastBlockValue = "0";
        blocks = new TreeSet<>();
        messages = new ArrayList<>();
    }

    public static Blockchain getBlockchain() {
        return blockchain;
    }

    public static String getLastBlockValue() {
        return lastBlockValue;
    }

    public static Set<Block> getBlocks() {
        return blocks;
    }

    public static int getZeros() {
        return zeros;
    }

    public static void setZeros(int z) {
        zeros = z;
    }

    public synchronized static void insertNewBlock(Block block) {
        if (validateBlockchain(block)) {
            block.setMessages(new ArrayList<>(messages));
            lastBlockValue = block.getThisBlock();
            block.setId();
            blocks.add(block);
            if (block.getSecondsToGenerate() > 60) {
                //Blockchain.setZeros(Blockchain.getZeros() - 1);
            } else if (block.getSecondsToGenerate() < 10 && Blockchain.getZeros() < 3) {
                Blockchain.setZeros(Blockchain.getZeros() + 1);
            }
            messages.clear();
        }
    }

    public synchronized static void sendMessage(String message) {
        messages.add(message);
    }

    public static boolean validateBlockchain(Block block) {
        return block.getPreviousBlock().equals(lastBlockValue) && block.getThisBlock().matches("^[0]{" + zeros + "}([1-9]|[A-z])([A-z]|[0-9])*");
    }

}
