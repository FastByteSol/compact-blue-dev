package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.Category;

import java.util.ArrayList;
import java.util.List;

public class GuiDropDownList extends ActionGuiElement {

    private final List<Category> categories = new ArrayList<>();

    public void addCategory(Category category){
        categories.add(category);
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {

    }

}
