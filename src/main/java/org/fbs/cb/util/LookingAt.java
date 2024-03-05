package org.fbs.cb.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class LookingAt {

    private LookingAt(){}

    public static BlockState takeAtLook(){
        HitResult rt = Minecraft.getInstance().hitResult;

        double x = 0;
        double y = 0;
        double z = 0;

        try {
            x = (rt.getLocation().x);
            y = (rt.getLocation().y);
            z = (rt.getLocation().z);
        }
        catch (Exception e){
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(e.getMessage()));
        }

        double xla = 0;
        double yla = 0;
        double zla = 0;

        try {
            xla = Minecraft.getInstance().player.getLookAngle().x;
            yla = Minecraft.getInstance().player.getLookAngle().y;
            zla = Minecraft.getInstance().player.getLookAngle().z;
        }
        catch (Exception e){
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(e.getMessage()));
        }

        if ((x%1==0)&&(xla<0))x-=0.01;
        if ((y%1==0)&&(yla<0))y-=0.01;
        if ((z%1==0)&&(zla<0))z-=0.01;

        BlockPos ps = new BlockPos((int)x, (int)y, (int)z);
        BlockState bl = null;

        try {
            bl = Minecraft.getInstance().level.getBlockState(ps);
        }
        catch (Exception e){
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(e.getMessage()));
        }

        return bl;
    }

}
