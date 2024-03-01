package org.fbs.cb.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.fbs.cb.CB;
import org.fbs.cb.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CB.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.OPEN_MENU_KEY);
        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key key){
            if (KeyBinding.OPEN_MENU_KEY.consumeClick()){
                String inventoryHotBarIndex1 = "";
                String usedItem = "";
                String hitResultType = "";
                String screenSize = "";
                Vec3 hitResultLoc = null;
                Vec3 playerLoc = null;
                Direction playerDir = null;
                BlockState hitResultBlock = null;
                try {
                    inventoryHotBarIndex1 = Minecraft.getInstance().player.getInventory().items.get(1).toString();
                    screenSize = Minecraft.getInstance().getWindow().getWidth() + "x" + Minecraft.getInstance().getWindow().getHeight();
                    usedItem = Minecraft.getInstance().player.getMainHandItem().getItem().toString();
                    hitResultType = String.valueOf(Minecraft.getInstance().hitResult.getType());
                    hitResultLoc = Minecraft.getInstance().hitResult.getLocation();
                    playerLoc = new Vec3(Minecraft.getInstance().player.getX(), Minecraft.getInstance().player.getY(), Minecraft.getInstance().player.getZ());
                    playerDir = Minecraft.getInstance().player.getDirection();
                    // north, west, lower r parallel
                    if (playerDir == Direction.NORTH){
                        //x ++
                        hitResultLoc = new Vec3(hitResultLoc.x + 1, hitResultLoc.y, hitResultLoc.z);
                        if (playerLoc.y < hitResultLoc.y){
                            hitResultLoc = new Vec3(hitResultLoc.x, hitResultLoc.y + 1, hitResultLoc.z);
                        }
                    }
                    else if (playerDir == Direction.WEST){
                        //y ++
                        hitResultLoc = new Vec3(hitResultLoc.x, hitResultLoc.y + 1, hitResultLoc.z);
                        if (playerLoc.x > hitResultLoc.x){
                            hitResultLoc = new Vec3(hitResultLoc.x - 1, hitResultLoc.y, hitResultLoc.z);
                        }
                    }
                    if (playerLoc.z >= hitResultLoc.z){
                        hitResultLoc = new Vec3(hitResultLoc.x, hitResultLoc.y, hitResultLoc.z - 1);
                    }
                    hitResultBlock = Minecraft.getInstance().player.getCommandSenderWorld().getBlockState(new BlockPos((int) hitResultLoc.x, (int) hitResultLoc.y, (int) hitResultLoc.z));
                }
                catch (Exception exception){
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Code has an Exception: " + exception.getMessage()));
                }
                String gamePath = Minecraft.getInstance().gameDirectory.getAbsolutePath();

                String data = String.format(
                        """
                        inventoryHotBarIndex1: %s
                        screenSize: %s
                        gamePath: %s
                        usedItem: %s
                        hitResultType: %s
                        hitResultLoc: %s
                        playerLoc: %s
                        hitResultBlock: %s
                        direction: %s
                        """, inventoryHotBarIndex1, screenSize, gamePath, usedItem, hitResultType, getByInt(hitResultLoc), getByInt(playerLoc), hitResultBlock, playerDir);
                Minecraft.getInstance().player.sendSystemMessage(Component.literal(data));
            }
        }
        private static Vec3 getByInt(Vec3 vec3){
            return new Vec3((int)vec3.x, (int)vec3.y, (int)vec3.z);
        }
    }

}
