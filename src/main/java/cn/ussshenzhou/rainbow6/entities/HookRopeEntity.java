package cn.ussshenzhou.rainbow6.entities;

import cn.ussshenzhou.rainbow6.gui.R6ThrowableEntityUtils;
import cn.ussshenzhou.rainbow6.items.ModItems;
import cn.ussshenzhou.rainbow6.utils.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HookRopeEntity extends ProjectileItemEntity {

    public HookRopeEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HookRopeEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public HookRopeEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.hookrope;
    }

    @Override
    public void tick() {
        super.tick();
        R6ThrowableEntityUtils.ThrowableEntityMovementFix.fix(this, world, this.getGravityVelocity());
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            this.setMotion(0, 0, 0);
            this.setVelocity(0, 0, 0);
            this.setNoGravity(true);
            this.setOnGround(true);
            this.markVelocityChanged();
        }
        super.onImpact(result);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public void readAdditional(CompoundNBT compound) {

    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
