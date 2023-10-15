package com.github.doiche.block.object;


import com.github.doiche.Main;
import com.github.doiche.block.object.block.AnimalFarmBlock;
import com.github.doiche.block.object.block.NoteAnimalFarmBlock;
import com.github.doiche.block.object.block.TripWireAnimalFarmBlock;
import org.bukkit.Instrument;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalFarmBlocks {
    private static final String FILE_NAME = "customblocks.yml";
    private static final Map<String, AnimalFarmBlock> customBlocks = new HashMap<>();

    public static void register(String name, AnimalFarmBlock animalFarmBlock) {
        customBlocks.put(name, animalFarmBlock);
    }

    public static List<String> getBlockNames() {
        return List.copyOf(customBlocks.keySet());
    }

    public static AnimalFarmBlock get(String name) {
        return customBlocks.get(name);
    }

    public static boolean has(String name) {
        return customBlocks.containsKey(name);
    }

    public static void read() {
        File file = new File(Main.getInstance().getDataFolder(), FILE_NAME);
        if(!file.exists()) {
            Main.getInstance().saveResource(FILE_NAME, false);
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection tripWire = yamlConfiguration.getConfigurationSection("tripwire");
        ConfigurationSection noteBlock = yamlConfiguration.getConfigurationSection("note_block");

        if(tripWire != null) {
            tripWire.getKeys(false).forEach(name -> {
                ConfigurationSection section = tripWire.getConfigurationSection(name);
                if(section == null) {
                    return;
                }
                boolean attached = section.getBoolean("attached");
                boolean disarmed = section.getBoolean("disarmed");
                boolean powered = section.getBoolean("powered");
                boolean east = section.getBoolean("east");
                boolean north = section.getBoolean("north");
                boolean south = section.getBoolean("south");
                boolean west = section.getBoolean("west");
                AnimalFarmBlock animalFarmBlock = new TripWireAnimalFarmBlock(attached, disarmed, powered, east, north, south, west);
                AnimalFarmBlocks.register(name, animalFarmBlock);
            });
        }
        if(noteBlock != null) {
            noteBlock.getKeys(false).forEach(name -> {
                ConfigurationSection section = noteBlock.getConfigurationSection(name);
                if (section == null) {
                    return;
                }
                Instrument instrument = Instrument.valueOf(section.getString("instrument").toUpperCase());
                int note = section.getInt("note");
                boolean powered = section.getBoolean("powered");
                AnimalFarmBlock animalFarmBlock = new NoteAnimalFarmBlock(instrument, note, powered);
                AnimalFarmBlocks.register(name, animalFarmBlock);
            });
        }
    }
}
