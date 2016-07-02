package twilightforest.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTFUberousSoil extends Block implements IGrowable {

	protected BlockTFUberousSoil() {
		super(Material.GROUND);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
        this.setHardness(0.6F);
        this.setSoundType(SoundType.GROUND);
        this.setTickRandomly(true);
        
        this.setBlockTextureName(TwilightForestMod.ID + ":uberous_soil");
        
		this.setCreativeTab(TFItems.creativeTab);
    }

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random p_149650_2_, int p_149650_3_)
    {
        return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState(), p_149650_2_, p_149650_3_);
    }

	@Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    	Material aboveMaterial = world.getBlockState(pos.up()).getMaterial();
    	if (aboveMaterial.isSolid()) {
    		world.setBlockState(pos, Blocks.DIRT.getDefaultState());
    	}    
    }

    /**
     * Determines if this block can support the passed in plant, allowing it to be planted and grow.
     * Some examples:
     *   Reeds check if its a reed, or if its sand/dirt/grass and adjacent to water
     *   Cacti checks if its a cacti, or if its sand
     *   Nether types check for soul sand
     *   Crops check for tilled soil
     *   Caves check if it's a solid surface
     *   Plains check if its grass or dirt
     *   Water check if its still water
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @param direction The direction relative to the given position the plant wants to be, typically its UP
     * @param plantable The plant that wants to check
     * @return True to allow the plant to be planted/stay.
     */
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        //Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        return plantType == EnumPlantType.Crop ||  plantType == EnumPlantType.Plains ||  plantType == EnumPlantType.Cave;
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        Block above = world.getBlock(x, y + 1, z);
        Material aboveMaterial = above.getMaterial();

        if (aboveMaterial.isSolid())
        {
        	world.setBlock(x, y, z, Blocks.DIRT);
        }

        if (above instanceof IPlantable) {
        	IPlantable plant = (IPlantable)above;
        	// revert to farmland or grass
        	if (plant.getPlantType(world, x, y + 1, z) == EnumPlantType.Crop) {
        		world.setBlock(x, y, z, Blocks.FARMLAND, 2, 2);
        	} else if (plant.getPlantType(world, x, y + 1, z) == EnumPlantType.Plains) {
        		world.setBlock(x, y, z, Blocks.GRASS);
        	} else {
        		world.setBlock(x, y, z, Blocks.DIRT);
        	}
        	// apply bonemeal
        	ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, x, y + 1, z, null);
        	ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, x, y + 1, z, null);
        	ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, x, y + 1, z, null);
        	ItemDye.applyBonemeal(new ItemStack(Items.DYE), world, x, y + 1, z, null);
        	// green sparkles
        	if (!world.isRemote) {
        		world.playAuxSFX(2005, x, y + 1, z, 0);
        	}
        }
    }

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean var5) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		int gx = x;
		int gy = y;
		int gz = z;

		if (rand.nextBoolean()) {
			gx += (rand.nextBoolean() ? 1 : -1);
		} else {
			gz += (rand.nextBoolean() ? 1 : -1);
		}

		Block blockAt = world.getBlock(gx, gy, gz);
		if (world.isAirBlock(gx, gy + 1, gz) && (blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt == Blocks.FARMLAND)) {
			world.setBlock(gx, gy, gz, this);
		}
	}
}
