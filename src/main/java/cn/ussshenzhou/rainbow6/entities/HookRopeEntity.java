package cn.ussshenzhou.rainbow6.entities;

import cn.ussshenzhou.rainbow6.gui.R6ThrowableEntityUtils;
import cn.ussshenzhou.rainbow6.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

public class HookRopeEntity extends ProjectileItemEntity {

    public HookRopeEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HookRopeEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn, PlayerEntity player) {
        super(type, livingEntityIn, worldIn);
        this.dataManager.set(playerId, player.getUniqueID().toString());
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
//        if (dataManager.get(beenHit) && !world.isRemote) {
//            R6ThrowableEntityUtils.ThrowableEntityMovementFix.fix(this, world, this.getGravityVelocity());
//            EntityDataManager dataManager = getDataManager();
//            PlayerEntity player = getEntityWorld().getPlayerByUuid(UUID.fromString(dataManager.get(playerId)));
//            Vector3d playerVec = player.getPositionVec();
//            int hitx = dataManager.get(hitPos).getX();
//            int hitz = dataManager.get(hitPos).getZ();
//            if (player.getPosZ() != hitz && player.getPosX() != hitx) {
//                player.setPositionAndUpdate(hitx, player.getPosY(), hitz);
//                System.out.println(dataManager.get(hitPos) + "  " + dataManager.get(beenHit));
//            }
//        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        Vector3d hitVec = result.getHitVec();
        if (result.getType() == RayTraceResult.Type.BLOCK) {
            this.setMotion(0, 0, 0);
            this.setVelocity(0, 0, 0);
            this.setNoGravity(true);
            this.setOnGround(true);
            this.markVelocityChanged();
            getDataManager().set(beenHit, true);
        }

        PlayerEntity player = world.getPlayerByUuid(UUID.fromString(dataManager.get(playerId)));
        Vector3d lookVec = hitVec.subtract(player.getPositionVec());
        double setPlayerPosX = hitVec.getX();
        double setPlayerPosZ = hitVec.getZ();
        System.out.println(lookVec);
        if (Math.abs(lookVec.getX()) > Math.abs(lookVec.getZ())){
            //只需要减x
            if (lookVec.getX() < 0){
                setPlayerPosX += 1;
            }
            if (lookVec.getX() > 0){
                setPlayerPosX -= 1;
            }
        } else {
            //只需要减z
            if (lookVec.getZ() < 0){
                setPlayerPosZ += 1;
            }
            if (lookVec.getZ() > 0){
                setPlayerPosZ -= 1;
            }
        }

        player.setPositionAndUpdate(setPlayerPosX, player.getPosY(), setPlayerPosZ);

        super.onImpact(result);
    }

    public static DataParameter<String> playerId = EntityDataManager.createKey(HookRopeEntity.class, DataSerializers.STRING);
    public static DataParameter<Boolean> beenHit = EntityDataManager.createKey(HookRopeEntity.class, DataSerializers.BOOLEAN);
    public static DataParameter<BlockPos> hitPos = EntityDataManager.createKey(HookRopeEntity.class, DataSerializers.BLOCK_POS);

    @Override
    protected void registerData() {
        this.dataManager.register(playerId, "");
        this.dataManager.register(beenHit, false);
        this.dataManager.register(hitPos, null);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("playerId")) {
            this.dataManager.set(playerId, compound.getString("playerId"));
            this.dataManager.set(beenHit, compound.getBoolean("using"));
            this.dataManager.set(hitPos, (BlockPos) compound.get("hitPos"));
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("playerId", this.dataManager.get(playerId));
        compound.putBoolean("using", this.dataManager.get(beenHit));
        compound.put("hitPos", (INBT) this.dataManager.get(hitPos));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
