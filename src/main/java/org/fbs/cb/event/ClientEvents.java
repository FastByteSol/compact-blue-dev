package org.fbs.cb.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.fbs.cb.CB;
import org.fbs.cb.gui.screen.MenuScreen;
import org.fbs.cb.util.KeyBinding;

public class ClientEvents {
    private static final MenuScreen menuScreen = new MenuScreen(Minecraft.getInstance().screen, Minecraft.getInstance());
    @Mod.EventBusSubscriber(modid = CB.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.OPEN_MENU_KEY);
        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key key){
            if (KeyBinding.OPEN_MENU_KEY.consumeClick()){
                try {
                    Minecraft.getInstance().setScreen(menuScreen);
                }
                catch (Exception e){
                    CB.LOGGER.info("info:", (Object) e.getStackTrace());
                }

                //AutoTool.equip(Minecraft.getInstance());
                //FIXME:ARRAY INDEX 16 OUT OF BOUNDS LENGTH 16
            }
        }

    }

}
