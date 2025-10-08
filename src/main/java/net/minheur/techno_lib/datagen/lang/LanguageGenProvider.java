package net.minheur.techno_lib.datagen.lang;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minheur.techno_lib.datagen.advancement.AdvancementBuilder;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class LanguageGenProvider implements DataProvider {
    /**
     * This is the saver for data. The first {@link Map} if {@link String}, {@link Map}.
     * The {@link String} is the lang name (ex. {@code fr_fr}, {@code en_us}...) and the
     * {@link Map} is filled with key with translation.
     */
    private final Map<String, Map<String, String>> data = new HashMap<>();
    /**
     * This is the output to write the finished files.
     */
    private final PackOutput output;
    /**
     * The ID of the mod which extends this class. It's used when registering advancements.
     */
    private final String modid;

    protected LanguageGenProvider(PackOutput output, String modid) {
        this.output = output;
        this.modid = modid;
    }

    /**
     * This method is the one who get Override by the class extending this.
     */
    protected abstract void addTranslation();

    /**
     * Executes when the datagen is running
     * @param pOutput the output for saving
     * @return the list of {@link CompletableFuture}
     */
    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (SupportedLanguages lang : SupportedLanguages.values()) {
            data.put(lang.name(), new HashMap<>());
        }

        addTranslation();

        for (Map.Entry<String, Map<String, String>> langEntry : data.entrySet()) {
            String langId = langEntry.getKey();
            Map<String, String> translations = langEntry.getValue();

            if (!translations.isEmpty()) {
                futures.add(save(pOutput, translations, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.modid).resolve("lang").resolve(langId + ".json")));
            }
        }

        if (!futures.isEmpty()) return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        return CompletableFuture.allOf();
    }

    /**
     * Saving one lang file from its {@link Map}
     * @param cache the buimder
     * @param data the {@link Map} with data
     * @param target the path to set the file in
     * @return the single {@link CompletableFuture} of this lang
     */
    private @NotNull CompletableFuture<?> save(CachedOutput cache, @NotNull Map<String, String> data, Path target) {
        JsonObject json = new JsonObject();
        data.forEach(json::addProperty);

        return DataProvider.saveStable(cache, json, target);
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return modid + ": generating";
    }

    /**
     * Main function to add a traduction
     * @param key the long id of the translation
     * @return a built translation
     */
    public TranslationBuilder add(String key) {
        return new TranslationBuilder(key);
    }
    /**
     * Create a {@link Block} translation builder from the extended {@link Block} you gave.
     * @param key the extended {@link Block}
     * @return a builder for {@link Block}
     */
    public TranslationBuilder addBlock(@NotNull Supplier<? extends Block> key) {
        return add(key.get());
    }
    /**
     * Create a {@link Block} translation builder from the {@link Block} you gave
     * @param key the {@link Block}
     * @return a builder for {@link Block}
     */
    public TranslationBuilder add(@NotNull Block key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create an {@link Item} translation builder from the extended {@link Item} you gave.
     * @param key the extended {@link Item}
     * @return a builder for {@link Item}
     */
    public TranslationBuilder addItem(@NotNull Supplier<? extends Item> key) {
        return add(key.get());
    }
    /**
     * Create an {@link Item} translation builder from the {@link Item} you gave
     * @param key the {@link Item}
     * @return a builder for {@link Item}
     */
    public TranslationBuilder add(@NotNull Item key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create a disc description translation builder from the extended {@link Item} you gave.
     * @param key the extended {@link Item}
     * @return a builder for disc desc
     */
    public TranslationBuilder addRecordDesc(@NotNull Supplier<? extends Item> key) {
        return addDesc(key.get());
    }
    /**
     * Create a disc description translation builder from the {@link Item} you gave
     * @param key the {@link Item}
     * @return a builder for disc description
     */
    public TranslationBuilder addDesc(@NotNull Item key) {
        return add(key.getDescriptionId() + ".desc");
    }
    /**
     * Create an {@link ItemStack} translation builder from the extended {@link ItemStack} you gave.
     * @param key the extended {@link ItemStack}
     * @return a builder for {@link ItemStack}
     */
    public TranslationBuilder addItemStack(@NotNull Supplier<ItemStack> key) {
        return add(key.get());
    }
    /**
     * Create an {@link ItemStack} translation builder from the {@link ItemStack} you gave
     * @param key the {@link ItemStack}
     * @return a builder for {@link ItemStack}
     */
    public TranslationBuilder add(@NotNull ItemStack key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create an {@link Enchantment} translation builder from the extended {@link Enchantment} you gave.
     * @param key the extended {@link Enchantment}
     * @return a builder for {@link Enchantment}
     */
    public TranslationBuilder addEnchantment(@NotNull Supplier<? extends Enchantment> key) {
        return add(key.get());
    }
    /**
     * Create an {@link Enchantment} translation builder from the {@link Enchantment} you gave
     * @param key the {@link Enchantment}
     * @return a builder for {@link Enchantment}
     */
    public TranslationBuilder add(@NotNull Enchantment key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create a {@link MobEffect} translation builder from the extended {@link MobEffect} you gave.
     * @param key the extended {@link MobEffect}
     * @return a builder for {@link MobEffect}
     */
    public TranslationBuilder addEffect(@NotNull Supplier<? extends MobEffect> key) {
        return add(key.get());
    }
    /**
     * Create a {@link MobEffect} translation builder from the {@link MobEffect} you gave
     * @param key the {@link MobEffect}
     * @return a builder for {@link MobEffect}
     */
    public TranslationBuilder add(@NotNull MobEffect key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create an {@link EntityType} translation builder from the extended {@link EntityType} you gave.
     * @param key the extended {@link EntityType}
     * @return a builder for {@link EntityType}
     */
    public TranslationBuilder addEntityType(@NotNull Supplier<? extends EntityType<?>> key) {
        return add(key.get());
    }
    /**
     * Create an {@link EntityType} translation builder from the {@link EntityType} you gave
     * @param key the {@link EntityType}
     * @return a builder for {@link EntityType}
     */
    public TranslationBuilder add(@NotNull EntityType<?> key) {
        return add(key.getDescriptionId());
    }
    /**
     * Create a {@link CreativeModeTab} translation builder from the {@link CreativeModeTab} you gave.
     * @param tab the {@link CreativeModeTab}
     * @return a builder for {@link CreativeModeTab}
     */
    public TranslationBuilder addCreativeTab(@NotNull CreativeModeTab tab) {
        return add("creativetab." + modid + "." + tab.getDisplayName());
    }
    /**
     * Create an advancement title translation builder from the group and key of an advancement.
     * @param group the group of the advancement
     * @param key the key of the advancement
     * @return a builder for an advancement title
     */
    public TranslationBuilder addAdvancementTitle(String group, String key) {
        return add("advancements." + modid + "." + group + "." + key + ".title");
    }
    /**
     * Create an advancement title translation builder from the {@link AdvancementBuilder} you set.
     * Used to get easier translation when using this system of advancements.
     * @param adv the {@link AdvancementBuilder} you want.
     * @return a builder for advancement
     */
    public TranslationBuilder addAdvancementTitle(@NotNull AdvancementBuilder adv) {
        return addAdvancementTitle(adv.getGroup(), adv.getId());
    }
    /**
     * Create an {@link Advancement} description translation builder from the group and key of an advancement.
     * @param group the group of the advancement
     * @param key the key of the advancement
     * @return a builder for an {@link Advancement} description
     */
    public TranslationBuilder addAdvancementDesc(String group, String key) {
        return add("advancements." + modid + "." + group + "." + key + ".description");
    }
    /**
     * Create an advancement description translation builder from the {@link AdvancementBuilder} you set.
     * Used to get easier translation when using this system of advancements.
     * @param adv the {@link AdvancementBuilder} you want.
     * @return a builder for advancement
     */
    public TranslationBuilder addAdvancementDesc(@NotNull AdvancementBuilder adv) {
        return addAdvancementDesc(adv.getGroup(), adv.getId());
    }
    /**
     * Create a translation for tooltip.
     * @param tooltipId the ID of your tooltip
     * @return a builder for tooltip
     */
    public TranslationBuilder addTooltip(String tooltipId) {
        return add("tooltip." + modid + "." + tooltipId + ".tooltip");
    }
    /**
     * Create a translation for credit tooltip.
     * @param creditId the ID of your credit
     * @return a builder for tooltip
     */
    public TranslationBuilder addTooltipCredit(String creditId) {
        return add("tooltip." + modid + "." + creditId + ".credit");
    }
    /**
     * Create a translation for a sound
     * @param soundName the name of your sound
     * @return a builder for sound
     */
    public TranslationBuilder addSound(String soundName) {
        return add("sounds." + modid + "." + soundName);
    }
    /**
     * Create a translation for the name of your recipe
     * @param recipeName the name of your recipe
     * @return a builder for recipe
     */
    public TranslationBuilder addRecipeName(String recipeName) {
        return add("recipe." + modid + "." + recipeName);
    }
    /**
     * Create a translation for a property of a recipe
     * @param recipeName the name of the recipe
     * @param property the property you want to define
     * @return a builder for a recipe property
     */
    public TranslationBuilder addRecipeProperty(String recipeName, String property) {
        return add("recipe." + modid + "." + recipeName + "." + property);
    }
    /**
     * Create a translation for a gui name
     * @param guiId the id of your GUI
     * @return a builder for a gui name
     */
    public TranslationBuilder addGuiName(String guiId) {
        return add("gui." + modid + "." + guiId + ".name");
    }
    /**
     * Create a translation for the name of your villager
     * @param villagerId the id of  your villager
     * @return a builder for a villager name
     */
    public TranslationBuilder addVillagerType(String villagerId) {
        return add("entity.minecraft.villager." + modid + "." + villagerId);
    }
    /**
     * Create a translation for a death message
     * @param damage the death type id
     * @return a builder for a death message
     */
    public TranslationBuilder addAttackDeath(String damage) {
        return add("death.attack." + damage);
    }
    /**
     * Create a translation for a death by player with item message
     * @param damage the death type id
     * @return a builder for a death message
     */
    public TranslationBuilder addAttackDeathItem(String damage) {
        return add("death.attack." + damage + ".item");
    }
    /**
     * Create a translation for a death by player message
     * @param damage the death type id
     * @return a builder for a death message
     */
    public TranslationBuilder addAttackDeathPlayer(String damage) {
        return add("death.attack." + damage + ".player");
    }

    /**
     * The class that handles adding languages.
     * <p>Remember to add the method with the correct name when adding a new language to supported ones.</p>
     */
    public class TranslationBuilder {
        /**
         * The id of the language the instance is handling.
         */
        private final String key;

        private TranslationBuilder(String key) {
            this.key = key;
        }

        /**
         * Adding a translation for a specific language a value.
         * @param langId the id of the lang you are adding a traduction in
         * @param value the value of this translation
         * @return the actual translation pack you are creating
         */
        public TranslationBuilder lang(String langId, String value) {
            Map<String, String> translations = data.get(langId);
            if (translations.put(key, value) != null) {
                throw new IllegalStateException("Duplicate translation key " + key + " for lang " + langId);
            }
            return this;
        }

        /**
         * Adding a translation for the en_us lang file.
         * @param value the value of the translation
         * @return the actual translation pack you are creating
         */
        public TranslationBuilder en_us(String value) {
            return lang("en_us", value);
        }
        /**
         * Adding a translation for the fr_fr lang file.
         * @param value the value of the translation
         * @return the actual translation pack you are creating
         */
        public TranslationBuilder fr_fr(String value) {
            return lang("fr_fr", value);
        }
    }
}
