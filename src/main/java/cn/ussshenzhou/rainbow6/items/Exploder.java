package cn.ussshenzhou.rainbow6.items;

import cn.ussshenzhou.rainbow6.entities.RemoteGasGrenadeEntity;
import cn.ussshenzhou.rainbow6.utils.ModItemGroups;
import cn.ussshenzhou.rainbow6.utils.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author USS_Shenzhou
 */
public class Exploder extends Item {
    public Exploder() {
        super(new Properties().group(ModItemGroups.Main));
        this.setRegistryName("exploder");
    }
    private static final Predicate<RemoteGasGrenadeEntity> ifexist = new Predicate<RemoteGasGrenadeEntity>() {
        @Override
        public boolean test(RemoteGasGrenadeEntity entity) {
            return entity != null ;
        }
    };
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound(playerIn,playerIn.getPosition(),ModSounds.EXPLODER_CLICK,SoundCategory.PLAYERS,0.7f,1.0f);
        List<RemoteGasGrenadeEntity> list = worldIn.getEntitiesWithinAABB(RemoteGasGrenadeEntity.class, playerIn.getBoundingBox().grow(50.0d),ifexist);
        if (!list.isEmpty()){
            for(RemoteGasGrenadeEntity entity:list){
                entity.explode();
            }
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
