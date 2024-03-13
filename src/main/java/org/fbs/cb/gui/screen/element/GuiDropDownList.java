package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.event.ClientEvents;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.Category;
import org.fbs.cb.gui.GuiColor;
import org.fbs.cb.util.Math;

import java.util.ArrayList;
import java.util.List;

public class GuiDropDownList extends ActionGuiElement {

    public GuiDropDownList(Vec2 firstPoint, int side, GuiColor color){
        this.firstPoint = firstPoint;
        this.side = side;
        this.guiColor = color;
        setActive(true);
    }

    private final List<Category> categories = new ArrayList<>();
    private Vec2 firstPoint;
    private int side;
    private GuiColor guiColor;

    public void addCategory(Category category){
        categories.add(category);
    }

    public Vec2 getFirstPoint() {
        return firstPoint;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public void setDrawn(boolean isDrawn) {

    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {
        GuiRectanglePlain background = new GuiRectanglePlain();

        double maxCategoryWidth = 0;
        for (Category category: categories){
            if (category.MAX_WIDTH() > maxCategoryWidth){
                maxCategoryWidth = category.MAX_WIDTH();
            }
        }

        int elements = 0;
        for (Category category: categories){
            elements += category.actionGuiElementList().size();
        }
        double height = (
                        (categories.size() * Math.normalize(0, ClientEvents.MENU_SCREEN.HEIGHT, 10))
                        +
                        (elements * Math.normalize(0, ClientEvents.MENU_SCREEN.HEIGHT, 20))
        );

        height = java.lang.Math.min(firstPoint.y + height, ClientEvents.MENU_SCREEN.HEIGHT);
        maxCategoryWidth = java.lang.Math.min(firstPoint.x + maxCategoryWidth, ClientEvents.MENU_SCREEN.WIDTH);

        background.setCoordinates(firstPoint, new Vec2((float) (firstPoint.x + maxCategoryWidth), (float) height));


        isDrawn = true;
    }

}
