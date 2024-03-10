package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.exception.GuiDrawException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuiPanel extends ActionGuiElement{

    private boolean isDrawn = false;
    private ActionGuiElement lastClicked = null;
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

    private final List<ActionGuiElement> actionGuiElementList = new ArrayList<>();

    public void addGuiElement(ActionGuiElement actionGuiElement){
        actionGuiElementList.add(actionGuiElement);
    }

    public void removeGuiElement(ActionGuiElement actionGuiElement){
        actionGuiElementList.remove(actionGuiElement);
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void addScroller(@NotNull Scroller scroller){
        scroller = scroller;
        hasScroller = true;
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {
        if (!actionGuiElementList.isEmpty()){
            for (ActionGuiElement guiElement: actionGuiElementList){
                guiElement.draw(guiGraphics);
            }
            isDrawn = true;
        }
    }

    @Override
    public boolean onMouseClick(double mouseX, double mouseY, int mouseKey) {
        if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
            for (ActionGuiElement guiElement : actionGuiElementList) {
                if (guiElement.onMouseClick(mouseX, mouseY, mouseKey)){
                    lastClicked = guiElement;
//                    removeGuiElement(guiElement);
//                    addGuiElement(guiElement);
                }
            }
            return true;
        }
        return false;
    }
}
