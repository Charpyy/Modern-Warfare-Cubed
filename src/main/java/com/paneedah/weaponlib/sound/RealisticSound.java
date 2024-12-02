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
    private static final float BASE_VOLUME = 100.0F;
    private static final float BASE_PITCH = 1.0F;
    private static final float MAX_DISTANCE = 400.0F;

    public RealisticSound(SoundEvent sound, SoundCategory categoryIn, float volumeIn, float pitchIn) {
        this.sound = sound;
        this.categoryIn = categoryIn;
        this.volumeIn = volumeIn;
        this.pitchIn = pitchIn;
    }

    public static RealisticSound createSound(SoundEvent soundIn, BlockPos origin, BlockPos playercoord) {
        double distance = Math.sqrt(playercoord.distanceSq(origin));
        float volume = adjustVolumeForDistance(distance);
        float pitch = adjustPitchForDistance(distance);
        return new RealisticSound(soundIn, SoundCategory.PLAYERS, volume, pitch);
    }

    private static float adjustPitchForDistance(double distance) {
        return BASE_PITCH * (1.0f - (float) Math.min(distance / 100.0, 0.5));
    }

    private static float adjustVolumeForDistance(double distance) {
        return Math.max(MINIMUM_VOLUME, BASE_VOLUME * (1 - (float) distance / MAX_DISTANCE));
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
