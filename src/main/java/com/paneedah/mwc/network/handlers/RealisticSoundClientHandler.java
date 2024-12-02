package com.paneedah.mwc.network.handlers;

import com.paneedah.mwc.network.messages.RealisticSoundClientMessage;
import com.paneedah.weaponlib.sound.RealisticSound;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RealisticSoundClientHandler implements IMessageHandler<RealisticSoundClientMessage, IMessage> {

    @Override
    public IMessage onMessage(RealisticSoundClientMessage message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            System.out.println("  ");
            System.out.println("  ");
            System.out.println("  Minecraft sound client");
            System.out.println("  ");
            Minecraft minecraft = Minecraft.getMinecraft();
            if (minecraft.world != null) {
                System.out.println("  ");
                System.out.println("  ");
                System.out.println("  Minecraft sound client check avec coord: "+message.getPos());
                System.out.println("  ");
                minecraft.world.playSound(
                        message.getPos().getX(),
                        message.getPos().getY(),
                        message.getPos().getZ(),
                        message.getSound(),
                        SoundCategory.PLAYERS,
                        message.getVolume(),
                        message.getPitch(),
                        false
                );
            }
        });
        return null;
    }
}