package choonster.testmod3.init;

import choonster.testmod3.TestMod3;
import choonster.testmod3.block.*;
import choonster.testmod3.block.pipe.BlockPipeBasic;
import choonster.testmod3.block.pipe.BlockPipeFluid;
import choonster.testmod3.block.slab.BlockColouredSlab;
import choonster.testmod3.block.slab.BlockSlabTestMod3;
import choonster.testmod3.block.variantgroup.BlockVariantGroup;
import choonster.testmod3.block.variantgroup.IBlockVariantGroup;
import choonster.testmod3.block.variantgroup.SlabVariantGroup;
import choonster.testmod3.item.block.ItemFluidTank;
import choonster.testmod3.tileentity.*;
import choonster.testmod3.util.RegistryUtil;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static choonster.testmod3.util.InjectionUtil.Null;
import static choonster.testmod3.util.RegistryUtil.setBlockName;

@SuppressWarnings("WeakerAccess")
@ObjectHolder(TestMod3.MODID)
public class ModBlocks {

	public static final BlockWaterGrass WATER_GRASS = Null();

	public static final BlockLargeCollisionTest LARGE_COLLISION_TEST = Null();

	public static final BlockRightClickTest RIGHT_CLICK_TEST = Null();

	public static final BlockClientPlayerRightClick CLIENT_PLAYER_RIGHT_CLICK = Null();

	public static final BlockRotatableLamp ROTATABLE_LAMP = Null();

	public static final BlockItemCollisionTest ITEM_COLLISION_TEST = Null();

	public static final BlockFluidTank<TileEntityFluidTank> FLUID_TANK = Null();

	public static final BlockItemDebugger ITEM_DEBUGGER = Null();

	public static final Block END_PORTAL_FRAME_FULL = Null();

	public static final BlockPotionEffect POTION_EFFECT = Null();

	public static final BlockClientPlayerRotation CLIENT_PLAYER_ROTATION = Null();

	public static final BlockPigSpawnerRefiller PIG_SPAWNER_REFILLER = Null();

	public static final BlockPlane MIRROR_PLANE = Null();

	public static final Block VANILLA_MODEL_TEST = Null();

	public static final Block FULLBRIGHT = Null();

	public static final Block NORMAL_BRIGHTNESS = Null();

	public static final BlockMaxHealthSetter MAX_HEALTH_SETTER = Null();

	public static final BlockMaxHealthGetter MAX_HEALTH_GETTER = Null();

	public static final BlockSmallCollisionTest SMALL_COLLISION_TEST = Null();

	public static final BlockModChest CHEST = Null();

	public static final BlockHidden HIDDEN = Null();

	public static final BlockPipeBasic BASIC_PIPE = Null();

	public static final BlockPipeFluid FLUID_PIPE = Null();

	public static final BlockSurvivalCommandBlock SURVIVAL_COMMAND_BLOCK = Null();

	public static final BlockSurvivalCommandBlock REPEATING_SURVIVAL_COMMAND_BLOCK = Null();

	public static final BlockSurvivalCommandBlock CHAIN_SURVIVAL_COMMAND_BLOCK = Null();

	public static final BlockSaplingTestMod3 SAPLING = Null();

	public static final BlockInvisible INVISIBLE = Null();

	public static final BlockFluidTankRestricted FLUID_TANK_RESTRICTED = Null();

	public static final Block PLANKS = Null();

	public static class VariantGroups {
		public static final BlockVariantGroup<EnumDyeColor, BlockColoredRotatable> COLORED_ROTATABLE_BLOCKS = BlockVariantGroup.Builder.<EnumDyeColor, BlockColoredRotatable>create()
				.groupName("rotatable_block")
				.variants(EnumDyeColor.values())
				.material(Material.CLOTH)
				.blockFactory(BlockColoredRotatable::new)
				.build();

