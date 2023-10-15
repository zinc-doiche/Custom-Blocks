package com.github.doiche.block.listener;

import com.github.doiche.Main;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void onChange(EntityChangeBlockEvent event) {
        if(event.getBlockData() instanceof NoteBlock) {
            Main.getInstance().getSLF4JLogger().info("NoteBlock changed!");
            event.setCancelled(true);
        }
    }
}
