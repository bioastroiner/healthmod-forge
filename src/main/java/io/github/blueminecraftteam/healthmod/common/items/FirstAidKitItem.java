package io.github.blueminecraftteam.healthmod.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FirstAidKitItem extends Item {
    public FirstAidKitItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(worldIn.isRemote){
            Minecraft mc = Minecraft.getInstance();
            RayTraceResult result = mc.objectMouseOver;

            if(result.getType() == RayTraceResult.Type.ENTITY){

            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
