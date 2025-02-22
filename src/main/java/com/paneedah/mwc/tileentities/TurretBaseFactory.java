package com.paneedah.mwc.tileentities;

import com.paneedah.mwc.MWC;
import com.paneedah.weaponlib.ModContext;
import com.paneedah.weaponlib.tile.LootBoxConfiguration;
import net.minecraft.block.material.Material;
import org.lwjgl.opengl.GL11;

public class TurretBaseFactory {

    public static void createTileEntity(ModContext modContext) {
        new LootBoxConfiguration()
                .withMaterial(Material.ROCK)
                .withName("turret_base")
                .withModelClassName("com.paneedah.mwc.models.TurretBase")
                .withTextureName("textures/models/turretbase.png")
                .withCreativeTab(MWC.PROPS_TAB)
                .withBoundingBox(0.0, 0, 0.0, 1, 0.2, 1)
                .withPositioning(tileEntity -> {
//            GL11.glScalef(0.5f, 0.5f, 0.5f);
                    GL11.glTranslatef(0.5f, 0f, 0.5f);
//            GL11.glRotatef(-45F, 0f, 1f, 0f);
                })
                .build(modContext);
    }
}
