package wukeslyOS.trcdemo.parser;

import java.nio.charset.Charset;

public class EncodeParser {
	
	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String UnicodeBig = "UnicodeBig";
	public static final String UnicodeLittle = "UnicodeLittle";
	public static final String GB2312 = "GB2312";

	/**
     * is string utf8
     */
    public static boolean isUTF8(byte[] rawtext) {
        final int READ_MAX_LENG = 512;
        int score = 0;
        int i, rawtextlen = 0;
        int goodbytes = 0, asciibytes = 0;
        // Maybe also use UTF8 Byte Order Mark: EF BB BF
        // Check to see if characters fit into acceptable ranges
        rawtextlen = rawtext.length;
        if (rawtextlen > READ_MAX_LENG) {
            rawtextlen = READ_MAX_LENG;
        }
        for (i = 0; i < rawtextlen; i++) {
            if ((rawtext[i] & (byte) 0x7F) == rawtext[i]) {
                // the high bit=0 for acii
                asciibytes++;
                // Ignore ASCII, can throw off count
            } else if (-64 <= rawtext[i] && rawtext[i] <= -33// -0x40~-0x21
                    && // Two bytes
                    i + 1 < rawtextlen && -128 <= rawtext[i + 1] && rawtext[i + 1] <= -65) {
                goodbytes += 2;
                i++;
            } else if (-32 <= rawtext[i] && rawtext[i] <= -17
                    && // Three bytes
                    i + 2 < rawtextlen && -128 <= rawtext[i + 1] && rawtext[i + 1] <= -65 && -128 <= rawtext[i + 2]
                    && rawtext[i + 2] <= -65) {
                goodbytes += 3;
                i += 2;
            }
        }
        if (asciibytes == rawtextlen) {

            return false;
        }
        score = 100 * goodbytes / (rawtextlen - asciibytes);
        // If not above 98, reduce to zero to prevent coincidental matches
        // Allows for some (few) bad formed sequences
        if (score > 98) {
            return true;
        } else if (score > 95 && goodbytes > 30) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 获取byte[]对应文本的编码格式
     * @param sub 待判断的byte[]
     * @return 默认返回{@link #GB2312},不然，根据特征，返回{@link EncodeParser#UTF_8}、{@link #UnicodeBig}或{@link #UnicodeLittle}
     */
    public static String getDecodeType(byte[] sub) {
        int reLen = 264;
        int mFileLen = sub.length;
        if (reLen > mFileLen) {
            reLen = mFileLen;
        }

        if (isUTF8(sub)) {
            return UTF_8;
        } else if (isUnicodeBig(sub)) {
            return UnicodeBig;
        } else if (isUnicodeLittle(sub)) {
            return UnicodeLittle;
        } else {
            return GB2312;
        }
    }

    public static boolean isUnicodeBig(byte[] rawText) {
        if (rawText[0] == -2 && rawText[1] == -1) {
            return true;
        }
        return false;
    }

    public static boolean isUnicodeLittle(byte[] rawText) {
        if (rawText[0] == -1 && rawText[1] == -2) {
            return true;
        }
        return false;
    }
}
