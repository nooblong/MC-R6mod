package cn.ussshenzhou.rainbow6.items;

import cn.ussshenzhou.rainbow6.utils.ModItemGroups;
import net.minecraft.item.Item;

public class HookRope extends Item {
    public HookRope(){
        super(new Properties().group(ModItemGroups.Main));
        this.setRegistryName("hookrope");
    }
}
