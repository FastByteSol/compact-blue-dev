package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;

public class GuiButton extends MenuGuiElement{
    @Override
    protected void draw(GuiGraphics guiGraphics) throws GuiDrawException {

    }

    @Override
    protected void onClick(double mouseX, double mouseY, int mouseKey) {
        CB.LOGGER.info("click");
    }
}
