package me.itswagpvp.economyplus.vault;

import me.itswagpvp.economyplus.EconomyPlus;
import me.itswagpvp.economyplus.database.cache.CacheManager;
import me.itswagpvp.economyplus.database.misc.Selector;
import org.bukkit.OfflinePlayer;

public class Economy extends VEconomy {

    private final OfflinePlayer player;
    private final double money;

    public Economy(OfflinePlayer player, double money) {
        super(EconomyPlus.plugin);
        this.player = player;
        this.money = money;
    }

    // Returns the money of a player
    public double getBalance() {
        return super.getBalance(new Selector().playerToString(player));
    }

    // Set the money of a player
    public void setBalance() {
        CacheManager.cachedPlayersMoneys.put(new Selector().playerToString(player), money);
        EconomyPlus.getDBType().setTokens(new Selector().playerToString(player), money);
    }

    // Add moneys to a player account
    public void addBalance() {
        super.depositPlayer(new Selector().playerToString(player), money);
    }

    // Remove moneys from a player's account
    public void takeBalance() {
        super.withdrawPlayer(new Selector().playerToString(player), money);
    }

    // Set player's bank to the constructor value
    public void setBank() {
        CacheManager.cachedPlayersBanks.put(new Selector().playerToString(player), money);
        EconomyPlus.getDBType().setBank(new Selector().playerToString(player), money);
    }

    // Returns the player bank
    public double getBank() {
        return CacheManager.cachedPlayersBanks.get(new Selector().playerToString(player));
    }

    // Controls if the player has enough moneys
    public boolean detractable() {
        return has(player, money);
    }
}