		public static final BlockVariantGroup<EnumDyeColor, BlockColoredMultiRotatable> COLORED_MULTI_ROTATABLE_BLOCKS = BlockVariantGroup.Builder.<EnumDyeColor, BlockColoredMultiRotatable>create()
				.groupName("multi_rotatable_block")
				.variants(EnumDyeColor.values())
				.material(Material.CLOTH)
				.blockFactory(BlockColoredMultiRotatable::new)
				.build();

		public static final BlockVariantGroup<BlockVariants.EnumType, BlockVariants> VARIANTS_BLOCKS = BlockVariantGroup.Builder.<BlockVariants.EnumType, BlockVariants>create()
				.groupName("variants_block")
				.suffix()
				.variants(BlockVariants.EnumType.values())
				.material(Material.IRON)
				.blockFactory(BlockVariants::new)
				.build();

		public static final SlabVariantGroup<EnumDyeColor, BlockColouredSlab> TERRACOTTA_SLABS = SlabVariantGroup.Builder.<EnumDyeColor, BlockColouredSlab>create()
				.groupName("terracotta_slab")
				.variants(EnumDyeColor.values())
				.material(Material.ROCK)
				.blockFactory((colour, material, isDouble, slabGroup) -> {
					final IProperty<EnumDyeColor> variantProperty = BlockSlabTestMod3.createVariantProperty(EnumDyeColor.class, colour);

					return new BlockColouredSlab(colour, material, slabGroup) {
						@Override
						public boolean isDouble() {
							return isDouble;
						}

						@Override
						public IProperty<EnumDyeColor> getVariantProperty() {
							return variantProperty;
						}
					};
				})
				.build();
	}

