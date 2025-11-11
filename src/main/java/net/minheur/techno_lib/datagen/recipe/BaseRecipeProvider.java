package net.minheur.techno_lib.datagen.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public abstract class BaseRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected final String MOD_ID;
    
    public BaseRecipeProvider(PackOutput pOutput, String modId) {
        super(pOutput);
        MOD_ID = modId;
    }

    /**
     * This creates a boat recipe.
     * @param isChestBoat if true, the recipe will have a chest in the middle.
     */
    protected  void simpleBoatCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike boatResult, ItemLike ingredient, boolean isChestBoat) {
        if(isChestBoat) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boatResult)
                    .pattern("PCP")
                    .pattern("PPP")
                    .define('P', ingredient)
                    .define('C', Blocks.CHEST)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(pFinishedRecipeConsumer);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boatResult)
                    .pattern("P P")
                    .pattern("PPP")
                    .define('P', ingredient)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(pFinishedRecipeConsumer);
        }
    }

    /**
     * This creates a recipe for 9 items to one. Template for block like iron.
     */
    protected  void simpleBlockCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(result) + "_from_nine_" + getItemName(ingredient));
    }

    /**
     * This is a template to craft a sign.
     */
    protected  void signCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike signResult, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, signResult, 3)
                .pattern("SSS")
                .pattern(" B ")
                .define('S', ingredient)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }
    /**
     * This recipe is a template for the hanging sign.
     */
    protected  void signHangingCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike hangingSignResult, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, hangingSignResult, 6)
                .pattern("C C")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ingredient)
                .define('C', Items.CHAIN)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * This is a template for a 4 to 1 recipe. Used for creating wood from logs
     */
    protected  void simpleWoodCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 3)
                .pattern("SS")
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * Template for stairs.
     */
    protected  void simpleStairsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * Helmet crafting
     */
    protected  void simpleHelmetCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * chestplate crafting
     */
    protected  void simpleChestplateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * leggings crafting
     */
    protected  void simpleLeggingsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * boots crafting
     */
    protected  void simpleBootsCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S S")
                .pattern("S S")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * Recipe template for a sword
     */
    protected  void simpleSwordCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("S")
                .pattern("S")
                .pattern("P")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a pickaxe
     */
    protected  void simplePickaxeCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SSS")
                .pattern(" P ")
                .pattern(" P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a shovel
     */
    protected  void simpleShovelCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("S")
                .pattern("P")
                .pattern("P")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for an axe
     */
    protected  void simpleAxesCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SS")
                .pattern("PS")
                .pattern("P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }
    /**
     * Recipe template for a hoe
     */
    protected  void simpleHoesCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Item result, ItemLike ingredient, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("SS")
                .pattern("P ")
                .pattern("P ")
                .define('S', ingredient)
                .define('P', stick)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The recipe template for the catalyzer
     * @param pFinishedRecipeConsumer the consumer
     * @param catalyzerResult the catalizer you want to craft
     * @param base the base of the catalizer
     * @param surround the item you add to the base
     */
    protected  void catalyzerCrafting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike catalyzerResult, ItemLike base, ItemLike surround) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, catalyzerResult)
                .pattern(" B ")
                .pattern("BIB")
                .pattern(" B ")
                .define('I', base)
                .define('B', surround)
                .unlockedBy(getHasName(base), has(base))
                .save(pFinishedRecipeConsumer);
    }

    /**
     * The slab template
     */
    protected  void simpleSlabCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 6)
                .pattern("SSS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The pressure plate template
     */
    protected  void simplePressurePlateCrafting(Consumer<FinishedRecipe> pFinisherRecpipeConsumer, Block result, Block ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("SS")
                .define('S', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinisherRecpipeConsumer);
    }

    /**
     * The shapeless of one item
     */
    protected  void simpleShapelessCraftingOne(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, ItemLike ingredient, Integer resultQuantity, Integer ingredientQuantity) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient, ingredientQuantity)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_one_" + getItemName(ingredient));
    }
    /**
     * The shapeless of two items
     */
    protected  void simpleShapelessCraftingTwo(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQ, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQ)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(result) + "_from_shapeless_of_two_base_" + getItemName(ingredient1));
    }
    /**
     * The shapeless of three items
     */
    protected  void simpleShapelessCraftingThree(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike result, Integer resultQuantity, ItemLike ingredient1, Integer ingredient1Q, ItemLike ingredient2, Integer ingredient2Q, ItemLike ingredient3, Integer ingredient3Q) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultQuantity)
                .requires(ingredient1, ingredient1Q)
                .requires(ingredient2, ingredient2Q)
                .requires(ingredient3, ingredient3Q)
                .unlockedBy(getHasName(ingredient1), has(ingredient1))
                .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(result)+"_from_shapeless_of_three_base_"+getItemName(ingredient1));
    }

    /**
     * The ore smelting recipe
     */
    protected void simpleOreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        simpleOreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    /**
     * The ore blasting
     */
    protected void simpleOreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        simpleOreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    /**
     * The item smoking
     */
    protected void itemSmoking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        simpleOreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMOKING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smoking");
    }
    /**
     * Ore cooking : it's the base of {@link #oreBlasting}, {@link #oreBlasting} and {@link #itemSmoking}.
     */
    protected void simpleOreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
