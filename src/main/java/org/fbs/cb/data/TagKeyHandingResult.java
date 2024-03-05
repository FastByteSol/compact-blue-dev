package org.fbs.cb.data;

import org.fbs.cb.event.ClientEvents;

public class TagKeyHandingResult {

    public TagKeyHandingResult(boolean MINEABLE_BY_PICKAXE, boolean MINEABLE_BY_SHOVEL, boolean MINEABLE_BY_HOU, boolean MINEABLE_BY_AXE, PickaxeLevel pickaxeLevel, boolean isOre, boolean isCrumbly){
        this.MINEABLE_BY_PICKAXE = MINEABLE_BY_PICKAXE;
        this.MINEABLE_BY_SHOVEL = MINEABLE_BY_SHOVEL;
        this.MINEABLE_BY_HOU = MINEABLE_BY_HOU;
        this.MINEABLE_BY_AXE = MINEABLE_BY_AXE;
        this.pickaxeLevel = pickaxeLevel;
        this.isOre = isOre;
        this.isCrumbly = isCrumbly;
    }
    public TagKeyHandingResult(String tool, PickaxeLevel pickaxeLevel, boolean isOre, boolean isCrumbly){
        switch (tool){
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

}
