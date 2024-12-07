package com.paneedah.mwc.network.handlers;

import com.paneedah.mwc.network.messages.RealisticSoundClientMessage;
import com.paneedah.mwc.network.messages.RealisticSoundMessage;
import com.paneedah.weaponlib.sound.RealisticSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;

import static com.paneedah.mwc.MWC.CHANNEL;

public class RealisticSoundHandler implements IMessageHandler<RealisticSoundMessage, IMessage> {

    @Override
    public IMessage onMessage(RealisticSoundMessage message, MessageContext ctx) {
        ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
            EntityPlayer player = ctx.getServerHandler().player;
            World world = player.getEntityWorld();
            for (EntityPlayer playerInWorld : world.playerEntities) {
                double distance = Math.sqrt(message.getPos().distanceSq(playerInWorld.getPosition()));
                RealisticSound sound = RealisticSound.createSound(message.getSilencer(), message.getSound(), message.getPos(), playerInWorld.getPosition());
                if (sound.getVolumeIn() > 0.0F) {
                    if (playerInWorld instanceof EntityPlayerMP) {
                    EntityPlayerMP playerMP = (EntityPlayerMP) playerInWorld;
                    playerMP.getServerWorld().addScheduledTask(() -> {
                        CHANNEL.sendTo(
                                new RealisticSoundClientMessage(sound.getSound(), message.getPos(), sound.getVolumeIn(), sound.getPitchIn(), distance),
                                playerMP
                        );
                    });
                    }
                }
            }
        });
        return null;
    }
}