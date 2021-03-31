package com.floweytf.forgebukkit.inventory.crafting;

import net.minecraft.server.MinecraftServer;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;

public class CraftSmithingRecipe extends SmithingRecipe implements CraftRecipe {
    public CraftSmithingRecipe(NamespacedKey key, ItemStack result, RecipeChoice base, RecipeChoice addition) {
        super(key, result, base, addition);
    }

    public static CraftSmithingRecipe fromBukkitRecipe(SmithingRecipe recipe) {
        if (recipe instanceof CraftSmithingRecipe) {
            return (CraftSmithingRecipe) recipe;
        }
        CraftSmithingRecipe ret = new CraftSmithingRecipe(recipe.getKey(), recipe.getResult(), recipe.getBase(), recipe.getAddition());
        return ret;
    }

    @Override
    public void addToCraftingManager() {
        ItemStack result = this.getResult();

        MinecraftServer.getServer().getCraftingManager().addRecipe(new net.minecraft.server.RecipeSmithing(CraftNamespacedKey.toMinecraft(this.getKey()), toMinecraft(this.getBase(), true), toMinecraft(this.getAddition(), true), CraftItemStack.asNMSCopy(result)));
    }
}
