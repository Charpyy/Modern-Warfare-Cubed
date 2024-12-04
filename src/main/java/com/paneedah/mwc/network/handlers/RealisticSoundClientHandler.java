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
                System.out.println("  Minecraft sound client check avec coord: " + message.getPos());
                System.out.println("  ");
                double distance = message.getDistance();
                double speedOfSound = 343.0;
                long delayInTicks = (long) ((distance / speedOfSound) * 20);
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" DELAY IN TICKS ");
                System.out.println(" ");
                System.out.println(" ");
                minecraft.addScheduledTask(new Runnable() {
                    private long ticks = 0;

                    @Override
                    public void run() {
                        if (ticks >= delayInTicks) {
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
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println(" PLAY SOUND NOW");
                            System.out.println(" ");
                            System.out.println(" ");
                        } else {
                            ticks++;
                            Minecraft.getMinecraft().addScheduledTask(this);
                        }
                    }
                });
            }
        });
        return null;
    }
}