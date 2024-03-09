package org.fbs.cb.gui.screen.element;

import java.util.ArrayList;
import java.util.List;

public class GuiEventGenerator {

    private final List<MenuGuiListener> menuGuiListenerList = new ArrayList<>();

    public void addGuiListener(MenuGuiListener menuGuiListener){
        menuGuiListenerList.add(menuGuiListener);
    }

    public void removeListener(MenuGuiListener menuGuiListener){
        menuGuiListenerList.remove(menuGuiListener);
    }

    public void clickEvent(double mouseX, double mouseY, int mouseKey){
        for (MenuGuiListener menuGuiListener : menuGuiListenerList){
            menuGuiListener.onClick(mouseX, mouseY, mouseKey);
        }
    }

}
