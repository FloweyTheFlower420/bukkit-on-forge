package com.floweytf.forgebukkit.inventory.crafting;

import com.floweytf.forgebukkit.util.ForgeBukkitMagicNumbers;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;

import java.util.ArrayList;
import java.util.List;

public interface CraftRecipe extends Recipe {
    static void addRecipe(RecipeManager manager, IRecipe<?> recipe) {
        Object2ObjectLinkedOpenHashMap<ResourceLocation, IRecipe<?>> map =
            RecipeManager.recipes.get(recipe.getType());

        if (map.containsKey(recipe.getId()))
            throw new IllegalStateException("Duplicate recipe ignored with ID " + recipe.getKey());
        else
            map.putAndMoveToFirst(recipe.getId(), recipe);
    }

    void addToCraftingManager();

    default Ingredient toMinecraft(RecipeChoice bukkit, boolean requireNotEmpty) {
        Ingredient stack;

        if (bukkit == null) {
            stack = Ingredient.EMPTY;
        }
        else if (bukkit instanceof RecipeChoice.MaterialChoice) {
            stack = new Ingredient(((RecipeChoice.MaterialChoice) bukkit).getChoices().stream().map((mat) -> new net.minecraft.server.RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(new ItemStack(mat)))));
        }
        else if (bukkit instanceof RecipeChoice.ExactChoice) {
            stack = new Ingredient(((RecipeChoice.ExactChoice) bukkit).getChoices().stream().map((mat) -> new net.minecraft.server.RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(mat))));
            stack.exact = true;
        }
        else {
            throw new IllegalArgumentException("Unknown recipe stack instance " + bukkit);
        }

        stack.buildChoices();
        if (requireNotEmpty && stack.choices.length == 0) {
            throw new IllegalArgumentException("Recipe requires at least one non-air choice!");
        }

        return stack;
    }

    public static RecipeChoice toBukkit(Ingredient list) {
        list.buildChoices();

        if (list.choices.length == 0) {
            return null;
        }

        if (list.exact) {
            List<ItemStack> choices = new ArrayList<>(list.choices.length);
            for (net.minecraft.server.ItemStack i : list.choices) {
                choices.add(CraftItemStack.asBukkitCopy(i));
            }

            return new RecipeChoice.ExactChoice(choices);
        } else {

            List<org.bukkit.Material> choices = new ArrayList<>(list.choices.length);
            for (net.minecraft.server.ItemStack i : list.choices) {
                choices.add(ForgeBukkitMagicNumbers.getMaterial(i.getItem()));
            }

            return new RecipeChoice.MaterialChoice(choices);
        }
    }
}
