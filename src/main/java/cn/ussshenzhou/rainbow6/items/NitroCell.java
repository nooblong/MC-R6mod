package cn.ussshenzhou.rainbow6.items;

import cn.ussshenzhou.rainbow6.entities.NitroCellEntity;
import cn.ussshenzhou.rainbow6.utils.ModItemGroups;
import cn.ussshenzhou.rainbow6.utils.ModSounds;
import cn.ussshenzhou.rainbow6.entities.ModEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
/**
 * @author USS_Shenzhou
 */
public class NitroCell extends Item {

    public NitroCell() {
        super(new Properties().group(ModItemGroups.Main));
        this.setRegistryName("nitrocell");
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound((PlayerEntity)null,playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(), ModSounds.NITRO_CELL_THROW,SoundCategory.PLAYERS,1.0f,1.0f);
        if (!worldIn.isRemote) {
            NitroCellEntity nitroCellEntity = new NitroCellEntity(ModEntityTypes.nitroCellEntityType,playerIn,worldIn);
            nitroCellEntity.setItem(itemstack);
            nitroCellEntity.shoot(playerIn.getLookVec().x,playerIn.getLookVec().y,playerIn.getLookVec().z, 0.45F, 0.1F);
            nitroCellEntity.setRandomRotation();
            worldIn.addEntity(nitroCellEntity);
        }
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        playerIn.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

}

