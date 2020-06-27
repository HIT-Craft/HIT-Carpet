package carpet.utils.portalsearcher;

import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Comparator;

public abstract class PortalSearcherAbstract {
    protected final World worldIn;
    public static NetherPortalBlock BLOCK_NETHER_PORTAL = (NetherPortalBlock) Blocks.NETHER_PORTAL;
    protected static final Comparator<BlockPos> vallinaComparator =
            Comparator.comparing(BlockPos::getX).thenComparing(BlockPos::getZ).thenComparing(p -> -p.getY());

    public PortalSearcherAbstract(World worldIn) {
        this.worldIn = worldIn;
    }

    public abstract PortalSearchResult search(BlockPos searchCenter);
}
