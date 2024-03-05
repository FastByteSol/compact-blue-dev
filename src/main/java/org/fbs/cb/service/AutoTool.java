package org.fbs.cb.service;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.fbs.cb.data.PickaxeLevel;
import org.fbs.cb.data.TagKeyHandingResult;

import java.util.ArrayList;
import java.util.List;

import static org.fbs.cb.util.LookingAt.takeAtLook;

public class AutoTool {

    private AutoTool(){}

    public static void equip(Minecraft minecraft){
        try {
            BlockState blockState = takeAtLook();
            List<TagKey<Block>> list = blockState.getTags().toList();
            String tool = "none";
            PickaxeLevel pickaxeLevel = PickaxeLevel.NONE;
            boolean isOre = false;
            boolean isCrumbly = false;
            boolean inNetherrack = false;

            for (TagKey<Block> tagKey : list){
                switch (tagKey.location().toLanguageKey()){
                    case "minecraft.mineable/pickaxe":{
                        tool = "pickaxe";
                        break;
                    }
                    case "minecraft.mineable/axe":{
                        tool = "axe";
                        break;
                    }
                    case "minecraft.mineable/shovel":{
                        tool = "shovel";
                        break;
                    }
                    case "minecraft.mineable/hou":{
                        tool = "hou";
                        break;
                    }
                    case "forge.ores":{
                        isOre = true;
                        break;
                    }
                    case "forge.ores/coal":
                    case "forge.ores/copper":
                    case "forge.ores/redstone":
                    case "forge.ores/lapis":
                    case "forge.ores/emerald":
                    case "forge.ores/diamond":{
                        isCrumbly = true;
                        break;
                    }
                    case "forge.ores_in_ground/netherrack":{
                        inNetherrack = true;
                        break;
                    }
                    case "minecraft.needs_iron_tool":{
                        pickaxeLevel = PickaxeLevel.IRON;
                        break;
                    }
                    case "minecraft.needs_stone_tool":{
                        pickaxeLevel = PickaxeLevel.STONE;
                        break;
                    }
                    case "minecraft.needs_diamond_tool":{
                        pickaxeLevel = PickaxeLevel.DIAMOND;
                        break;
                    }
                }
                if (inNetherrack){
                    switch (tagKey.location().toLanguageKey()){
                        case "forge.ores/gold":
                        case "forge.ores/quartz":{
                            isCrumbly = true;
                            break;
                        }
                    }
                }
            }
            TagKeyHandingResult handlingResult = new TagKeyHandingResult(tool, pickaxeLevel, isOre, isCrumbly);
            Inventory inventory = Minecraft.getInstance().player.getInventory();
            if (handlingResult.getPickaxeLevel() != PickaxeLevel.NONE) {
                if (handlingResult.getPickaxeLevel() == PickaxeLevel.STONE) {
                    ItemStack itemInHand = Minecraft.getInstance().player.getMainHandItem();
                    List<ItemStack> neededTools = new ArrayList<>();
                }
            }
        }
        catch (Exception e){
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

}
