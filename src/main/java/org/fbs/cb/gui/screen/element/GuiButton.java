package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;

public class GuiButton extends MenuGuiElement implements MenuGuiListener {
    @Override
    protected void draw(GuiGraphics guiGraphics) throws GuiDrawException {

    }

    @Override
    public void onClick(double mouseX, double mouseY, int mouseKey) {
        CB.LOGGER.info("fferfggvrrtgrtg");
    }
}
