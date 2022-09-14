package org.abhishek.om.util;

/**
 * Created by sca820 on 03 sep., 2022
 */
public class Utils {
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public static String uniqueEmailAddress() {
//        return "teres" + RandomStringUtils.randomAlphabetic((8)) + "@teliacompany.com";
//    }
}
