package com.github.doiche.block.command;

import com.github.doiche.block.object.AnimalFarmBlocks;
import com.github.doiche.lib.AnimalFarmCommand;
import com.github.doiche.lib.TabBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

import static net.kyori.adventure.text.Component.text;

public class AnimalFarmBlockCommand {
    public static void register() {
        AnimalFarmCommand.builder()
                .name("customblock")
                .alias("커스텀블록", "쳔새ㅡㅠㅣㅐ차")
                .executor((sender, command, label, args) -> {
                    if(!(sender instanceof Player player)) {
                        return false;
                    }
                    switch (args.length) {
                        case 1 -> {
                            if(args[0].equals("list")) {
                                Component message = text("커스텀 블록 목록: ");
                                for (String name : AnimalFarmBlocks.getBlockNames()) {
                                    message = message.appendNewline()
                                            .append(text("  - " + name));
                                }
                                player.sendMessage(message);
                                return true;
                            }
                        }
                        case 2 -> {
                            if(args[0].equals("get")) {
                                return true;
                            } else if (args[0].equals("set")) {
                                if(!AnimalFarmBlocks.has(args[1])) {
                                    player.sendMessage(text("존재하지 않는 블록입니다."));
                                    return true;
                                }
                                BlockData blockData = AnimalFarmBlocks.get(args[1]).getBlockData();
                                RayTraceResult result = player.rayTraceBlocks(10);
                                if(result == null) {
                                    player.sendMessage(text("블록을 바라보고 있지 않습니다."));
                                    return true;
                                }
                                Block hitBlock = result.getHitBlock();
                                if(hitBlock == null) {
                                    player.sendMessage(text("블록을 바라보고 있지 않습니다."));
                                    return true;
                                }
                                hitBlock.setBlockData(blockData);
                                return true;
                            }
                        }
                    }
                    player.sendMessage(text("/customblock list", NamedTextColor.RED, TextDecoration.BOLD)
                            .appendNewline()
                            .append(text("/customblock <get|set> [block name]", NamedTextColor.RED, TextDecoration.BOLD)));
                    return false;
                })
                .completer((sender, command, label, args) -> {
                    TabBuilder tabBuilder = TabBuilder.of(args);
                    switch (args.length) {
                        case 1 -> {
                            tabBuilder.set(0, "list", "get", "set");
                        }
                        case 2 -> {
                            if(args[0].equals("get") || args[0].equals("set")) {
                                tabBuilder.set(1, AnimalFarmBlocks.getBlockNames());
                            }
                        }
                        default -> tabBuilder.setDefault();
                    }
                    return tabBuilder.build();
                })
                .build().register();
    }
}
