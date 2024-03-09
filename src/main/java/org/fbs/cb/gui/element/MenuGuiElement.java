package org.fbs.cb.gui.element;

import net.minecraft.client.gui.GuiGraphics;
import org.fbs.cb.exception.GuiDrawException;

public abstract class MenuGuiElement {

    protected abstract void draw(GuiGraphics guiGraphics) throws GuiDrawException;

}
