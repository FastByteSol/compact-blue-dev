package org.fbs.cb.service;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.fbs.cb.data.*;
import org.fbs.cb.util.TagKeyHanding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fbs.cb.util.LookingAt.takeAtLook;

public class AutoTool {

    private AutoTool(){}

    public static void equip(Minecraft minecraft){
        try {
            BlockState blockState = takeAtLook();
            List<TagKey<Block>> list = blockState.getTags().toList();
            LocalPlayer player = Minecraft.getInstance().player;

            TagKeyHandingResult handlingResult = TagKeyHanding.handingResult(list);

            ItemStack itemInHand = player.getMainHandItem();
            List<Tool> toolList = new ArrayList<>();

            int inventorySize = player.getInventory().getContainerSize();

            for (int i = 0; i < inventorySize; i++) {

                ItemStack itemStack = player.getInventory().getItem(i);
                player.sendSystemMessage(Component.literal(itemStack.toString()));
                if (contain(itemStack.toString(), "pickaxe") ||
                        contain(itemStack.toString(), "hou")||
                        contain(itemStack.toString(), "axe")||
                        contain(itemStack.toString(), "shovel")) {


                    //player.sendSystemMessage(Component.literal(itemStack.getItem().getName(itemStack).getString()));
                    int enchantmentsSize = itemStack.getEnchantmentTags().size();

                    ListTag enchantments = itemStack.getEnchantmentTags();
                    List<EnchantmentReduced> enchantmentReducedList = new ArrayList<>();

                    for (int j = 0; j < enchantmentsSize; j++) {
                        Tag enchantmentTag = enchantments.get(j);

                        Enchantment enchantment;

                        String enchnt = getBetween(enchantmentTag.getAsString(), '"');
                        int enchntLevel = Integer.parseInt(getLastSymbol(getBetween(enchantmentTag.getAsString(), "lvl:", "s")));

                        enchantment = switch (enchnt) {
                            case "minecraft:efficiency" -> Enchantment.EFFICIENCY;
                            case "minecraft:fortune" -> Enchantment.FORTUNE;
                            case "minecraft:silk_touch" -> Enchantment.SILK_TOUCH;
                            default -> null;
                        };

                        enchantmentReducedList.add(new EnchantmentReduced(enchantment, enchntLevel));
                    }
                    Tool tool = new Tool(((float) itemStack.getDamageValue() / itemStack.getMaxDamage()), handlingResult.getPickaxeLevel(), enchantmentReducedList, i);

                    player.sendSystemMessage(Component.literal(tool.toString()));

                }

            }



            //player.sendSystemMessage(Component.literal(handlingResult.toString()));

        }
        catch (Exception e){
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

    private static String getLastSymbol(String string){
        try {
            return String.valueOf(string.toCharArray()[string.toCharArray().length-1]);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return "";
    }

    private static String getBetween(String string, char symbol){
        StringBuilder str = new StringBuilder();
        boolean reading = false;
        for (int i = 0; i < string.toCharArray().length; i++) {
            if (string.toCharArray()[i] == symbol){
                if (reading){
                    break;
                }
                reading = true;
                continue;
            }
            if (reading){
                str.append(string.toCharArray()[i]);
            }
        }
        return str.toString();
    }

    public static String getBetween(String string, String firstPart, String secondPart) {
        StringBuilder str = new StringBuilder();
        boolean reading = false;

        for (int i = 0; i < string.toCharArray().length; i++) {
            try {
                if (reading){
                    StringBuilder stringBuilder = new StringBuilder();

                    if (string.toCharArray()[i] == secondPart.toCharArray()[0]) {
                        stringBuilder.append(string.toCharArray()[i]);
                        int j = 1;
                        while (j < secondPart.toCharArray().length) {
                            if (string.toCharArray()[i + j] == secondPart.toCharArray()[j]) {
                                j++;
                                stringBuilder.append(string.toCharArray()[i + j]);
                            }
                            else {
                                j = 0;
                                str.append(stringBuilder);
                                break;
                            }
                        }
                        if (j == secondPart.toCharArray().length) {
                            reading = false;
                            break;
                        }
                    }
                    else {
                        str.append(string.toCharArray()[i]);
                    }

                }
                else if (string.toCharArray()[i] == firstPart.toCharArray()[0]) {
                    int j = 1;
                    while (j < firstPart.toCharArray().length) {
                        if (string.toCharArray()[i + j] == firstPart.toCharArray()[j]){
                            j ++;
                        }
                        else {
                            j = 0;
                            break;
                        }
                    }
                    if (j == firstPart.toCharArray().length){
                        reading = true;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    private static boolean contain(String string, String str){
        boolean allOk = false;
        for (int i = 0; i < string.toCharArray().length; i++) {
            int j = 1;
            if (str.toCharArray()[0] == string.toCharArray()[i]){
                while (j < str.toCharArray().length){
                    if (str.toCharArray()[j] == string.toCharArray()[i+j]){
                        j ++;
                        allOk = true;
                    }
                    else {
                        allOk = false;
                        break;
                    }
                }
                if (allOk){
                    return true;
                }
            }
        }
        return false;
    }

}
