package Part2;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.DocumentFilter;

public class Filter extends DocumentFilter{
    public String lettersOnly (String s) {
        ArrayList<Character> charList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (String.valueOf(c).matches("[a-zA-Z]")) {
                charList.add(c);
            }

        }
        return charList.toString();
    }

    // public static char oneCharOnly (String s) {
    //     if (s.length() != 1) {
    //         throw new IllegalArgumentException("Input must be a single character");
    //     }
    //     return s.charAt(0);
    // }

    public static String firstSymbol (String s) {
        if (s == null || s.length() == 0)  {
            JOptionPane.showMessageDialog(null, "Your input is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // the logic of this method is because character is one code point, but some emoji takes two so we need to get the amount of cp then use it to get the first
        int codepoint = s.codePointAt(0);
        int charCount = Character.charCount(codepoint);
        return s.substring(0, charCount);
    }
    
    public static int intOnly (String s) {
        return Integer.parseInt(s);
    }

    public static double doubleOnly (String s) {
        return Double.parseDouble(s);
    }

    public static <T extends Number> T numbersOnly(String s) {
        if (s.matches("[0-9]+")) {
            return (T) Integer.valueOf(s);
        } else if (s.matches("[0-9]+\\.[0-9]+")) {
            return (T) Double.valueOf(s);
        } else {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    public static <T extends Number> T numbersRangeOnly(T t, T max, T min) {
        if (t.doubleValue() > min.doubleValue() && t.doubleValue() < max.doubleValue()) {
            return t;
        } else {
            throw new IllegalArgumentException("Invalid number range");
        }
    }
}
