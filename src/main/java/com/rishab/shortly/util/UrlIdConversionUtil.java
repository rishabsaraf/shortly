package com.rishab.shortly.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class UrlIdConversionUtil {

    private static final long BASE = 62;
    private static final BiMap<Character, Long> DICTIONARY = HashBiMap.create();

    static {
        DICTIONARY.put('0', 0l);
        DICTIONARY.put('1', 1l);
        DICTIONARY.put('2', 2l);
        DICTIONARY.put('3', 3l);
        DICTIONARY.put('4', 4l);
        DICTIONARY.put('5', 5l);
        DICTIONARY.put('6', 6l);
        DICTIONARY.put('7', 7l);
        DICTIONARY.put('8', 8l);
        DICTIONARY.put('9', 9l);
        DICTIONARY.put('a', 10l);
        DICTIONARY.put('b', 11l);
        DICTIONARY.put('c', 12l);
        DICTIONARY.put('d', 13l);
        DICTIONARY.put('e', 14l);
        DICTIONARY.put('f', 15l);
        DICTIONARY.put('g', 16l);
        DICTIONARY.put('h', 17l);
        DICTIONARY.put('i', 18l);
        DICTIONARY.put('j', 19l);
        DICTIONARY.put('k', 20l);
        DICTIONARY.put('l', 21l);
        DICTIONARY.put('m', 22l);
        DICTIONARY.put('n', 23l);
        DICTIONARY.put('o', 24l);
        DICTIONARY.put('p', 25l);
        DICTIONARY.put('q', 26l);
        DICTIONARY.put('r', 27l);
        DICTIONARY.put('s', 28l);
        DICTIONARY.put('t', 29l);
        DICTIONARY.put('u', 30l);
        DICTIONARY.put('v', 31l);
        DICTIONARY.put('w', 32l);
        DICTIONARY.put('x', 33l);
        DICTIONARY.put('y', 34l);
        DICTIONARY.put('z', 35l);
        DICTIONARY.put('A', 36l);
        DICTIONARY.put('B', 37l);
        DICTIONARY.put('C', 38l);
        DICTIONARY.put('D', 39l);
        DICTIONARY.put('E', 40l);
        DICTIONARY.put('F', 41l);
        DICTIONARY.put('G', 42l);
        DICTIONARY.put('H', 43l);
        DICTIONARY.put('I', 44l);
        DICTIONARY.put('J', 45l);
        DICTIONARY.put('K', 46l);
        DICTIONARY.put('L', 47l);
        DICTIONARY.put('M', 48l);
        DICTIONARY.put('N', 49l);
        DICTIONARY.put('O', 50l);
        DICTIONARY.put('P', 51l);
        DICTIONARY.put('Q', 52l);
        DICTIONARY.put('R', 53l);
        DICTIONARY.put('S', 54l);
        DICTIONARY.put('T', 55l);
        DICTIONARY.put('U', 56l);
        DICTIONARY.put('V', 57l);
        DICTIONARY.put('W', 58l);
        DICTIONARY.put('X', 59l);
        DICTIONARY.put('Y', 60l);
        DICTIONARY.put('Z', 61l);
    }

    public static long generate(final String input) {
        long output = 0;
        for (int i = 0; i < input.length(); i++) {
            output = output * BASE;
            char c = input.charAt(i);
            long faceValue = DICTIONARY.get(c);
            output = output + faceValue;
        }
        return output;
    }

    public static String getStringRepresentation(final long input) {
        String output = "";
        long inputBuffer = input;
        while (output.length() < 6) {
            output = DICTIONARY.inverse()
                               .get(inputBuffer % BASE) + output;
            inputBuffer = inputBuffer / BASE;
        }
        return output;
    }
}
