package org.fbs.cb.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.gui.Scroller;

public class ScrollerDrawingThread extends Thread{

    private final GuiGraphics guiGraphics;
    private final Scroller scroller;
    private Vec2 mouse;
    private final Vec2 firstPoint, width_height, finalWidth_Height;

    public ScrollerDrawingThread(GuiGraphics guiGraphics, Scroller scroller, Vec2 firstPoint, Vec2 width_height, Vec2 finalWidth_Height) {
        this.guiGraphics = guiGraphics;
        this.scroller = scroller;
        this.firstPoint = firstPoint;
        this.width_height = width_height;
        this.finalWidth_Height = finalWidth_Height;
    }

    public Vec2 getFirstPoint() {
        return firstPoint;
    }

    public Vec2 getWidth_height() {
        return width_height;
    }

    public Vec2 getFinalWidth_Height() {
        return finalWidth_Height;
    }

    public GuiGraphics getGuiGraphics() {
        return guiGraphics;
    }

    public Scroller getScroller() {
        return scroller;
    }

    public Vec2 getMouse() {
        return mouse;
    }

    public void setMouse(Vec2 mouse) {
        this.mouse = mouse;
    }

    public int getMouseKey() {
        return mouseKey;
    }

    public void setMouseKey(int mouseKey) {
        this.mouseKey = mouseKey;
    }

    private int mouseKey;

    public void draw(){}

}
