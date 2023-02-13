package blockchain;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Miner extends Thread {

    private Long secondsToGenerate;
    private final Long minerId;
    private boolean isMining;

    public Miner(Long minerId) {
        this.minerId = minerId;
    }

    @Override
    public void run() {
        isMining = true;
        mine();
    }

    private void mine() {
        while (isMining && Block.getLastId() <= 15) {
            findMagicNumber();
        }
    }

    public void setMining(boolean isMining) {
        this.isMining = isMining;
    }

    public Long getMinerId() {
        return minerId;
    }

    private void findMagicNumber() {
        Timer timer = new Timer();
        secondsToGenerate = 0L;
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        secondsToGenerate++;
                    }
                }
                ,
                0,
                1000);

        Random random = new Random();
        int zerosBefore = Blockchain.getZeros();
        long idBefore = Block.getLastId();
        long rand;
        String temp;
        long timestamp;
        Long lastId;
        String lastBlock;
        do {
            timestamp = new Date().getTime();
            lastId = Block.getLastId();
            lastBlock = Blockchain.getLastBlockValue();
            rand = random.nextInt();
            temp = StringUtil.applySha256(lastId.toString() + Long.toString(minerId) + Long.toString(timestamp) + lastBlock + rand);
        } while (!temp.matches("^[0]{"+Blockchain.getZeros()+"}([1-9]|[A-z])([A-z]|[0-9])*") && Block.getLastId() <= 15 && Block.getLastId() == idBefore && Blockchain.getZeros() == zerosBefore);
        Blockchain.insertNewBlock(new Block(timestamp, rand, secondsToGenerate, temp, this));
        if (new Random().nextInt() % 2 == 0) {
            Blockchain.sendMessage("Miner " + minerId + " sends 30 VC");
            Blockchain.sendMessage("Miner " + minerId + " sends 20 VC");
            Blockchain.sendMessage("Miner " + minerId + " spends 5 VC");
        } else {
            Blockchain.sendMessage("Miner " + minerId + " sends 20 VC to NICK");
        }
        timer.cancel();
    }

}
