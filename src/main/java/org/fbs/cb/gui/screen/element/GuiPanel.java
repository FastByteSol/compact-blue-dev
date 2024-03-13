package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.Scroller;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuiPanel extends ActionGuiElement {

    private ActionGuiElement lastClicked, lastFocused, lastReleased;
    private Scroller scroller = null;
    private boolean hasScroller = false;
    private Vec2 firstPoint, secondPoint;
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

    private final List<GuiButton> guiButtonList = new ArrayList<>();

    public void addGuiElement(GuiButton guiButton){
        guiButtonList.add(guiButton);
    }

    public void removeGuiElement(GuiButton guiButton){
        guiButtonList.remove(guiButton);
    }

    public void addScroller(@NotNull Scroller scroller){
        this.scroller = scroller;
        hasScroller = true;
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {
        if (!guiButtonList.isEmpty()){
            for (ActionGuiElement guiElement: guiButtonList){
                guiElement.draw(guiGraphics);
            }
            isDrawn = true;
            isActive = true;
        }
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void setDrawn(boolean isDrawn) {

    }

    @Override
    public boolean onMouseClick(double mouseX, double mouseY, int mouseKey) throws GuiDrawException {
        if (isActive) {
            if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                    && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
                if (lastClicked.onMouseClick(mouseX, mouseY, mouseKey)) {}
                else if (lastFocused.onMouseClick(mouseX, mouseY, mouseKey)){}
                else {
                    for (ActionGuiElement guiElement : guiButtonList) {
                        if (guiElement.onMouseClick(mouseX, mouseY, mouseKey)) {
                            lastClicked = guiElement;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onMouseMove(double mouseX, double mouseY) {
        if (isActive) {
            if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                    && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
                if (lastFocused.onMouseMove(mouseX, mouseY)) {
                }
                else {
                    for (ActionGuiElement guiElement : guiButtonList) {
                        if (guiElement.onMouseMove(mouseX, mouseY)) {
                            lastFocused = guiElement;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onMouseReleased(double mouseX, double mouseY, int mouseKey) {
        if (isActive) {
            if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                    && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
                if (lastReleased.onMouseReleased(mouseX, mouseY, mouseKey)) {
                }
                else {
                    for (ActionGuiElement guiElement : guiButtonList) {
                        if (guiElement.onMouseReleased(mouseX, mouseY, mouseKey)) {
                            lastReleased = guiElement;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

}
