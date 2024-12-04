package com.paneedah.weaponlib.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class RealisticSound {

    private final SoundEvent sound;
    private final SoundCategory categoryIn;
    private final float volumeIn;
    private final float pitchIn;

    private static final float MINIMUM_VOLUME = 0.0F;
    private static final float BASE_VOLUME = 1.0F;
    private static final float BASE_PITCH = 1.0F;
    private static final double MAX_DISTANCE = 3400.0F;

    public RealisticSound(SoundEvent sound, SoundCategory categoryIn, float volumeIn, float pitchIn) {
        this.sound = sound;
        this.categoryIn = categoryIn;
        this.volumeIn = volumeIn;
        this.pitchIn = pitchIn;
    }

    public static RealisticSound createSound(Boolean silencer, SoundEvent soundIn, BlockPos origin, BlockPos playercoord) {
        double distance = Math.sqrt(playercoord.distanceSq(origin));
        float volume = (float) adjustVolumeForDistance(distance, MAX_DISTANCE);
        if (silencer && volume > 0D) {
            volume *= 0.4F;
        }
        //float pitch = adjustPitchForDistance(distance);
        return new RealisticSound(soundIn, SoundCategory.PLAYERS, volume, BASE_PITCH);
    }


    //DOPPLER
    private static float adjustPitchForDistance(double distance) {
        return BASE_PITCH * (1.0f - (float) Math.min(distance / 100.0, 0.5));
    }


    public static double adjustVolumeForDistance(double distance, double maxDistance) {
        if (distance >= maxDistance) {
            return 0.0F;
        }
        if (distance <= 0) {
            return 1.0F;
        }
        return 1.0F - (distance / maxDistance);
    }

    public SoundEvent getSound() {
        return sound;
    }

    public SoundCategory getCategoryIn() {
        return categoryIn;
    }

    public float getVolumeIn() {
        return volumeIn;
    }

    public float getPitchIn() {
        return pitchIn;
    }
}
