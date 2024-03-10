package org.fbs.cb.gui.screen.element;

public abstract class ActionGuiElement extends GuiElement {

    public boolean onMouseClick(double mouseX, double mouseY, int mouseKey){
        return false;
    }

    public boolean onMouseReleased(double mouseX, double mouseY, int mouseKey){
        return false;
    }

    public boolean onMouseMove(double mouseX, double mouseY){
        return false;
    }

}