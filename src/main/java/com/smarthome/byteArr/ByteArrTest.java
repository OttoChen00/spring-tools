package com.smarthome.byteArr;

import java.util.Arrays;

/**
 * @Description: 合并多个byte数组
 * @Author: bing.chen
 * @Date: 2018/6/27
 */
public class ByteArrTest {

    public static void main(String[] args){
        byte[] bytes1 = {1, 2, 3};
        byte[] bytes2 = {1, 2, 4};
        byte[] byteMergerAll = byteMergerAll(bytes1, bytes2);
        byte[] unitByteArray = unitByteArray(bytes1, bytes2);
        System.out.println(System.currentTimeMillis());
        System.out.println(Arrays.toString(byteMergerAll));
        System.out.println(System.currentTimeMillis());
        System.out.println(Arrays.toString(unitByteArray));
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 合并多个byte数组
     */
    private static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    /**
     * 合并两个byte数组
     */
    public static byte[] unitByteArray(byte[] byte1,byte[] byte2){
        byte[] unitByte = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, unitByte, 0, byte1.length);
        System.arraycopy(byte2, 0, unitByte, byte1.length, byte2.length);
        return unitByte;
    }
}
