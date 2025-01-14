package cn.ussshenzhou.rainbow6.entities;

import cn.ussshenzhou.rainbow6.items.HookRope;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

/**
 * @author USS_Shenzhou
 */
public class ModEntityTypes {
    public static EntityType nitroCellEntityType = EntityType.Builder.<NitroCellEntity>create(NitroCellEntity::new, EntityClassification.MISC)
            .size(0.25f, 0.12f)
            .build("nitrocell")
            .setRegistryName("rainbow6", "nitrocell");
    public static EntityType impactGrenadeEntityType = EntityType.Builder.<ImpactGrenadeEntity>create(ImpactGrenadeEntity::new, EntityClassification.MISC)
            .size(0.1f, 0.1f)
            .build("impactgrenade")
            .setRegistryName("rainbow6", "impactgrenade");
    public static EntityType fragGrenadeEntityType = EntityType.Builder.<FragGrenadeEntity>create(FragGrenadeEntity::new, EntityClassification.MISC)
            .size(0.1f, 0.1f)
            .build("fraggrenade")
            .setRegistryName("rainbow6", "fraggrenade");
    /*public static EntityType gumineentitytype = EntityType.Builder.<gumineentity>create(gumineentity::new,EntityClassification.MISC)
            .size(0.2f,0.1f)
            .build("gumine")
            .setRegistryName("gumine");*/
    public static EntityType remoteGasGrenadeEntityType = EntityType.Builder.<RemoteGasGrenadeEntity>create(RemoteGasGrenadeEntity::new, EntityClassification.MISC)
            .size(0.2f, 0.13f)
            .build("RemoteGasGrenade")
            .setRegistryName("rainbow6", "remotegasgrenade");
    public static EntityType proximityAlarmEntityType = EntityType.Builder.<ProximityAlarmEntity>create(ProximityAlarmEntity::new, EntityClassification.MISC)
            .size(0.35f, 0f)
            .build("ProximityAlarm")
            .setRegistryName("rainbow6", "proximityalarm");
    public static EntityType smokeGrenadeEntityType = EntityType.Builder.<SmokeGrenadeEntity>create(SmokeGrenadeEntity::new, EntityClassification.MISC)
            .size(0.1f, 0.1f)
            .build("smokeGrenade")
            .setRegistryName("rainbow6", "smokegrenade");
    public static EntityType droneEntityType = EntityType.Builder.<DroneEntity>create(DroneEntity::new, EntityClassification.MISC)
            .size(0.3f, 0.16f)
            .build("drone")
            .setRegistryName("rainbow6", "drone");

    public static EntityType hookRopeEntityType = EntityType.Builder.<HookRopeEntity>create(HookRopeEntity::new, EntityClassification.MISC)
            .size(0.3f, 0.16f)
            .build("hookrope")
            .setRegistryName("rainbow6", "hookrope");
}
