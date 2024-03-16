package org.fbs.cb.gui;

public class Scroller {

    public Scroller(double min, double max){
        this.max = max;
        this.min = min;
    }

    private final double min, max;

    private double scale = 10;

    private double progress = 0;

    public double getProgress() {
        return progress;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }
    public void add(double value){
        progress = Math.min(max, progress + value);
    }
    public void subtract(double value){
        progress = Math.max(min, progress - value);
    }

}
