package choonster.testmod3.api.capability.chunkenergy;

import net.minecraft.util.math.ChunkPos;

/**
 * Allows {@link IChunkEnergy} instances to be added and removed.
 *
 * @author Choonster
 * @deprecated Use {@link IChunkEnergy} directly instead; TODO: Remove in 1.13
 */
@Deprecated
public interface IChunkEnergyHolderModifiable extends IChunkEnergyHolder {

	/**
	 * Set the {@link IChunkEnergy} for the specified chunk position.
	 *
	 * @param chunkPos    The chunk position
	 * @param chunkEnergy The IChunkEnergy
	 */
	void setChunkEnergy(final ChunkPos chunkPos, final IChunkEnergy chunkEnergy);

	/**
	 * Remove the {@link IChunkEnergy} for the specified chunk position.
	 *
	 * @param chunkPos The chunk position
	 */
	void removeChunkEnergy(final ChunkPos chunkPos);
}
