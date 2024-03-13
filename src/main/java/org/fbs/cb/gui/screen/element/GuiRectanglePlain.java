package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.GuiColor;
import org.fbs.cb.gui.GuiElement;

public class GuiRectanglePlain extends GuiElement {

    private GuiColor color;
    private Vec2 firstPoint, secondPoint;

    public void setColor(GuiColor color) {
        this.color = color;
    }

    public void setCoordinates(Vec2 firstPoint, Vec2 secondPoint){
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public void setDrawn(boolean isDrawn) {

    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {

        if (color == null) throw new GuiDrawException("Color is null");
        if (firstPoint == null) throw new GuiDrawException("First point is null");
        if (secondPoint == null) throw new GuiDrawException("Second point is null");

        guiGraphics.fillGradient((int) firstPoint.x, (int) firstPoint.y, (int) secondPoint.x, (int) secondPoint.y, color.getColor(), color.getColor());

    }

}
