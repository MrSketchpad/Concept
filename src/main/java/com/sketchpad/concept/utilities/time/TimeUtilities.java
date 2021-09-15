package com.sketchpad.concept.utilities.time;

public class TimeUtilities {
    public static int secondsPassed(long oldTime, long newTime) {
        return (int) ((oldTime - newTime)/-20);
    }
}
