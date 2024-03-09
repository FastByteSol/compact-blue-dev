package org.fbs.cb.data;

import org.fbs.cb.data.auto_tool.PickaxeLevel;

public class TagKeyHandingResult {

    public TagKeyHandingResult(String handledItem, PickaxeLevel pickaxeLevel, boolean isOre, boolean isCrumbly){
        switch (handledItem){
            case "pickaxe":{
                MINEABLE_BY_PICKAXE = true;
                MINEABLE_BY_AXE = false;
                MINEABLE_BY_HOU = false;
                MINEABLE_BY_SHOVEL = false;
                break;
            }
            case "shovel":{
                MINEABLE_BY_PICKAXE = false;
                MINEABLE_BY_AXE = false;
                MINEABLE_BY_HOU = false;
                MINEABLE_BY_SHOVEL = true;
                break;
            }
            case "hou":{
                MINEABLE_BY_PICKAXE = false;
                MINEABLE_BY_AXE = false;
                MINEABLE_BY_HOU = true;
                MINEABLE_BY_SHOVEL = false;
                break;
            }
            case "axe":{
                MINEABLE_BY_PICKAXE = false;
                MINEABLE_BY_AXE = true;
                MINEABLE_BY_HOU = false;
                MINEABLE_BY_SHOVEL = false;
                break;
            }
            default:{
                MINEABLE_BY_PICKAXE = false;
                MINEABLE_BY_AXE = false;
                MINEABLE_BY_HOU = false;
                MINEABLE_BY_SHOVEL = false;
                break;
            }
        }
        this.pickaxeLevel = pickaxeLevel;
        this.isOre = isOre;
        this.isCrumbly = isCrumbly;
    }

    private final boolean MINEABLE_BY_PICKAXE;
    private final boolean MINEABLE_BY_SHOVEL;
    private final boolean MINEABLE_BY_HOU;
    private final boolean MINEABLE_BY_AXE;
    private final PickaxeLevel pickaxeLevel;
    private final boolean isOre;
    private final boolean isCrumbly;

    public boolean isMINEABLE_BY_PICKAXE() {
        return MINEABLE_BY_PICKAXE;
    }

    public boolean isMINEABLE_BY_SHOVEL() {
        return MINEABLE_BY_SHOVEL;
    }

    public boolean isMINEABLE_BY_HOU() {
        return MINEABLE_BY_HOU;
    }

    public boolean isMINEABLE_BY_AXE() {
        return MINEABLE_BY_AXE;
    }

    public PickaxeLevel getPickaxeLevel() {
        return pickaxeLevel;
    }

    public boolean isOre() {
        return isOre;
    }

    public boolean isCrumbly() {
        return isCrumbly;
    }

    @Override
    public String toString() {
        String pickaxeLevel = "NONE";
        if (this.pickaxeLevel == PickaxeLevel.STONE) pickaxeLevel = "STONE";
        else if (this.pickaxeLevel == PickaxeLevel.IRON) pickaxeLevel = "IRON";
        else if (this.pickaxeLevel == PickaxeLevel.DIAMOND) pickaxeLevel = "DIAMOND";


        String handledItem = "";
        if (MINEABLE_BY_AXE) handledItem = "AXE";
        else if (MINEABLE_BY_HOU) handledItem = "HOU";
        else if (MINEABLE_BY_PICKAXE) handledItem = "PICKAXE";
        else if (MINEABLE_BY_SHOVEL) handledItem = "SHOVEL";

        return String.format(
                """
                handledItem: %s (pickaxe level: %s)
                isOre: %s
                isCrumbly: %s
                """, handledItem, pickaxeLevel, isOre, isCrumbly);
    }
}
