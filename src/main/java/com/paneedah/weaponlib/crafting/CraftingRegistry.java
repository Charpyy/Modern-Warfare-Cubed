package com.paneedah.weaponlib.crafting;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * CraftingRegistry that stores all of the craftable items, allows for an easy lookup of them,
 * and finally an easy registration process that first uses a hook system, and then later fills
 * in the recipes from a file.
 *
 * @author Homer Riva-Cambrin
 * @version October 9th, 2022
 */
public class CraftingRegistry {

    // Stores a map of all the items registered in a certain category
    public static final HashMap<CraftingGroup, ArrayList<ICraftingRecipe>> craftingMap = new HashMap<>(200, .7f);

    // Stores a map of a map of each group under their unlocalized names respectively
    private static final HashMap<CraftingGroup, HashMap<String, ICraftingRecipe>> categoricalLookup = new HashMap<>(50, 0.7f);

    protected static HashMap<String, CraftingEntry[]> recipeMap = new HashMap<>();

    private static final HashMap<Item, ICraftingRecipe> hookMap = new HashMap<>();

    static {
        // Fills the maps with the groups (obviously important
        // or we will get a null pointer exception)
        for (CraftingGroup group : CraftingGroup.values()) {
            craftingMap.put(group, new ArrayList<>());
            categoricalLookup.put(group, new HashMap<>());
        }
    }

    /**
     * Returns the ICraftingRecipe given the crafting group to search and the
     * unlocalized name of the item you are looking to craft.
     *
     * @param group - Crafting group of the crafting you are looking for
     * @param name - Unlocalized name of item you are looking to craft
     *
     * @return The modern crafting of that item
     */
    public static ICraftingRecipe getModernCrafting(CraftingGroup group, String name) {
        return categoricalLookup.get(group).get(name);
    }

    /**
     * Returns if there is an ICraftingRecipe for an item given
     * the crafting group to search and the unlocalized name of
     * the item you are looking to check.
     *
     * @param group - Crafting group of the crafting you are looking for
     * @param name - Unlocalized name of item you are looking to craft
     *
     * @return The modern crafting of that item
     */
    public static boolean hasModernCrafting(CraftingGroup group, String name) {
        return categoricalLookup.get(group).containsKey(name);
    }

    /**
     * Returns the list of all the items registered to a category
     *
     * @param group - Crafting group of the crafting you are looking for
     *
     * @return The list of all the items registered to that category
     */
    public static ArrayList<ICraftingRecipe> getCraftingListForGroup(CraftingGroup group) {
        return craftingMap.get(group);
    }

    /**
     * Registers an implementor of {@link ICraftingRecipe}
     *
     * @param crafting - ICraftingRecipe to register
     */
    public static void registerHook(ICraftingRecipe crafting) {
        hookMap.put(crafting.getOutput().getItem(), crafting);
    }

    public static boolean hasHook(String registryName) {
        return hasHook(Item.getByNameOrId(registryName));
    }

    public static boolean hasHook(Item item) {
        return hookMap.containsKey(item);
    }

    public static ICraftingRecipe getHook(String registryName) {
        return getHook(Item.getByNameOrId(registryName));
    }

    public static ICraftingRecipe getHook(Item item) {
        return hookMap.get(item);
    }

    protected static boolean isRecipeRegistered(String registryName) {
        return isRecipeRegistered(Item.getByNameOrId(registryName));
    }

    protected static boolean isRecipeRegistered(Item item) {
        return isRecipeRegistered(getHook(item));
    }

    protected static boolean isRecipeRegistered(ICraftingRecipe crafting) {
        if (crafting.getCraftingGroup() == null) {
            return false;
        }

        return craftingMap.get(crafting.getCraftingGroup()).contains(crafting);
    }

    protected static void deleteRecipeRegistry(Item item) {
        deleteRecipeRegistry(getHook(item));
    }

    public static void deleteRecipeRegistry(ICraftingRecipe crafting) {
        hookMap.remove(crafting.getOutput().getItem(), crafting);
        craftingMap.get(crafting.getCraftingGroup()).remove(crafting);
        categoricalLookup.get(crafting.getCraftingGroup()).remove(crafting.getOutput().getTranslationKey());
    }

    public static void clearRecipeRegistry() {
        hookMap.clear();

        for (CraftingGroup group : craftingMap.keySet()) {
            craftingMap.get(group).clear();
            categoricalLookup.get(group).clear();
        }
    }

    public static void registerRecipe(Item item, CraftingGroup group, CraftingEntry[] entry) {
        ICraftingRecipe crafting = getHook(item);

        // Sets their crafting groups
        try {
            crafting.setCraftingRecipe(entry);
            crafting.setCraftingGroup(group);

        } catch (Exception ignored) {
            return;
        }

        // If it already exists, assume we are loading a new set of recipes.
        if (isRecipeRegistered(crafting)) {
            deleteRecipeRegistry(crafting);
        }

        // Registers them
        craftingMap.get(crafting.getCraftingGroup()).add(crafting);
        categoricalLookup.get(crafting.getCraftingGroup()).put(crafting.getOutput().getTranslationKey(), crafting);
    }

    public static void registerRecipe(ICraftingRecipe crafting) {
        registerHook(crafting);
        // Registers them
        craftingMap.get(crafting.getCraftingGroup()).add(crafting);
        categoricalLookup.get(crafting.getCraftingGroup()).put(crafting.getOutput().getTranslationKey(), crafting);
    }

    public static void clearRegistry() {
        for (CraftingGroup g : CraftingGroup.values()) { // Clear out the registry
            clearGroup(g);
        }
    }

    public static void clearGroup(CraftingGroup group) {
        craftingMap.get(group).clear();
        categoricalLookup.get(group).clear();
    }
}
