package blockchain;

public class MinerFactory {

    public static Miner getMiner(long id) {
        return new Miner(id);
    }

}
