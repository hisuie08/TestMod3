package choonster.testmod3.block;

import choonster.testmod3.tileentity.ModChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * A chest block.
 *
 * @author Choonster
 */
public class ModChestBlock extends TileEntityBlock<ModChestTileEntity> {
	/**
	 * The chest's shape.
	 */
	private static final VoxelShape SHAPE = makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	public static final IProperty<Direction> FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

	public ModChestBlock(final Block.Properties properties) {
		super(true, properties);
	}

	@Override
	protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(final BlockState state, final IBlockReader world, final BlockPos pos, final ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		return new ModChestTileEntity();
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final BlockState state, @Nullable final LivingEntity placer, final ItemStack stack) {
		if (stack.hasDisplayName()) {
			final ModChestTileEntity tileEntity = getTileEntity(worldIn, pos);
			if (tileEntity != null) {
				tileEntity.setDisplayName(stack.getDisplayName());
			}
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(final BlockItemUseContext context) {
		return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult rayTraceResult) {
		if (!world.isRemote && !isBlocked(world, pos)) {
			final ModChestTileEntity tileEntity = getTileEntity(world, pos);
			if (tileEntity != null) {
				tileEntity.openGUI((ServerPlayerEntity) player);
			}
		}

		return true;
	}

	/**
	 * Is the chest at the specified position blocked?
	 *
	 * @param world The World
	 * @param pos   The position
	 * @return Is the chest blocked?
	 */
	private boolean isBlocked(final World world, final BlockPos pos) {
		return isBelowSolidBlock(world, pos) || isCatSittingOnChest(world, pos);
	}

	/**
	 * Is the chest at the specified position below a solid block?
	 *
	 * @param world The World
	 * @param pos   The position
	 * @return Is the chest below a solid block?
	 */
	private boolean isBelowSolidBlock(final World world, final BlockPos pos) {
		return world.getBlockState(pos.up()).isNormalCube(world, pos.up());
	}

	/**
	 * Is a Cat sitting on the chest at the specified position?
	 *
	 * @param world The World
	 * @param pos   The position
	 * @return Is a Cat sitting on the chest?
	 */
	private boolean isCatSittingOnChest(final World world, final BlockPos pos) {
		for (final CatEntity cat : world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1))) {
			if (cat.isSitting()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public BlockState rotate(final BlockState state, final IWorld world, final BlockPos pos, final Rotation direction) {
		return state.with(FACING, direction.rotate(state.get(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.with(FACING, mirror.mirror(state.get(FACING)));
	}

	@Override
	public void getDrops(final BlockState state, final NonNullList<ItemStack> drops, final World world, final BlockPos pos, final int fortune) {
		super.getDrops(state, drops, world, pos, fortune);

		final ModChestTileEntity tileEntity = getTileEntity(world, pos);
		if (tileEntity != null) {
			drops.addAll(tileEntity.getDrops());
		}
	}
}