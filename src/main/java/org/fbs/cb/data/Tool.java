package org.fbs.cb.data;

import java.util.Collections;
import java.util.List;

public class Tool {

    public Tool(float strengthPercent, PickaxeLevel pickaxeLevel, List<EnchantmentReduced> enchantmentReducedList, int inventoryId){
        this.strengthPercent = strengthPercent;
        this.pickaxeLevel = pickaxeLevel;
        this.enchantmentReducedList = Collections.unmodifiableList(enchantmentReducedList);
        this.inventoryId = inventoryId;
    }

    private final float strengthPercent;
    private final int inventoryId;
    private final PickaxeLevel pickaxeLevel;
    private final List<EnchantmentReduced> enchantmentReducedList;

    public float getStrengthPercent() {
        return strengthPercent;
    }

    public PickaxeLevel getPickaxeLevel() {
        return pickaxeLevel;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public List<EnchantmentReduced> getEnchantmentReducedList() {
        return enchantmentReducedList;
    }

    @Override
    public String toString() {
        StringBuilder array = new StringBuilder();
        for (EnchantmentReduced enchantmentReduced : enchantmentReducedList){
            array.append(enchantmentReduced.enchantment()).append(": ").append(enchantmentReduced.level()).append("\n");
        }
        return String.format(
                """
                strengthPercent:%s
                pickaxeLevel:%s
                inventoryId:%s
                enchantmentReducedList:%s
                """, strengthPercent, pickaxeLevel, inventoryId, array);
    }
}
