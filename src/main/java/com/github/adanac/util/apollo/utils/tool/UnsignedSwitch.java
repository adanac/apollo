package com.github.adanac.util.apollo.utils.tool;

/**
 * @author adanac
 * @version 2014-8-21
 */
public class UnsignedSwitch {

    public static long UintToLong(int x) {
        return x & 0xFFFFFFFF;
    }

    public static int UShortToInt(short x) {
        return x & 0xFFFF;
    }

    public static int LongToUint(long x) {
        return (int) (x & 0xFFFFFFFF);
    }

    public static short IntToUshort(int x) {
        return (short) (x & 0xFFFF);
    }
}