	@Mod.EventBusSubscriber(modid = TestMod3.MODID)
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		/**
		 * Register this mod's {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
					setBlockName(new BlockWaterGrass(), "water_grass"),
					setBlockName(new BlockLargeCollisionTest(), "large_collision_test"),
					setBlockName(new BlockRightClickTest(), "right_click_test"),
					setBlockName(new BlockClientPlayerRightClick(), "client_player_right_click"),
					setBlockName(new BlockRotatableLamp(), "rotatable_lamp"),
					setBlockName(new BlockItemCollisionTest(), "item_collision_test"),
					setBlockName(new BlockFluidTank<>(), "fluid_tank"),
					setBlockName(new BlockItemDebugger(), "item_debugger"),
					setBlockName(new Block(Material.ROCK), "end_portal_frame_full"),
					setBlockName(new BlockPotionEffect(), "potion_effect"),
					setBlockName(new BlockClientPlayerRotation(), "client_player_rotation"),
					setBlockName(new BlockPigSpawnerRefiller(), "pig_spawner_refiller"),
					setBlockName(new BlockPlane(Material.IRON), "mirror_plane"),
					setBlockName(new Block(Material.IRON), "vanilla_model_test"),
					setBlockName(new Block(Material.ROCK), "fullbright").setLightLevel(1),
					setBlockName(new Block(Material.ROCK), "normal_brightness"),
					setBlockName(new BlockMaxHealthSetter(), "max_health_setter"),
					setBlockName(new BlockMaxHealthGetter(), "max_health_getter"),
					setBlockName(new BlockSmallCollisionTest(), "small_collision_test"),
					setBlockName(new BlockModChest(), "chest"),
					setBlockName(new BlockHidden(Material.ROCK), "hidden"),
					setBlockName(new BlockPipeBasic(), "basic_pipe"),
					setBlockName(new BlockPipeFluid(), "fluid_pipe"),
					setBlockName(new BlockSurvivalCommandBlock(TileEntityCommandBlock.Mode.REDSTONE), "survival_command_block"),
					setBlockName(new BlockSurvivalCommandBlock(TileEntityCommandBlock.Mode.AUTO), "repeating_survival_command_block"),
					setBlockName(new BlockSurvivalCommandBlock(TileEntityCommandBlock.Mode.SEQUENCE), "chain_survival_command_block"),
					setBlockName(new BlockSaplingTestMod3(), "sapling"),
					setBlockName(new BlockInvisible(), "invisible"),
					setBlockName(new BlockFluidTankRestricted(), "fluid_tank_restricted"),
					setBlockName(new Block(Material.WOOD), "planks"),
			};

			for (final Block block : blocks) {
				RegistryUtil.setDefaultCreativeTab(block);

				registry.register(block);
			}

			VariantGroups.COLORED_ROTATABLE_BLOCKS.registerBlocks(registry);
			VariantGroups.COLORED_MULTI_ROTATABLE_BLOCKS.registerBlocks(registry);
			VariantGroups.TERRACOTTA_SLABS.registerBlocks(registry);
			VariantGroups.VARIANTS_BLOCKS.registerBlocks(registry);

			registerTileEntities();
		}

		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					new ItemBlock(WATER_GRASS),
					new ItemBlock(LARGE_COLLISION_TEST),
					new ItemBlock(RIGHT_CLICK_TEST),
					new ItemBlock(CLIENT_PLAYER_RIGHT_CLICK),
					new ItemBlock(ROTATABLE_LAMP),
					new ItemBlock(ITEM_COLLISION_TEST),
					new ItemFluidTank(FLUID_TANK),
					new ItemBlock(ITEM_DEBUGGER),
					new ItemBlock(END_PORTAL_FRAME_FULL),
					new ItemBlock(POTION_EFFECT),
					new ItemBlock(CLIENT_PLAYER_ROTATION),
					new ItemBlock(PIG_SPAWNER_REFILLER),
					new ItemBlock(MIRROR_PLANE),
					new ItemBlock(VANILLA_MODEL_TEST),
					new ItemBlock(FULLBRIGHT),
					new ItemBlock(NORMAL_BRIGHTNESS),
					new ItemBlock(MAX_HEALTH_SETTER),
					new ItemBlock(MAX_HEALTH_GETTER),
					new ItemBlock(SMALL_COLLISION_TEST),
					new ItemBlock(CHEST),
					new ItemBlock(HIDDEN),
					new ItemBlock(BASIC_PIPE),
					new ItemBlock(FLUID_PIPE),
					new ItemBlock(SURVIVAL_COMMAND_BLOCK),
					new ItemBlock(REPEATING_SURVIVAL_COMMAND_BLOCK),
					new ItemBlock(CHAIN_SURVIVAL_COMMAND_BLOCK),
					new ItemMultiTexture(SAPLING, SAPLING, BlockSaplingTestMod3::getName),
					new ItemBlock(INVISIBLE),
					new ItemFluidTank(FLUID_TANK_RESTRICTED),
					new ItemBlock(PLANKS),
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}

			registerVariantGroupItems(registry, VariantGroups.COLORED_ROTATABLE_BLOCKS);
			registerVariantGroupItems(registry, VariantGroups.COLORED_MULTI_ROTATABLE_BLOCKS);
			registerVariantGroupItems(registry, VariantGroups.TERRACOTTA_SLABS);
			registerVariantGroupItems(registry, VariantGroups.VARIANTS_BLOCKS);
		}


		/**
		 * Register an {@link IBlockVariantGroup}'s {@link Item}s and add them to {@link #ITEM_BLOCKS}.
		 *
		 * @param registry The Item registry
		 * @param group    The variant group
		 */
		private static void registerVariantGroupItems(final IForgeRegistry<Item> registry, final IBlockVariantGroup<?, ?> group) {
			final List<? extends ItemBlock> items = group.registerItems(registry);
			ITEM_BLOCKS.addAll(items);
		}
	}

	private static void registerTileEntities() {
		registerTileEntity(TileEntitySurvivalCommandBlock.class, "survival_command_block");
		registerTileEntity(TileEntityFluidTank.class, "fluid_tank");
		registerTileEntity(TileEntityColoredMultiRotatable.class, "colored_multi_rotatable");
		registerTileEntity(TileEntityPotionEffect.class, "potion_effect");
		registerTileEntity(TileEntityModChest.class, "mod_chest");
		registerTileEntity(TileEntityHidden.class, "hidden");
		registerTileEntity(TileEntityFluidTankRestricted.class, "fluid_tank_restricted");
	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(TestMod3.MODID, name));
	}
}
