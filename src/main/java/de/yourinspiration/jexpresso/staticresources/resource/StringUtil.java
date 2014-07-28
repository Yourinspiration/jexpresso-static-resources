package de.yourinspiration.jexpresso.staticresources.resource;

public class StringUtil {

    public static String replace(String s, String sub, String with) {
        int c = 0;
        int i = s.indexOf(sub, c);
        if (i == -1)
            return s;

        StringBuilder buf = new StringBuilder(s.length() + with.length());

        do {
            buf.append(s.substring(c, i));
            buf.append(with);
            c = i + sub.length();
        } while ((i = s.indexOf(sub, c)) != -1);

        if (c < s.length())
            buf.append(s.substring(c, s.length()));

        return buf.toString();

    }

    public static void append(StringBuilder buf, byte b, int base) {
        int bi = 0xff & b;
        int c = '0' + (bi / base) % base;
        if (c > '9')
            c = 'a' + (c - '0' - 10);
        buf.append((char) c);
        c = '0' + bi % base;
        if (c > '9')
            c = 'a' + (c - '0' - 10);
        buf.append((char) c);
    }

}
