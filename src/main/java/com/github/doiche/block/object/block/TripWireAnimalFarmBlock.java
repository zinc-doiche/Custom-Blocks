package com.github.doiche.block.object.block;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;

public class TripWireAnimalFarmBlock implements AnimalFarmBlock {
    private final boolean attached;
    private final boolean disarmed;
    private final boolean powered;
    private final boolean east;
    private final boolean north;
    private final boolean south;
    private final boolean west;

    public TripWireAnimalFarmBlock(boolean attached, boolean disarmed, boolean powered, boolean east, boolean north, boolean south, boolean west) {
        this.attached = attached;
        this.disarmed = disarmed;
        this.powered = powered;
        this.east = east;
        this.north = north;
        this.south = south;
        this.west = west;
    }

    @Override
    public BlockData getBlockData() {
        Tripwire tripwire = (Tripwire) Material.TRIPWIRE.createBlockData();
        tripwire.setAttached(attached);
        tripwire.setDisarmed(disarmed);
        tripwire.setPowered(powered);
        tripwire.setFace(BlockFace.EAST, east);
        tripwire.setFace(BlockFace.NORTH, north);
        tripwire.setFace(BlockFace.SOUTH, south);
        tripwire.setFace(BlockFace.WEST, west);
        return tripwire;
    }
}
