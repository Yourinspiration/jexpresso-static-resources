package de.yourinspiration.jexpresso.staticresources.resource;

import java.io.IOException;

/* ------------------------------------------------------------ */
/**
 * TYPE Utilities. Provides various static utiltiy methods for manipulating
 * types and their string representations.
 *
 * @since Jetty 4.1
 */
public class TypeUtil {
    public static final Class<?>[] NO_ARGS = new Class[] {};
    public static int CR = '\015';
    public static int LF = '\012';

    /* ------------------------------------------------------------ */
    /**
     * Parse an int from a substring. Negative numbers are not handled.
     * 
     * @param s
     *            String
     * @param offset
     *            Offset within string
     * @param length
     *            Length of integer or -1 for remainder of string
     * @param base
     *            base of the integer
     * @return the parsed integer
     * @throws NumberFormatException
     *             if the string cannot be parsed
     */
    public static int parseInt(String s, int offset, int length, int base) throws NumberFormatException {
        int value = 0;

        // TODO NEED

        if (length < 0)
            length = s.length() - offset;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(offset + i);

            int digit = convertHexDigit(c);
            if (digit < 0 || digit >= base)
                throw new NumberFormatException(s.substring(offset, offset + length));
            value = value * base + digit;
        }
        return value;
    }

    /* ------------------------------------------------------------ */
    /**
     * Parse an int from a byte array of ascii characters. Negative numbers are
     * not handled.
     * 
     * @param b
     *            byte array
     * @param offset
     *            Offset within string
     * @param length
     *            Length of integer or -1 for remainder of string
     * @param base
     *            base of the integer
     * @return the parsed integer
     * @throws NumberFormatException
     *             if the array cannot be parsed into an integer
     */
    public static int parseInt(byte[] b, int offset, int length, int base) throws NumberFormatException {
        int value = 0;

        // TODO NEED

        if (length < 0)
            length = b.length - offset;

        for (int i = 0; i < length; i++) {
            char c = (char) (0xff & b[offset + i]);

            int digit = c - '0';
            if (digit < 0 || digit >= base || digit >= 10) {
                digit = 10 + c - 'A';
                if (digit < 10 || digit >= base)
                    digit = 10 + c - 'a';
            }
            if (digit < 0 || digit >= base)
                throw new NumberFormatException(new String(b, offset, length));
            value = value * base + digit;
        }
        return value;
    }

    /* ------------------------------------------------------------ */
    /**
     * @param c
     *            An ASCII encoded character 0-9 a-f A-F
     * @return The byte value of the character 0-16.
     */
    public static byte convertHexDigit(byte c) {

        // TODO need QotedToken...
        byte b = (byte) ((c & 0x1f) + ((c >> 6) * 0x19) - 0x10);
        if (b < 0 || b > 15)
            throw new IllegalArgumentException("!hex " + c);
        return b;
    }

    /* ------------------------------------------------------------ */
    /**
     * @param c
     *            An ASCII encoded character 0-9 a-f A-F
     * @return The byte value of the character 0-16.
     */
    public static int convertHexDigit(int c) {
        // TODO NEED
        int d = ((c & 0x1f) + ((c >> 6) * 0x19) - 0x10);
        if (d < 0 || d > 15)
            throw new NumberFormatException("!hex " + c);
        return d;
    }

    /* ------------------------------------------------------------ */
    public static void toHex(byte b, Appendable buf) {
        // TODO NEED
        try {
            int d = 0xf & ((0xF0 & b) >> 4);
            buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
            d = 0xf & b;
            buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------------------------------------------------------------ */
    public static void toHex(int value, Appendable buf) throws IOException {
        // TODO NEED
        int d = 0xf & ((0xF0000000 & value) >> 28);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x0F000000 & value) >> 24);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x00F00000 & value) >> 20);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x000F0000 & value) >> 16);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x0000F000 & value) >> 12);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x00000F00 & value) >> 8);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & ((0x000000F0 & value) >> 4);
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));
        d = 0xf & value;
        buf.append((char) ((d > 9 ? ('A' - 10) : '0') + d));

        Integer.toString(0, 36);
    }

}
