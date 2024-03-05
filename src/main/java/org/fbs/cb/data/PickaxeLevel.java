package org.fbs.cb.data;

public enum PickaxeLevel {
    NONE(0),
    STONE(1),
    IRON(2),
    DIAMOND(3);

    PickaxeLevel(int level) {
        this.level = level;
    }
    private final int level;

    public int getLevel() {
        return level;
    }
}
