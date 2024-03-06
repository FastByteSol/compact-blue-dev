package org.fbs.cb.util;

public class StringHelper{

    private StringHelper(){}

    public static String getLastSymbol(String string){
        try {
            return String.valueOf(string.toCharArray()[string.toCharArray().length-1]);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return "";
    }

    public static String getBetween(String string, char symbol){
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

    public static boolean contain(String string, String str){
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
