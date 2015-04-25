package com.durdina.personal.stringman.utf;

import java.lang.Character.UnicodeScript;

/**
 * Created by michaldurdina on 25/04/15.
 */

public class UnicodeCharsApp {

    static String STRING = "®Čau fešákη㊆ㄋ室木ㅏ五" + new String(new int[]{0x2F81A}, 0, 1);

    final int[] unicodeList;

    {

        int codePointCount = STRING.codePointCount(0, STRING.length());
        unicodeList = new int[codePointCount];
        for (int i = 0; i < codePointCount; i += 1) {
            int codePoint = STRING.codePointAt(i);
            unicodeList[i] = codePoint;
        }
    }

    public void start() {

        System.out.println(new String(unicodeList, 0, unicodeList.length));

        for (int i = 0; i < unicodeList.length; i += 1) {
            int codePoint = unicodeList[i];
            System.out.printf("%d: charCount=%d\ttoChars=%s\tHex=%5s\tBMP=%s\tSup=%s\tFamily=%-8s\tUnicode=%s\n",
                    i, Character.charCount(codePoint), new String(unicodeList, i, 1), Integer.toHexString(codePoint),
                    Character.isBmpCodePoint(codePoint), Character.isSupplementaryCodePoint(codePoint),
                    UnicodeScript.of(codePoint), Character.UnicodeBlock.of(codePoint));
        }

    }

    public static void main(String[] arg) {
        new UnicodeCharsApp().start();
    }

}