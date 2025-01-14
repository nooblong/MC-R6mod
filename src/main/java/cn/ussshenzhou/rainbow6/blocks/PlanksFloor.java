package cn.ussshenzhou.rainbow6.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author USS_Shenzhou
 */
public class PlanksFloor extends Block {

    public PlanksFloor() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(80f, 1.5f)
                .sound(SoundType.WOOD)
        );
    }


    public void onDestroyed(BlockState state, World worldIn, BlockPos pos) {
        worldIn.destroyBlock(pos,false);
        worldIn.setBlockState(pos,ModBlocks.ironBarFloor.getDefaultState().with(BlockStateProperties.FACING,state.get(BlockStateProperties.FACING)));
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        this.onDestroyed(state,(World)worldIn,pos);
    }

    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        this.onDestroyed(state,world,pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null){
            Direction direction = getFacingFromEntity(pos,placer);
            if (direction!=Direction.DOWN && direction!=Direction.UP){
                worldIn.setBlockState(pos, this.getDefaultState().with(BlockStateProperties.FACING,direction),2);
            }
            else {
                ItemStack returnStack = new ItemStack(this);
                ((PlayerEntity)placer).addItemStackToInventory(returnStack);
                worldIn.removeBlock(pos,false);
                worldIn.removeBlock(pos,false);
            }
        }
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity){
        Vector3d vec =entity.getPositionVec();
        return Direction.getFacingFromVector((float) (vec.x - clickedBlock.getX()),(float) (vec.y - clickedBlock.getY()),(float) (vec.z - clickedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
        super.fillStateContainer(builder);
    }

}
