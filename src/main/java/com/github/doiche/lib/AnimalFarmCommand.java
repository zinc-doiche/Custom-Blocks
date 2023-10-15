package com.github.doiche.lib;

import com.github.doiche.Main;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnimalFarmCommand {
    private String name;
    private String[] alias;
    private CommandExecutor commandExecutor;
    private TabCompleter tabCompleter;

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void setTabCompleter(TabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    private AnimalFarmCommand(String name, String[] alias, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        this.name = name;
        this.alias = alias;
        this.commandExecutor = commandExecutor;
        this.tabCompleter = tabCompleter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void register() {
        PluginCommand command = Main.getInstance().getServer().getPluginCommand(name);
        if(command == null) {
            throw new NullPointerException(name + " command is null");
        }
        command.setExecutor(new TabExecutor() {
            @Override
            public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                return tabCompleter.onTabComplete(sender, command, label, args);
            }
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                return commandExecutor.onCommand(sender, command, label, args);
            }
        });
    }

    public static class Builder {
        private String name;
        private String[] alias;
        private CommandExecutor commandExecutor;
        private TabCompleter tabCompleter;

        //builder
        private Builder() {;}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder alias(String... alias) {
            this.alias = alias;
            return this;
        }

        public Builder executor(CommandExecutor commandExecutor) {
            this.commandExecutor = commandExecutor;
            return this;
        }

        public Builder completer(TabCompleter tabCompleter) {
            this.tabCompleter = tabCompleter;
            return this;
        }

        public AnimalFarmCommand build() {
            return new AnimalFarmCommand(name, alias, commandExecutor, tabCompleter);
        }
    }
}
