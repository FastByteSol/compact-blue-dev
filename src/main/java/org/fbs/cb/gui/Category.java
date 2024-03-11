package org.fbs.cb.gui;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.List;

public record Category(Component component, List<ActionGuiElement> actionGuiElementList) {

    @Contract(pure = true)
    @Override
    public @NotNull @UnmodifiableView List<ActionGuiElement> actionGuiElementList() {
        return Collections.unmodifiableList(actionGuiElementList);
    }

}
