// Date: 10/10/2017 6:57:31 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package com.paneedah.mwc.models;

import com.paneedah.weaponlib.ModelWithAttachments;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Saiga12action extends ModelWithAttachments {
    //fields

    ModelRenderer action1;
    ModelRenderer action2;
    ModelRenderer action3;
    ModelRenderer action4;
    ModelRenderer action5;
    ModelRenderer action6;
    ModelRenderer action7;
    ModelRenderer action8;
    ModelRenderer action9;

    public Saiga12action() {
        textureWidth = 512;
        textureHeight = 256;

        action1 = new ModelRenderer(this, 100, 0);
        action1.addBox(0F, 0F, 0F, 1, 2, 13);
        action1.setRotationPoint(-2.6F, -15.2F, -33.5F);
        action1.setTextureSize(64, 32);
        action1.mirror = true;
        setRotation(action1, 0F, 0F, 0.3346075F);
        action2 = new ModelRenderer(this, 100, 0);
        action2.addBox(0F, 0F, 0F, 1, 2, 23);
        action2.setRotationPoint(-3.5F, -13F, -33.5F);
        action2.setTextureSize(64, 32);
        action2.mirror = true;
        setRotation(action2, 0F, 0F, 0F);
        action3 = new ModelRenderer(this, 100, 0);
        action3.addBox(0F, 0F, 0F, 2, 1, 15);
        action3.setRotationPoint(-3.5F, -13F, -33.5F);
        action3.setTextureSize(64, 32);
        action3.mirror = true;
        setRotation(action3, 0F, 0F, -1.07818F);
        action4 = new ModelRenderer(this, 100, 0);
        action4.addBox(0F, 0F, 0F, 1, 1, 7);
        action4.setRotationPoint(-3.6F, -13F, -26.5F);
        action4.setTextureSize(64, 32);
        action4.mirror = true;
        setRotation(action4, 0F, 0F, -1.07818F);
        action5 = new ModelRenderer(this, 100, 0);
        action5.addBox(0F, 0F, 0F, 1, 2, 6);
        action5.setRotationPoint(-2.7F, -15.2F, -26.5F);
        action5.setTextureSize(64, 32);
        action5.mirror = true;
        setRotation(action5, 0F, 0F, 0.3346075F);
        action6 = new ModelRenderer(this, 100, 0);
        action6.addBox(0F, 0F, 0F, 1, 1, 4);
        action6.setRotationPoint(-3.7F, -13F, -33.5F);
        action6.setTextureSize(64, 32);
        action6.mirror = true;
        setRotation(action6, 0F, 0F, 0F);
        action7 = new ModelRenderer(this, 100, 0);
        action7.addBox(0F, 0F, 0F, 1, 1, 1);
        action7.setRotationPoint(-3.7F, -13F, -29.5F);
        action7.setTextureSize(64, 32);
        action7.mirror = true;
        setRotation(action7, 0F, 0.2648976F, 0F);
        action8 = new ModelRenderer(this, 100, 0);
        action8.addBox(0F, 0F, 0F, 3, 1, 1);
        action8.setRotationPoint(-6F, -13F, -33F);
        action8.setTextureSize(64, 32);
        action8.mirror = true;
        setRotation(action8, 0F, 0F, 0F);
        action9 = new ModelRenderer(this, 100, 0);
        action9.addBox(0F, 0F, 0F, 2, 1, 1);
        action9.setRotationPoint(-4.7F, -13F, -33F);
        action9.setTextureSize(64, 32);
        action9.mirror = true;
        setRotation(action9, 0F, 0.3346075F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        action1.render(f5);
        action2.render(f5);
        action3.render(f5);
        action4.render(f5);
        action5.render(f5);
        action6.render(f5);
        action7.render(f5);
        action8.render(f5);
        action9.render(f5);
    }

}
