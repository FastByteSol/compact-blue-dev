package org.fbs.cb.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import org.fbs.cb.CB;
import org.fbs.cb.exception.GuiDrawException;
import org.fbs.cb.gui.ActionGuiElement;
import org.fbs.cb.gui.GuiColors;
import org.fbs.cb.gui.screen.element.GuiButton;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends Screen {
    private final Minecraft client;
    private Screen SCREEN;
    private GuiGraphics GUI_GRAPHICS;
    private final Screen parent;
    public int WIDTH;
    public int HEIGHT;
    private ActionGuiElement lastClicked, lastFocused, lastReleased;

    private final List<ActionGuiElement> actionGuiElementList = new ArrayList<>();

    private final GuiButton button = new GuiButton();

    public MenuScreen(Screen parent, Minecraft client) {
        super(Component.literal("Menu Screen"));
        SCREEN = parent;
        this.parent = parent;
        this.client = client;
        if (parent != null) {
            WIDTH = parent.getRectangle().width();
            HEIGHT = parent.getRectangle().height();
        }
        else {
            WIDTH = client.getWindow().getWidth();
            HEIGHT = client.getWindow().getHeight();
        }
        objectsAdding();
    }

    public Screen getSCREEN() {
        return SCREEN;
    }

    public GuiGraphics getGUI_GRAPHICS() {
        return GUI_GRAPHICS;
    }

    private void objectsAdding(){
        actionGuiElementList.add(button);
    }

    private void objectsInitialisation(){
        button.setFillColor(GuiColors.LIGHT_BLUE);
        button.setCoordinates(new Vec2((float) (WIDTH / 2), (float) (HEIGHT / 2)), new Vec2((float) ((WIDTH / 2) + 10), (float) ((HEIGHT / 2) + 15)));
        button.setText(Component.literal(WIDTH + "x" + HEIGHT), this.font, GuiColors.WHITE);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float p_282465_) {
        GUI_GRAPHICS = guiGraphics;
        objectsInitialisation();
        if (SCREEN != null) {
            WIDTH = SCREEN.getRectangle().width();
            HEIGHT = SCREEN.getRectangle().height();
        }
        else {
            WIDTH = client.getWindow().getWidth();
            HEIGHT = client.getWindow().getHeight();
        }

        try {
            for (ActionGuiElement element: actionGuiElementList){
                if (element.isActive()){
                    element.draw(guiGraphics);
                }
            }
        } catch (GuiDrawException e) {
            CB.LOGGER.info(e.getMessage());
        }

        super.render(guiGraphics, mouseX, mouseY, p_282465_);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseKey) {
        CB.LOGGER.info("Clicked " + mouseX + ":" + mouseY + ":" + mouseKey);
        boolean needSearch = true;
        try {
            if (!(lastClicked.onMouseClick(mouseX, mouseY, mouseKey))) {
                needSearch = true;
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        try {
            if (!(lastFocused.onMouseClick(mouseX, mouseY, mouseKey))) {
                needSearch = true;
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        try {
            if (needSearch){
                for (ActionGuiElement element : actionGuiElementList) {
                    if (element.isActive()) {
                        if (element.onMouseClick(mouseX, mouseY, mouseKey)) {
                            lastClicked = element;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        return super.mouseClicked(mouseX, mouseY, mouseKey);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        boolean needSearch = true;
        try {
            if (!lastFocused.onMouseMove(mouseX, mouseY)) {
                needSearch = true;
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        try {
            if (needSearch) {
                for (ActionGuiElement element : actionGuiElementList) {
                    if (element.isActive()) {
                        if (element.onMouseMove(mouseX, mouseY)) {
                            lastFocused = element;
                            CB.LOGGER.info("Focused: " + element.toString());
                        }
                    }
                }
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseKey) {
        boolean needSearch = true;
        try {
            if (!lastReleased.onMouseReleased(mouseX, mouseY, mouseKey)) {
                needSearch = true;
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        try {
            if (needSearch) {
                for (ActionGuiElement element : actionGuiElementList) {
                    if (element.isActive()) {
                        if (element.onMouseReleased(mouseX, mouseY, mouseKey)) {
                            lastReleased = element;
                            CB.LOGGER.info("Release " + mouseX + ":" + mouseY + ":" + mouseKey);
                        }
                    }
                }
            }
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        return super.mouseReleased(mouseX, mouseY, mouseKey);
    }

    @Override
    public void onClose() {
        client.setScreen(parent);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void tick() {
        SCREEN = client.screen;
        try {
            this.init();
        }
        catch (Exception e){
            CB.LOGGER.info(e.getMessage());
        }
        super.tick();
    }

}
