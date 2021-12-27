package me.itswagpvp.economyplus.database.yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static me.itswagpvp.economyplus.EconomyPlus.plugin;

public class YMLManager {

    public boolean contains(String playerName) {
        CompletableFuture<Boolean> getBoolean = CompletableFuture.supplyAsync(() -> plugin.getYMLData().contains("Data." + playerName));

        try {
            return getBoolean.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    public double getTokens (String name)  {
        return plugin.getYMLData().getDouble("Data." + name + ".tokens");
    }

    public void setTokens(String name, double value) {
        plugin.getYMLData().set("Data." + name + ".tokens", value);
        plugin.saveYMLConfig();
    }

    public double getBank(String name) {
        return plugin.getYMLData().getDouble("Data." + name + ".bank");
    }

    public void setBank(String name, double value) {
        plugin.getYMLData().set("Data." + name + ".bank", value);
        plugin.saveYMLConfig();
    }

    public boolean createPlayer(String player) {
        setTokens(player, plugin.getConfig().getDouble("Starting-Balance"));
        setBank(player, plugin.getConfig().getDouble("Starting-Bank-Balance"));
        return true;
    }

    public List<String> getList () {
        return new ArrayList<>(plugin.getYMLData().getConfigurationSection("Data").getKeys(false));
    }

}