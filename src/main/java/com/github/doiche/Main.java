package com.github.doiche;

import com.github.doiche.block.command.AnimalFarmBlockCommand;
import com.github.doiche.block.listener.BlockListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        AnimalFarmBlockCommand.register();
        getServer().getPluginManager().registerEvents(new BlockListener(), Main.getInstance());
    }
}
