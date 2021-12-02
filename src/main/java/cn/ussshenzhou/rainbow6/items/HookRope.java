package cn.ussshenzhou.rainbow6.items;

import cn.ussshenzhou.rainbow6.entities.FragGrenadeEntity;
import cn.ussshenzhou.rainbow6.entities.HookRopeEntity;
import cn.ussshenzhou.rainbow6.entities.ModEntityTypes;
import cn.ussshenzhou.rainbow6.utils.ModItemGroups;
import cn.ussshenzhou.rainbow6.utils.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class HookRope extends Item {
    public HookRope() {
        super(new Properties().group(ModItemGroups.Main));
        this.setRegistryName("hookrope");
    }

    private final int MAX_TIME = 100;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(stack);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLiving;
            ItemStack itemstack = player.getHeldItem(player.getActiveHand());
            if (!worldIn.isRemote) {
                HookRopeEntity hookRopeEntity = new HookRopeEntity(ModEntityTypes.hookRopeEntityType, entityLiving, worldIn, player);
                hookRopeEntity.setItem(itemstack);
                hookRopeEntity.shoot(player.getLookVec().x, player.getLookVec().y, player.getLookVec().z, 0.8F, 0.1F);
                worldIn.addEntity(hookRopeEntity);
            }
            player.addStat(Stats.ITEM_USED.get(this));

        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return this.MAX_TIME;
    }
}
