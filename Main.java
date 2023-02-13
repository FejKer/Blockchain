package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Miner m1 = new Miner(1L);
        Miner m2 = new Miner(2L);
        Miner m3 = new Miner(3L);
        Miner m4 = new Miner(4L);
        Miner m5 = new Miner(5L);
        Miner m6 = new Miner(6L);
        Miner m7 = new Miner(7L);
        Miner m8 = new Miner(8L);
        Miner m9 = new Miner(9L);
        Miner m10 = new Miner(10L);
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();
        m7.start();
        m8.start();
        m9.start();
        m10.start();
        m1.join();
        m2.join();
        m3.join();
        m4.join();
        m5.join();
        m6.join();
        m7.join();
        m8.join();
        m9.join();
        m10.join();

        for (Block b:
             Blockchain.getBlocks()) {
            b.printInfo();
        }

    }
}
