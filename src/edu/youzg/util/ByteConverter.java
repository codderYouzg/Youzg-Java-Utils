package edu.youzg.util;

/**
 * 字节 与 long、int、String 转换器
 */
public class ByteConverter {
    public static final String HEX = "0123456789ABCDEF";    // 保存 16进制 的 所有可能性组成

    public ByteConverter() {
    }

    /**
     * 将 byte类型数据 转换成 long类型数据
     * @param bytes 目标字节数组
     * @param offset 要转换的 偏移量
     * @return 转换结果
     */
    public static long bytesToLong(byte[] bytes, int offset) {
        long res = 0;

        for (int index = 7; index >= 0; index--) {  // 一个long由8个byte构成(64字节)
            res <<= 8;  // 一个byte有8位，因此我们在转换当前byte时，需要将高位左移8位
            res |= bytes[offset + index] & 0xFF;
        }
        return res;
    }

    /**
     * 将 byte类型数据 转换成 long类型数据
     * @param bytes 目标字节数组
     * @return 转换结果
     */
    public static long bytesToLong(byte[] bytes) {
        long res = 0;

        for (int index = 7; index >= 0; index--) {  // 一个long由8个byte构成(64字节)
            res <<= 8;  // 一个byte有8位，因此我们在转换当前byte时，需要将高位左移8位
            res |= bytes[index] & 0xFF;
        }

        return res;
    }

    /**
     * 将 byte类型数据 转换成 int类型数据
     * @param bytes 目标字节数组
     * @param offset 要转换的 偏移量
     * @return 转换结果
     */
    public static int bytesToInt(byte[] bytes, int offset) {
        int res = 0;

        for (int index = 3; index >= 0; index--) {  // 一个int由4个byte构成(64字节)
            res <<= 8;  // 一个byte有8位，因此我们在转换当前byte时，需要将高位左移8位
            res |= bytes[offset + index] & 0xFF;
        }

        return res;
    }

    /**
     * 将 byte类型数据 转换成 int类型数据
     *
     * @param bytes  目标字节数组
     * @return 转换结果
     */
    public static int bytesToInt(byte[] bytes) {
        int res = 0;

        for (int index = 3; index >= 0; index--) {  // 一个int由4个byte构成(64字节)
            res <<= 8;  // 一个byte有8位，因此我们在转换当前byte时，需要将高位左移8位
            res |= bytes[index] & 0xFF;
        }

        return res;
    }

    /**
     * 将 int型数值 转换为 byte数组
     * @param val int型值
     * @return 转换结果
     */
    public static byte[] intToBytes(int val) {
        byte[] res = new byte[4];   // 一个int型数据，由4个byte组成

        for (int index = 0; index < res.length; index++) {
            // 此处是按照 “高高低低”原则 进行转换的，对应了反转换的逻辑
            res[index] = (byte) ((val >> (index * 8)) & 0xFF);
        }

        return res;
    }

    /**
     * 将 int型数值 转换为 byte数组
     * @param res 结果字节数组
     * @param offset 偏移量
     * @param val int型值
     * @return 转换结果
     */
    public static byte[] intToBytes(byte[] res, int offset, int val) {
        byte intBytes[] = intToBytes(val);

        // 此处是按照 “高高低低”原则 进行转换的，对应了反转换的逻辑
        for (int index = 0; index < intBytes.length; index++) {
            res[offset + index] = intBytes[index];
        }

        return res;
    }

    /**
     * 将 long型数值 转换为 byte数组
     * @param val long型值
     * @return 转换结果
     */
    public static byte[] longToBytes(long val) {
        byte[] res = new byte[8];   // 一个int型数据，由8个byte组成

        // 此处是按照 “高高低低”原则 进行转换的，对应了反转换的逻辑
        for (int index = 0; index < res.length; index++) {
            res[index] = (byte) ((val >> (index * 8)) & 0xFF);
        }

        return res;
    }

    /**
     * 将 long型数值 转换为 byte数组
     * @param res 结果数组
     * @param offset 偏移量
     * @param val long型值
     * @return 转化结果
     */
    public static byte[] longToBytes(byte[] res, int offset, long val) {
        byte intBytes[] = longToBytes(val);

        // 此处是按照 “高高低低”原则 进行转换的，对应了反转换的逻辑
        for (int index = 0; index < intBytes.length; index++) {
            res[offset + index] = intBytes[index];
        }

        return res;
    }

    /**
     * 将 16进制格式的字符串 转换为 byte数组
     * @param str 目标字符串
     * @return 转换结果
     */
    public static byte[] stringToBinary(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len <= 0 || len % 2 != 0) {
            return null;
        }
        byte[] result = new byte[len / 2];  // 1个字符 由 2个字节 组成

        int binaryIndex = 0;
        int strIndex = 0;
        while (strIndex < len) {
            int hVal = HEX.indexOf(str.charAt(strIndex));
            int lVal = HEX.indexOf(str.charAt(strIndex + 1));

            result[binaryIndex++] = (byte) ((hVal << 4) | lVal);

            strIndex += 2;
        }

        return result;
    }

    /**
     * 将 字节数组 转换为 16进制字符串
     * @param buffer 目标字节数组
     * @param offset 偏移量
     * @param len 转换长度
     * @return 16进制字符串
     */
    public static String binaryToString(byte[] buffer, int offset, int len) {
        StringBuffer result = new StringBuffer();

        for (int index = offset; index < offset + len; index++) {
            byte val = buffer[index];
            result.append(HEX.charAt((val >> 4) & 0x0F));
            result.append(HEX.charAt(val & 0x0F));
        }

        return result.toString();
    }

}
