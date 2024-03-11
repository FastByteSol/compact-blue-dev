package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.jetbrains.annotations.NotNull;

public class GuiButton extends ActionGuiElement {

    private Vec2 firstPoint, secondPoint;
    private GuiColor fillColor, borderColor;
    private Component text;
    private GuiColor textColor;
    private Font font;
    private boolean hasBorder = false;
    private boolean isDrawn = false;
    private boolean isActive = true;
    private GuiGraphics guiGraphics = null;

    public void setFillColor(GuiColor fillColor) {
        this.fillColor = fillColor;
    }

    public void setHasBorder(boolean hasBorder, GuiColor borderColor) {
        this.hasBorder = hasBorder;
        this.borderColor = borderColor;
    }

    public void setText(Component text, Font font, GuiColor textColor) {
        this.text = text;
        this.font = font;
        this.textColor = textColor;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCoordinates(@NotNull Vec2 firstPoint, @NotNull Vec2 secondPoint){
        float x0, x1;
        float y0, y1;
        if (firstPoint.x <= secondPoint.x){
             x0 = firstPoint.x;
             x1 = secondPoint.x;
        }
        else {
            x0 = secondPoint.x;
            x1 = firstPoint.x;
        }
        if (firstPoint.y <= secondPoint.y){
            y0 = firstPoint.y;
            y1 = secondPoint.y;
        }
        else {
            y0 = secondPoint.y;
            y1 = firstPoint.y;
        }
        this.firstPoint = new Vec2(x0, y0);
        this.secondPoint = new Vec2(x1, y1);
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {

        this.guiGraphics = guiGraphics;

        if (fillColor == null) throw new GuiDrawException("Fill color is null");
        if (firstPoint == null) throw new GuiDrawException("First point is null");
        if (secondPoint == null) throw new GuiDrawException("Second point is null");

        GuiRectanglePlain button = new GuiRectanglePlain();
        button.setColor(fillColor);
        button.setCoordinates(firstPoint, secondPoint);
        button.draw(guiGraphics);

        if (text != null){
            if (font == null) throw new GuiDrawException("Font is null");
            double width = secondPoint.x - firstPoint.x;
            double height = secondPoint.y - firstPoint.y;
            guiGraphics.drawCenteredString(font, text, (int) Math.round(width / 2), (int)Math.round(height / 2), textColor.getColor());
        }

        if (hasBorder){
            if (borderColor == null) throw new GuiDrawException("Border color is null");
        }

        isDrawn = true;
    }

    @Override
    public boolean onMouseClick(double mouseX, double mouseY, int mouseKey) {
        if (isDrawn) {
            if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                    && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
                if (mouseKey == 0) {
                    CB.LOGGER.info("Left click");
                } else if (mouseKey == 1) {
                    CB.LOGGER.info("Right click");
                } else if (mouseKey == 2) {
                    CB.LOGGER.info("Scroll click");
                } else {
                    CB.LOGGER.info("Unregistered click");
                }
                return true;
            }
        }
        return false;
    }
}
