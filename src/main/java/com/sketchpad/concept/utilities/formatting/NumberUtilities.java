package com.sketchpad.concept.utilities.formatting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtilities {
    public static String addCommas(BigDecimal b, boolean integer) {
        DecimalFormat format = new DecimalFormat("#,##0.0");
        BigDecimal a = b.setScale(1, RoundingMode.DOWN);
        if (a.intValue() == a.doubleValue() || integer) format = new DecimalFormat("#,###");
        return format.format(a.doubleValue());
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
