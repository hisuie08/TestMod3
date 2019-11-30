package choonster.testmod3.data;

import choonster.testmod3.TestMod3;
import choonster.testmod3.capability.hiddenblockrevealer.HiddenBlockRevealerCapability;
import choonster.testmod3.capability.lastusetime.LastUseTimeCapability;
import choonster.testmod3.init.ModItems;
import com.google.common.base.Preconditions;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.client.model.generators.ModelFile;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generates this mod's item models.
 *
 * @author Choonster
 */
public class TestMod3ItemModelProvider extends ItemModelProvider {
	private static final String LAYER_0 = "layer0";

	/**
	 * A model that extends item/generated and uses the same transforms as the Vanilla bow.
	 */
	private final LazyLoadBase<ModelFile> simpleModel = new LazyLoadBase<>(() ->
			withGeneratedParent("simple_model")
					.transforms()

					.transform(Perspective.THIRDPERSON_RIGHT)
					.rotation(-80, 260, -40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.THIRDPERSON_LEFT)
					.rotation(-80, -280, 40)
					.translation(-1, -2, 2.5f)
					.scale(0.9f, 0.9f, 0.9f)
					.end()

					.transform(Perspective.FIRSTPERSON_RIGHT)
					.rotation(0, -90, 25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.transform(Perspective.FIRSTPERSON_LEFT)
					.rotation(0, 90, -25)
					.translation(1.13f, 3.2f, 1.13f)
					.scale(0.68f, 0.68f, 0.68f)
					.end()

					.end()
	);

	public TestMod3ItemModelProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
		super(generator, TestMod3.MODID, existingFileHelper);
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return "TestMod3ItemModels";
	}

	@Override
	protected void registerModels() {
		withExistingParent(ModItems.WOODEN_AXE, Items.WOODEN_AXE);

		withExistingParent(ModItems.ENTITY_TEST, Items.PORKCHOP);

		withExistingParent(ModItems.RECORD_SOLARIS, Items.MUSIC_DISC_13);

		withExistingParent(ModItems.HEAVY, Items.BRICK);

		withExistingParent(ModItems.ENTITY_INTERACTION_TEST, Items.BEEF);

		withExistingParent(ModItems.BLOCK_DESTROYER, Items.TNT_MINECART);

		withSimpleParent(ModItems.SUBSCRIPTS);

		withSimpleParent(ModItems.SUPERSCRIPTS);


		// Create the parent model
		final ItemModelBuilder modelTest = withSimpleParent(ModItems.MODEL_TEST, itemTexture(ModItems.MODEL_TEST) + "_standby");

		// Create three child models and add them as overrides that display when the ticks since last use is >= index * 20
		IntStream.range(0, 3)
				.mapToObj(index -> {
					final ItemModelBuilder model = withSimpleParent(name(ModItems.MODEL_TEST) + "_" + index)
							.texture(LAYER_0, itemTexture(ModItems.MODEL_TEST) + "_" + index);

					return Pair.of(index, model);
				})
				.forEachOrdered(child -> {
					modelTest
							.override()
							.predicate(LastUseTimeCapability.TicksSinceLastUseGetter.ID, child.getKey() * 20)
							.model(child.getValue())
							.end();
				});

		// Add the parent as a fallback that displays when the ticks since last use is >= 60
		modelTest
				.override()
				.predicate(LastUseTimeCapability.TicksSinceLastUseGetter.ID, 60)
				.model(modelTest)
				.end();


		withExistingParent(ModItems.SNOWBALL_LAUNCHER, Items.FISHING_ROD);


		// Create the parent model
		final ItemModelBuilder slingshot = withSimpleParent(ModItems.SLINGSHOT);

		// Create the child model
		final ItemModelBuilder slingshotPulled = getBuilder(name(ModItems.SLINGSHOT) + "_pulled")
				.texture(LAYER_0, itemTexture(ModItems.SLINGSHOT) + "_pulled");

		// Add the child as an override that displays when the ticks since last use is >= 0 and < 20
		slingshot
				.override()
				.predicate(LastUseTimeCapability.TicksSinceLastUseGetter.ID, 0)
				.model(slingshotPulled)
				.end();

		// Add the parent as a fallback that displays when the ticks since last use is >= 20
		slingshot
				.override()
				.predicate(LastUseTimeCapability.TicksSinceLastUseGetter.ID, 20)
				.model(slingshot)
				.end();


		withExistingParent(ModItems.UNICODE_TOOLTIPS, Items.RABBIT);

		withExistingParent(ModItems.SWAP_TEST_A, Items.BRICK);

		withExistingParent(ModItems.SWAP_TEST_B, Items.NETHER_BRICK);

		withExistingParent(ModItems.BLOCK_DEBUGGER, Items.NETHER_STAR);

		withExistingParent(ModItems.WOODEN_HARVEST_SWORD, Items.WOODEN_SWORD);

		withExistingParent(ModItems.DIAMOND_HARVEST_SWORD, Items.DIAMOND_SWORD);

		withExistingParent(ModItems.CLEARER, Items.NETHER_STAR);

		bowItem(ModItems.BOW);

		withGeneratedParent(name(ModItems.ARROW));

		withExistingParent(ModItems.HEIGHT_TESTER, Items.COMPASS);

		withExistingParent(ModItems.PIG_SPAWNER_FINITE, Items.PORKCHOP);

		withExistingParent(ModItems.PIG_SPAWNER_INFINITE, Items.PORKCHOP);

		bowItem(ModItems.CONTINUOUS_BOW);

		withExistingParent(ModItems.RESPAWNER, Items.CLOCK);

		withExistingParent(ModItems.LOOT_TABLE_TEST, Items.GOLD_INGOT);

		withGeneratedParent(name(ModItems.MAX_HEALTH_GETTER_ITEM));

		withGeneratedParent(name(ModItems.MAX_HEALTH_SETTER_ITEM));

		withSimpleParent(ModItems.GUN);

		withSimpleParent(ModItems.DIMENSION_REPLACEMENT);

		withExistingParent(ModItems.SADDLE, Items.SADDLE);

		withExistingParent(ModItems.WOODEN_SLOW_SWORD, Items.WOODEN_SWORD);

		withExistingParent(ModItems.DIAMOND_SLOW_SWORD, Items.DIAMOND_SWORD);

		withExistingParent(name(ModItems.RITUAL_CHECKER), mcLoc("handheld"))
				.texture(LAYER_0, "item/banner_base");


		// Create the parent model
		final ItemModelBuilder hiddenBlockRevealer = withGeneratedParent(ModItems.HIDDEN_BLOCK_REVEALER);

		// Create the child model and add it as an override that's displayed when hidden blocks are being revealed
		final ItemModelBuilder hiddenBlockRevealerActive = getBuilder(name(ModItems.HIDDEN_BLOCK_REVEALER) + "_active")
				.parent(hiddenBlockRevealer)
				.texture(LAYER_0, itemTexture(ModItems.HIDDEN_BLOCK_REVEALER) + "_active");

		hiddenBlockRevealer
				.override()
				.predicate(HiddenBlockRevealerCapability.RevealHiddenBlocksGetter.ID, 1)
				.model(hiddenBlockRevealerActive)
				.end();

		withExistingParent(ModItems.NO_MOD_NAME, Items.BREAD);

		withExistingParent(name(ModItems.KEY), "handheld")
				.texture(LAYER_0, itemTexture(ModItems.KEY));

		withGeneratedParent(ModItems.BLOCK_DETECTION_ARROW);

		withGeneratedParent(name(ModItems.TRANSLUCENT_ITEM))
				.texture(LAYER_0, mcLoc("block/ice"));

		withGeneratedParent(ModItems.ENTITY_KILLER);

		withGeneratedParent(ModItems.CHUNK_ENERGY_SETTER);

		withGeneratedParent(ModItems.CHUNK_ENERGY_GETTER);

		withGeneratedParent(ModItems.CHUNK_ENERGY_DISPLAY);

		withExistingParent(name(ModItems.BEACON_ITEM), mcLoc("block/beacon"));

		withExistingParent(ModItems.SATURATION_HELMET, Items.CHAINMAIL_HELMET);

		withExistingParent(ModItems.ENTITY_CHECKER, Items.BONE);

		withGeneratedParent(ModItems.RUBBER);

		withExistingParent(ModItems.REPLACEMENT_HELMET, Items.CHAINMAIL_HELMET);

		withExistingParent(ModItems.REPLACEMENT_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE);

		withExistingParent(ModItems.REPLACEMENT_LEGGINGS, Items.CHAINMAIL_LEGGINGS);

		withExistingParent(ModItems.REPLACEMENT_BOOTS, Items.CHAINMAIL_BOOTS);

		ModItems.VariantGroups.VARIANTS_ITEMS
				.getItems()
				.forEach(this::withGeneratedParent);
	}

