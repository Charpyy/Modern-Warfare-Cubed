package com.paneedah.mwc.network.messages;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RealisticSoundMessage implements IMessage {
    private SoundEvent sound;
    private BlockPos pos;
    private int dimensionId;

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        byte[] soundBytes = new byte[length];
        buf.readBytes(soundBytes);
        String soundName = new String(soundBytes);
        sound = SoundEvent.REGISTRY.getObject(new ResourceLocation(soundName));
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        pos = new BlockPos(x, y, z);
        dimensionId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        String soundName = sound.getRegistryName().toString();
        byte[] soundBytes = soundName.getBytes();
        buf.writeInt(soundBytes.length);
        buf.writeBytes(soundBytes);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeInt(dimensionId);
    }
    public SoundEvent getSound() {return this.sound;}
    public BlockPos getPos() { return this.pos;}
    public int getWorldId() {return this.dimensionId;}
}
