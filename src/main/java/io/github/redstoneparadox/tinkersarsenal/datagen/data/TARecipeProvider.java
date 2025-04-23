package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

import java.util.function.Consumer;

public class TARecipeProvider extends RecipeProvider implements IMaterialRecipeHelper {
    public TARecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        String folder = "tools/materials/";
        materialRecipe(pWriter, ArsenalToolMaterials.DIAMOND,Ingredient.of(Items.DIAMOND),1, 1, folder + "diamond");
    }

    @Override
    public String getModId() {
        return TinkersArsenal.MOD_ID;
    }
}
