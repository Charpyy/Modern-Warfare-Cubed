package com.paneedah.weaponlib.compatibility;

import com.paneedah.mwc.WorldGeneratorEventHandler;
import com.paneedah.mwc.vectors.Vector3D;
import com.paneedah.weaponlib.Explosion;
import com.paneedah.weaponlib.ModContext;
import com.paneedah.weaponlib.ai.EntityCustomMob;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Deprecated
public interface Compatibility {


    void runInMainClientThread(Runnable runnable);

	void registerModEntity(Class<? extends Entity> class1, String string, int i, Object mod, int j, int k, boolean b);

    <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, String... fieldNames);

	int getButton(MouseEvent event);

    void setNewFov(FOVUpdateEvent event, float fov);

    GuiScreen getGui(GuiOpenEvent event);

    RayTraceResult getObjectMouseOver();

	IBlockState getBlockAtPosition(World world, RayTraceResult position);

	void destroyBlock(World world, RayTraceResult position);

    boolean consumeInventoryItem(InventoryPlayer inventoryPlayer, Item item);

    void consumeInventoryItemFromSlot(EntityPlayer player, int nextAttachmentSlot);

	void addShapedRecipe(ItemStack itemStack, Object... materials);

    void addBlockHitEffect(BlockPos pos, double x, double y, double z, EnumFacing enumFacing);

    String getPlayerName(EntityPlayer player);

    void addChatMessage(Entity entity, String message);

    Entity getRenderViewEntity();

    void setRenderViewEntity(Entity entity);

    ItemStack consumeInventoryItem(Item item, Predicate<ItemStack> condition, EntityPlayer player, int maxSize);

    ItemStack tryConsumingCompatibleItem(List<? extends Item> compatibleParts, int maxSize, EntityPlayer player, Predicate<ItemStack> ...conditions);

    Item findItemByName(String itemName);

    AxisAlignedBB expandEntityBoundingBox(Entity entity, double f, double f2, double f3);

    List<Entity> getEntitiesWithinAABBExcludingEntity(World world, Entity entity, AxisAlignedBB boundingBox);

    void spawnParticle(World world, String particleName, double d, double e, double f, double motionX, double motionY, double motionZ);

    IBlockState getBlockAtPosition(World world, BlockPos blockPos);

    boolean isBlockPenetratableByBullets(Block block);

    boolean isBlockPenetratableByBullets(IBlockState blockState);

    boolean canCollideCheck(Block block, IBlockState metadata, boolean hitIfLiquid);

    float getCompatibleShellCasingForwardOffset();

    boolean madeFromHardMaterial(IBlockState iBlockState);

    void playSoundAtEntity(Entity entity, SoundEvent explosionSound, float volume, float pitch);

    EntityAITarget createAINearestAttackableTarget(EntityLivingBase e, Class<? extends EntityLivingBase> targetClass, boolean checkSight);

    ItemStack tryConsumingCompatibleItem(Collection<? extends Item> compatibleItems, Comparator<ItemStack> comparator, EntityPlayer player);

}
