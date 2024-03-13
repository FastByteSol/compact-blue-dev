package org.fbs.cb.gui.screen.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.Category;
import org.fbs.cb.gui.GuiColor;

import java.util.ArrayList;
import java.util.List;

public class GuiDropDownList extends ActionGuiElement {

    private final List<Category> categories = new ArrayList<>();
    private Vec2 firstPoint;
    private Vec2 secondPoint;
    private int side;
    private GuiColor guiColor;

    public void addCategory(Category category){
        categories.add(category);
    }

    public void setCoordinates(Vec2 firstPoint){
        this.firstPoint = firstPoint;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public Vec2 getFirstPoint() {
        return firstPoint;
    }

    public void setColor(GuiColor guiColor){
        this.guiColor = guiColor;
    }

    @Override
    public void setActive(boolean isActive) {

    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {
        try {
            double SCREEN_WIDTH = Minecraft.getInstance().screen.getRectangle().width();
            double SCREEN_HEIGHT = Minecraft.getInstance().screen.getRectangle().height();
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }

        GuiRectanglePlain background = new GuiRectanglePlain();
//        double height = (categories.size() * );
//        background.setCoordinates(firstPoint, );

    }

}
