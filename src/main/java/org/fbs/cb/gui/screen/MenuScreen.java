package org.fbs.cb.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;

import java.util.List;

public class MenuScreen extends Screen {

    private static boolean isOpen;
    private final Screen parent;
    private final Minecraft client;
    private final int width;
    private final int height;

    private final List<GuiEventListener> children = (List<GuiEventListener>) children();

    public MenuScreen(Screen parent, Minecraft client) {
        super(Component.literal("Menu Screen"));
        this.client = client;
        this.parent = parent;
        if (parent != null) {
            width = parent.getRectangle().width();
            height = parent.getRectangle().height();
        }
        else {
            width = client.getWindow().getWidth();
            height = client.getWindow().getHeight();
        }
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void render(GuiGraphics guiGraphics, int p_281550_, int p_282878_, float p_282465_) {

        //guiGraphics.fillGradient(0, 0, width, height, 0xFF0000FF, 0xFF0000FF);

        super.render(guiGraphics, p_281550_, p_282878_, p_282465_);
        isOpen = true;
    }

    public static boolean isOpen(){
        return isOpen;
    }

    @Override
    public boolean mouseClicked(double p_94695_, double p_94696_, int p_94697_) {
        return super.mouseClicked(p_94695_, p_94696_, p_94697_);
    }

    @Override
    public void onClose() {
        if (parent == null) client.setScreen(parent);
        else client.player.sendSystemMessage(Component.literal("sfe"));
        isOpen = false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

    public void addListener(GuiEventListener listener){
        children.add(listener);
    }

}
