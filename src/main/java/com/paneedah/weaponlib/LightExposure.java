package com.paneedah.weaponlib;

import com.paneedah.mwc.network.UniversalObject;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@NoArgsConstructor
public class LightExposure extends UniversalObject implements Exposure {

    private long lastExposureTimestamp;
    private long maxDuration;
    @Getter @Setter private float totalDose = 1f;
    private float decayFactor = 0.995f;


    public LightExposure(long lastExposureTimestamp, long maxDuration, float dose, float decayFactor) {
        this.lastExposureTimestamp = lastExposureTimestamp;
        this.maxDuration = maxDuration;
        this.totalDose = dose;
        this.decayFactor = decayFactor;
    }

    @Override
    public boolean isEffective(World world) {
//        System.out.println("Total dose: " + totalDose);
        return totalDose > 0.0003f && world.getTotalWorldTime() - lastExposureTimestamp <= maxDuration;
    }


    @Override
    public void update(Entity entity) {
        totalDose *= decayFactor;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        super.write(byteBuf);
        byteBuf.writeLong(lastExposureTimestamp);
        byteBuf.writeFloat(totalDose);
    }

    @Override
    public void read(ByteBuf byteBuf) {
        super.read(byteBuf);
        lastExposureTimestamp = byteBuf.readLong();
        totalDose = byteBuf.readFloat();
    }


    @Override
    public void updateFrom(Exposure otherExposure) {
        if (otherExposure instanceof LightExposure) {
            LightExposure other = (LightExposure) otherExposure;
            this.lastExposureTimestamp = other.lastExposureTimestamp;
            this.totalDose = other.totalDose;
        }
    }

    @Override
    public long getLastSyncTimestamp() {
        return 0;
    }


}
