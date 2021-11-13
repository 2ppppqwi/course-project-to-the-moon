package entities;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class to store and operate on currency (Dogecoin).
 * @author Andy Wang
 * @since 30 October 2021
 */
public class Bank {
    private int coins;
    private int dcps; //dogecoin per second

    public Bank() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                increaseCoins(dcps);
                System.out.println("Current coins: " + coins);
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * Increases the amount of coins by the given amount.
     * @param c The amount of coins to increase it by.
     */
    public void increaseCoins(int c) {
        this.coins += c;
    }

    /**
     * Increases the dogecoin per second by the given amount.
     * @param dcps The amount of dogecoin per second to increase it by.
     */
    public void increaseDCPS(int dcps) {
        this.dcps += dcps;
    }

    /**
     * Makes a transaction, and returns whether it was successful.
     * @return Whether the transaction was successful. True iff coins >= cost.
     */
    public boolean makePurchase(int cost) {
        if (this.coins >= cost) {
            this.increaseCoins(-cost);
            return true;
        } return false;
    }

    // getters
    public int getDCPS() {
        return this.dcps;
    }
}