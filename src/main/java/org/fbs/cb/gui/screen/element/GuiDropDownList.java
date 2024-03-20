package org.fbs.cb.gui.screen.element;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.event.ClientEvents;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.Category;
import org.fbs.cb.gui.GuiColor;
import org.fbs.cb.gui.Scroller;
import org.fbs.cb.util.Math;
import org.fbs.cb.util.ScrollerDrawingThread;

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
    private final GuiGraphics guiGraphics = ClientEvents.MENU_SCREEN.getGUI_GRAPHICS();
    private Scroller verticalScroller, horizontalScroller;
    private Vec2 firstPoint = null;
    private Vec2 secondPoint;
    private double maxWidth, height, finalWidth, finalHeight;
    private int side;
    private final GuiColor guiColor;
    private final GuiRectanglePlain background = new GuiRectanglePlain();

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

    public void setHorizontalScroller(Scroller horizontalScroller) {
        this.horizontalScroller = horizontalScroller;
    }

    public void setVerticalScroller(Scroller verticalScroller) {
        this.verticalScroller = verticalScroller;
    }

    @Override
    public void draw(GuiGraphics guiGraphics) throws GuiDrawException {

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

        final double finalHeight = java.lang.Math.min(firstPoint.y + height, ClientEvents.MENU_SCREEN.HEIGHT);
        final double finalWidth = java.lang.Math.min(firstPoint.x + maxCategoryWidth, ClientEvents.MENU_SCREEN.WIDTH);

        this.height = height;
        maxWidth = maxCategoryWidth;
        this.finalHeight = finalHeight;
        this.finalWidth = finalWidth;

        secondPoint = new Vec2((float) (firstPoint.x + finalWidth), (float) finalHeight);

        background.setCoordinates(firstPoint, secondPoint);
        background.setColor(guiColor);
        background.draw(guiGraphics);

        isDrawn = true;
    }

    @Override
    public boolean onMouseMove(double mouseX, double mouseY) {
        if (isDrawn && isActive) {
            if (mouseX >= firstPoint.x && mouseX <= secondPoint.x
                    && mouseY >= firstPoint.y && mouseY <= secondPoint.y) {
                if (maxWidth != finalWidth){

                }
                return true;
            }
        }
        return false;
    }

    private final ScrollerDrawingThread horizontalThread = new ScrollerDrawingThread(guiGraphics, new Scroller(0, 1), new Vec2(firstPoint.x, firstPoint.y), new Vec2((float) finalWidth, (float) finalHeight), new Vec2((float) maxWidth, (float) height)){
        @Override
        public void draw() {

        }

        @Override
        public void run() {
            draw();
        }
    };

    private final ScrollerDrawingThread verticalThread = new ScrollerDrawingThread(guiGraphics, new Scroller(0, 1), new Vec2(firstPoint.x, firstPoint.y), new Vec2((float) finalWidth, (float) finalHeight), new Vec2((float) maxWidth, (float) height)){
        @Override
        public void draw() {

        }

        @Override
        public void run() {
            draw();
        }
    };

}
