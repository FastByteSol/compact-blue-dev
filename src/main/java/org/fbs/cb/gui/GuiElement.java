package org.fbs.cb.gui;

import net.minecraft.client.gui.GuiGraphics;
import org.fbs.cb.exception.GuiDrawException;

public abstract class GuiElement {

    protected boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }

    public abstract void setActive(boolean isActive);

    public abstract void draw(GuiGraphics guiGraphics) throws GuiDrawException;

    @Override
    public String toString(){
        return this.getClass().getName() + " " + this.getClass().getSimpleName();
    }

}
