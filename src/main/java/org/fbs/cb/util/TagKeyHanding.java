package org.fbs.cb.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.fbs.cb.data.PickaxeLevel;
import org.fbs.cb.data.TagKeyHandingResult;

import java.util.List;

public class TagKeyHanding {

    private TagKeyHanding(){}

    public static TagKeyHandingResult handingResult(List<TagKey<Block>> tagKeyList){

        String toolString = "none";
        PickaxeLevel pickaxeLevel = PickaxeLevel.NONE;
        boolean isOre = false;
        boolean isCrumbly = false;
        boolean inNether = false;

        for (TagKey<Block> tagKey : tagKeyList){
            switch (tagKey.location().toLanguageKey()){
                case "minecraft.mineable/pickaxe":{
                    toolString = "pickaxe";
                    break;
                }
                case "minecraft.mineable/axe":{
                    toolString = "axe";
                    break;
                }
                case "minecraft.mineable/shovel":{
                    toolString = "shovel";
                    break;
                }
                case "minecraft.mineable/hou":{
                    toolString = "hou";
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
                case "forge.ores/quartz":
                case "forge.ores/emerald":
                case "forge.ores/diamond":{
                    isCrumbly = true;
                    break;
                }
                case "forge.ores_in_ground/netherrack":
                case "minecraft/guarded_by_piglins":{
                    inNether = true;
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
            if (inNether){
                if (tagKey.location().toLanguageKey().equals("forge.ores/gold")) {
                    isCrumbly = true;
                }
            }
        }

        return new TagKeyHandingResult(toolString, pickaxeLevel, isOre, isCrumbly);

    }

}
