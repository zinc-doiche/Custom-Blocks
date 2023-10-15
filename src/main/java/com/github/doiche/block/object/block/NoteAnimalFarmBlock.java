package com.github.doiche.block.object.block;

import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;

public class NoteAnimalFarmBlock implements AnimalFarmBlock {
    private final Instrument instrument;
    private final int note;
    private final boolean powered;

    public NoteAnimalFarmBlock(Instrument instrument, int note, boolean powered) {
        this.instrument = instrument;
        this.note = note;
        this.powered = powered;
    }

    @Override
    public BlockData getBlockData() {
        NoteBlock noteBlock = (NoteBlock) Material.NOTE_BLOCK.createBlockData();
        noteBlock.setInstrument(instrument);
        noteBlock.setNote(new Note(note));
        noteBlock.setPowered(powered);
        return noteBlock;
    }
}
