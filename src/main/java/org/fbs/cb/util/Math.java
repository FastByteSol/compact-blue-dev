package org.fbs.cb.util;

public class Math {

    private Math(){}

    public double normalize(double min, double max, double value){
        if (value > max){
            return 1;
        }
        else if (value < min){
            return 0;
        }
        else {
            double size = max - min;
            return (value - min) / size;
        }
    }

}
