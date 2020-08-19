package edu.youzg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单位 转换器
 */
public class UnitConverter {

    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfDateTime =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final int TIME = 0;
    public static final int DATE = 1;
    public static final int DATE_TIME = 2;

    public UnitConverter() {
    }

    public static String getCurrentTime() {
        return getCurrentTime(TIME);
    }

    public static String getCurrentTime(int type) {
        String result = null;

        Date now = new Date(System.currentTimeMillis());
        switch (type) {
            case TIME:
                result = sdfTime.format(now);
                break;
            case DATE:
                result = sdfDate.format(now);
                break;
            case DATE_TIME:
                result = sdfDateTime.format(now);
                break;
            default:
                break;
        }

        return result;
    }

    public static final int CHAR_CN = 1;
    public static final int CHAR_EN = 0;
    private static final String[] charTypes = {"B", "字节"};
    private static final long[] level = {
            1L << 10,
            1L << 20,
            1L << 30,
            1L << 40,
            1L << 50,
    };
    private static final char[] un = {
            'K',
            'M',
            'G',
            'T',
            'P',
    };

    public static String sizeToByte(long size) {
        return sizeToByte(size, CHAR_EN);
    }

    public static String sizeToByte(long size, int charType) {
        String type = charTypes[charType];

        if (size < 0L) {
            return "非法长度值！";
        }

        if (size < level[0]) {
            return size + type;
        }

        StringBuffer res = new StringBuffer();

        if (size < level[1]) {
            return toBytes(size, 0, res, type);
        }
        if (size < level[2]) {
            return toBytes(size, 1, res, type);
        }
        if (size < level[3]) {
            return toBytes(size, 2, res, type);
        }

        return toBytes(size, 3, res, type);
    }

    private static String toBytes(long size, int index, StringBuffer res, String type) {
        double tmp = (double) size / (double) level[index];
        String s = String.valueOf(tmp) + "00";
        int dotIndex = s.indexOf('.');
        if (dotIndex == -1) {
            res.append(s);
        } else {
            res.append(s.substring(0, dotIndex + 3));
        }
        res.append(un[index]).append(type);
        return res.toString();
    }

}
