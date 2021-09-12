package com.sketchpad.concept.utilities.formatting;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Number {
    public static String addCommas(BigDecimal b) {
        DecimalFormat format = new DecimalFormat("###,###,###,###,###");
        return format.format(b.longValue()).replace(" ", ",");
    }
    public static String toRomanNumeral(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
}
