package org.fbs.cb.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.screen.element.GuiColors;
import org.fbs.cb.gui.screen.element.GuiRectanglePlain;

public class MenuScreen extends Screen {
    private final Minecraft client;
    private final int width;
    private final int height;

    public MenuScreen(Screen parent, Minecraft client) {
        super(Component.literal("Menu Screen"));
        this.client = client;
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float p_282465_) {

        GuiRectanglePlain rectanglePlain = new GuiRectanglePlain();
        rectanglePlain.setColor(GuiColors.TRANSLUCENT_WHITE);
        rectanglePlain.setCoordinates(new Vec2(0, 0), new Vec2(width, height));
        try {
            rectanglePlain.draw(guiGraphics);
        } catch (GuiDrawException e) {
            CB.LOGGER.info(e.getMessage());
        }

        super.render(guiGraphics, mouseX, mouseY, p_282465_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseKey) {

        return super.mouseClicked(mouseX, mouseY, mouseKey);
    }

    @Override
    public void onClose() {
        client.setScreen(null);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

}