	private ResourceLocation registryName(final Item item) {
		return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
	}

	private String name(final Item item) {
		return registryName(item).getPath();
	}

	private ResourceLocation itemTexture(final Item item) {
		final ResourceLocation name = registryName(item);
		return new ResourceLocation(name.getNamespace(), ITEM_FOLDER + "/" + name.getPath());
	}

	private ItemModelBuilder withExistingParent(final Item item, final Item modelItem) {
		return withExistingParent(name(item), registryName(modelItem));
	}

	private ItemModelBuilder withGeneratedParent(final Item item) {
		return withGeneratedParent(name(item))
				.texture(LAYER_0, itemTexture(item));
	}

	private ItemModelBuilder withGeneratedParent(final String name) {
		return withExistingParent(name, mcLoc("generated"));
	}

	private ItemModelBuilder withSimpleParent(final Item item) {
		return withSimpleParent(item, itemTexture(item));
	}

	private ItemModelBuilder withSimpleParent(final Item item, final ResourceLocation texture) {
		return withSimpleParent(item, texture.toString());
	}

	private ItemModelBuilder withSimpleParent(final Item item, final String texture) {
		return withSimpleParent(name(item))
				.texture(LAYER_0, texture);
	}

	private ItemModelBuilder withSimpleParent(final String name) {
		return getBuilder(name)
				.parent(simpleModel.getValue());
	}

	private void bowItem(final Item item) {
		// Create the parent model
		final ItemModelBuilder bow = withSimpleParent(item, itemTexture(Items.BOW));

		// Create three child models
		final List<ItemModelBuilder> bowPullingModels = IntStream.range(0, 3)
				.mapToObj(index ->
						getBuilder(name(item) + "_" + index)
								.parent(bow)
								.texture(LAYER_0, itemTexture(Items.BOW) + "_pulling_" + index)
				)
				.collect(Collectors.toList());

		// Add the child models as overrides that display when the bow is pulled back >= 0%, >= 65% and >= 90% respectively
		bow
				.override()
				.predicate(mcLoc("pulling"), 1)
				.model(bowPullingModels.get(0))
				.end()

				.override()
				.predicate(mcLoc("pulling"), 1)
				.predicate(mcLoc("pull"), 0.65f)
				.model(bowPullingModels.get(1))
				.end()

				.override()
				.predicate(mcLoc("pulling"), 1)
				.predicate(mcLoc("pull"), 0.9f)
				.model(bowPullingModels.get(2))
				.end();
	}
}