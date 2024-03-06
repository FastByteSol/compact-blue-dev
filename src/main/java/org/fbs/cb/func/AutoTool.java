package org.fbs.cb.func;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.fbs.cb.data.*;
import org.fbs.cb.util.TagKeyHanding;

import java.util.ArrayList;
import java.util.List;

import static org.fbs.cb.util.LookingAt.takeAtLook;
import static org.fbs.cb.util.StringHelper.*;

public class AutoTool {

    private AutoTool(){}

    public static void equip(Minecraft minecraft){
        try {
            BlockState blockState = takeAtLook();
            List<TagKey<Block>> list = blockState.getTags().toList();
            LocalPlayer player = minecraft.player;

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

                    PickaxeLevel pickaxeLevel = null;
                    for (int j = 0; j < enchantmentsSize; j++) {
                        Tag enchantmentTag = enchantments.get(j);

                        Enchantment enchantment;

                        String enchnt = getBetween(enchantmentTag.getAsString(), '"');
                        int enchntLevel = Integer.parseInt(getLastSymbol(getBetween(enchantmentTag.getAsString(), "lvl:", "s")));

                        enchantment = switch (enchnt) {
                            case "minecraft:efficiency" -> Enchantment.EFFICIENCY;
                            case "minecraft:fortune" -> Enchantment.FORTUNE;
                            case "minecraft:strength" -> Enchantment.STRENGTH;
                            case "minecraft:silk_touch" -> Enchantment.SILK_TOUCH;
                            default -> null;
                        };

                        if (contain(itemStack.toString(), "stone") || contain(itemStack.toString(), "golden")) pickaxeLevel = PickaxeLevel.STONE;
                        else if (contain(itemStack.toString(), "iron")) pickaxeLevel = PickaxeLevel.IRON;
                        else if (contain(itemStack.toString(), "diamond") || contain(itemStack.toString(), "netherite")) pickaxeLevel = PickaxeLevel.DIAMOND;
                        else pickaxeLevel = PickaxeLevel.NONE;

                        enchantmentReducedList.add(new EnchantmentReduced(enchantment, enchntLevel));
                    }
                    Tool tool = new Tool(((float) itemStack.getDamageValue() / itemStack.getMaxDamage()), pickaxeLevel, enchantmentReducedList, i);

                    player.sendSystemMessage(Component.literal(tool.toString()));

                }

            }



            //player.sendSystemMessage(Component.literal(handlingResult.toString()));

        }
        catch (Exception e){
            minecraft.player.sendSystemMessage(Component.literal(e.getMessage()));
        }
    }

}
