package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.fbs.cb.exception.GuiDrawException;

import java.util.ArrayList;
import java.util.List;

public class GuiDropDownList extends ActionGuiElement{

    private final List<Component> categories = new ArrayList<>();
    private final List<ActionGuiElement> actionGuiElementList = new ArrayList<>();
    private final List<List<Integer>> categoryList = new ArrayList<>();

    public void setCategoryForGuiElement(Component category, List<ActionGuiElement> actionGuiElementList){
        categories.add(category);
        this.actionGuiElementList.addAll(actionGuiElementList);
        List<Integer> indexes = new ArrayList<>();
        for (ActionGuiElement actionGuiElement: actionGuiElementList){
            indexes.add(this.actionGuiElementList.indexOf(actionGuiElement));
        }
        categoryList.add(indexes);
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {

    }

}
