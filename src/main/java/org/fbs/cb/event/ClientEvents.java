package org.fbs.cb.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.fbs.cb.CB;
import org.fbs.cb.service.AutoTool;
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
                AutoTool.equip(Minecraft.getInstance());
            }
        }

    }

}
