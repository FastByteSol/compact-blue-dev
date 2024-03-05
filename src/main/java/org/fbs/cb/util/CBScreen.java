package org.fbs.cb.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontTexture;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.fbs.cb.CB;

import java.awt.*;

public class CBScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + CB.MOD_ID + ".compact_blue_screen");
    private CBScreen cbScreen;
    private int leftPos, topPos;
    private final int cbWidth, cbHeight;
    
    public CBScreen(int width, int height) {
        super(TITLE);
        this.cbWidth = width;
        this.cbHeight = height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int p_281550_, int p_282878_, float p_282465_) {

        this.renderBackground(guiGraphics);

        guiGraphics.drawCenteredString(this.font, Component.literal("r"), p_281550_, p_282878_, 0);

        super.render(guiGraphics, p_281550_, p_282878_, p_282465_);
    }
}
