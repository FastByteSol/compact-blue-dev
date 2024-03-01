package org.fbs.cb.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY = "key.category.compact_blue.default";
    public static final String KEY_OPEN_MENU = "key.compact_blue.open_menu";

    public static final KeyMapping OPEN_MENU_KEY = new KeyMapping(KEY_OPEN_MENU,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            KEY_CATEGORY
            );

}
