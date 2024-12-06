package com.paneedah.weaponlib.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class RealisticSound {

    private final SoundEvent sound;
    private final float volumeIn;
    private final float pitchIn;

    private static final double MAX_DISTANCE = 400D;
    private static final double MAX_DISTANCE_SILENCER = 100D;

    public RealisticSound(SoundEvent sound, float volumeIn, float pitchIn) {
        this.sound = sound;
        this.volumeIn = volumeIn;
        this.pitchIn = pitchIn;
    }

    public static RealisticSound createSound(Boolean silencer, SoundEvent soundIn, BlockPos origin, BlockPos playercoord) {
        double distance = Math.sqrt(playercoord.distanceSq(origin));
        float volume = (float) adjustVolumeForDistance(silencer, distance);
        return new RealisticSound(soundIn, volume, 1.0F);
    }

    public static double adjustVolumeForDistance(boolean silencer, double distance) {
        double maxDistance = silencer ? MAX_DISTANCE_SILENCER : MAX_DISTANCE;
        if (distance >= maxDistance) {
            return 0.0;
        }
        if (distance <= 0) {
            return 1.0;
        }
        return 1.0 - (distance / maxDistance);
    }

    public SoundEvent getSound() {
        return sound;
    }

    public float getVolumeIn() {
        return volumeIn;
    }

    public float getPitchIn() {
        return pitchIn;
    }
}
