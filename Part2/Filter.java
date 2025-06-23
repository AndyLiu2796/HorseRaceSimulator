package Part2;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.DocumentFilter;

/**
 * 
 * @author Ho Ming, Liu
 * @version 2.0
*/

/**
  * Filter class
  * This class contains some input methods for some validation
*/
public class Filter extends DocumentFilter{

    // This method allows char input only
    //
    public String lettersOnly (String s) {
        ArrayList<Character> charList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (String.valueOf(c).matches("[a-zA-Z]")) {
                charList.add(c);
            }

        }
        return charList.toString();
    } // END lettersOnly

    // This method selects the first character of the given String
    //
    public static String firstSymbol (String s) {
        if (s == null || s.length() == 0)  {
            JOptionPane.showMessageDialog(null, "Your input is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // the logic of this method is because character is one code point, but some emoji takes two so we need to get the amount of cp then use it to get the first
        int codepoint = s.codePointAt(0);
        int charCount = Character.charCount(codepoint);
        return s.substring(0, charCount);
    } // END firstSymbol
    
    // This method only allows integer input from STring
    //
    public static int intOnly (String s) {
        return Integer.parseInt(s);
    } // END intOnly

    // This method only allows double input from String
    //
    public static double doubleOnly (String s) {
        return Double.parseDouble(s);
    } // END doubleOnly

    // This method only allow numbers integer or double
    //
    public static <T extends Number> T numbersOnly(String s) {
        if (s.matches("[0-9]+")) {
            return (T) Integer.valueOf(s);
        } else if (s.matches("[0-9]+\\.[0-9]+")) {
            return (T) Double.valueOf(s);
        } else {
            throw new IllegalArgumentException("Invalid number format");
        }
    } // END numbersOnly

    // This method allows numerical input within a given range
    //
    public static <T extends Number> T numbersRangeOnly(T t, T max, T min) {
        if (t.doubleValue() > min.doubleValue() && t.doubleValue() < max.doubleValue()) {
            return t;
        } else {
            throw new IllegalArgumentException("Invalid number range");
        }
    } // END numbersRangeOnly
}
