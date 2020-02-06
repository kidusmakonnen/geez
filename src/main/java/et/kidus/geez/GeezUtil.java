package et.kidus.geez;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple library to convert numbers to their Ge'ez number representation.
 *
 * @author Kidus Makonnen
 * @since 2020-Feb-01
 */
public class GeezUtil {
    private static final String[] GEEZ_ONES = {"", "፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱"};
    private static final String[] GEEZ_TENS = {"", "፲", "፳", "፴", "፵", "፶", "፷", "፸", "፹", "፺"};
    private static final String GEEZ_HUNDRED = "፻";
    private static final String GEEZ_TEN_THOUSAND = "፼";
    private static final Map<String, Integer> GEEZ_MAP = new HashMap<>();

    static {
        for (int i = 1; i < 11; i++) {
            GEEZ_MAP.put(toGeez(String.valueOf(i)), i);
            GEEZ_MAP.put(toGeez(String.valueOf(i * 10)), i * 10);
        }
        GEEZ_MAP.put(GEEZ_TEN_THOUSAND, 10000);
    }

    /**
     * This method converts an arabic numerals (1, 2, 3 etc) to Ge'ez numerals.
     *
     * @param num This is the first parameter to this method, which is any number (long) greater than 0.
     * @return The returned value is the converted Ge'ez number.
     */
    public static String toGeez(String num) {
        if (!num.matches("^[0-9]+") || "0".equals(num)) {
            throw new IllegalArgumentException("Invalid input.");
        }
        //determine the number of digits and append 0 if it's odd
        String number = num.length() % 2 == 0 ? num : String.format("0%s", num);
        //split the number string into groups of two
        List<String> splitNumber = splitAndGroup(number, 2);

        StringBuilder geez = new StringBuilder();

        int groupLength = splitNumber.size() - 1;
        for (int i = groupLength; i >= 0; i--) {
            String pair = splitNumber.get(i);

            //convert each pair
            String convertedPair = GEEZ_TENS[Character.getNumericValue(pair.charAt(0))] + GEEZ_ONES[Character.getNumericValue(pair.charAt(1))];
            String delim = "";

            if (groupLength == i) {
                delim = "";
            } else {
                if ((groupLength - i) % 2 == 0) {
                    delim = GEEZ_TEN_THOUSAND;
                    if (i == 0 && "01".equals(pair)) {
                        convertedPair = "";
                    }
                } else {
                    if (!"00".equals(pair))
                        delim = GEEZ_HUNDRED;
                    if ("01".equals(pair)) {
                        convertedPair = "";
                    }
                }
            }

            geez.insert(0, convertedPair + delim);
        }

        return geez.toString();
    }

    /**
     * This method converts Ge'ez numerals back into Arabic numerals.
     *
     * @param geezNumber The Ge'ez number string.
     * @return Returns the converted number.
     */
    public static String fromGeez(String geezNumber) throws IllegalArgumentException {
        String geezNum = geezNumber;
        //determine the amount of GEEZ_TEN_THOUSAND characters in the string
        int amountOfThousands = geezNum.length() - geezNum.replace(GEEZ_TEN_THOUSAND, "").length();
        //from the amountOfThousands get the max multiplier of the converted back number
        BigInteger multiplier = BigDecimal.valueOf(Math.pow(10000, amountOfThousands)).toBigInteger();

        BigInteger res = new BigInteger("0");

        if (geezNum.charAt(0) == GEEZ_TEN_THOUSAND.charAt(0)) {
            geezNum = GEEZ_ONES[1] + geezNum;
        }

        if (!geezNum.contains(GEEZ_TEN_THOUSAND)) {
            return String.valueOf(convertThousands(geezNum));
        }

        boolean multiplyNext = false;
        StringBuilder thousand = new StringBuilder();
        for (int i = 0; i < geezNum.length(); i++) {
            String s = String.valueOf(geezNum.charAt(i));
            if (!s.equals(GEEZ_TEN_THOUSAND)) { //convert Ge'ez numbers delimited by GEEZ_TEN_THOUSAND
                thousand.append(s);
                multiplyNext = true;
            } else {
                if (multiplyNext) {
                    res = res.add(multiplier.multiply(BigInteger.valueOf(convertThousands(thousand.toString()))));
                    multiplyNext = false;
                    thousand = new StringBuilder();
                    multiplier =  BigDecimal.valueOf(Math.pow(10000, --amountOfThousands)).toBigInteger();
                }
            }
        }

        //convert back the remaining digits and add them back
        if (thousand.length() > 0) {
            res = res.add(BigInteger.valueOf(convertThousands(thousand.toString())));
        }

        return res.toString();
    }

    /**
     * This method converts numbers 1 - 9999 from Ge'ez to Arabic numerals.
     *
     * @param thousand This is a Ge'ez number string between 1 and 9999.
     * @return Returns the number that is converted to Arabic numerals.
     */
    private static long convertThousands(String thousand) {
        if (!thousand.contains(GEEZ_HUNDRED)) {
            return convertTens(thousand);
        }

        int amountOfHundreds = thousand.length() - thousand.replace(GEEZ_HUNDRED, "").length();

        if (amountOfHundreds > 1) {
            throw new IllegalArgumentException("Invalid Ge'ez number.");
        }

        String[] hundreds = thousand.split(GEEZ_HUNDRED);

        switch (hundreds.length) {
            case 0:
                return 100;
            case 1:
                return convertTens(hundreds[0]) * 100;
            case 2:
                return (convertTens(hundreds[0]) * 100) + convertTens(hundreds[1]);
            default:
                throw new IllegalArgumentException("Invalid Ge'ez number.");
        }


    }

    /**
     * This method converts numbers 1 - 99 from Ge'ez to Arabic numerals.
     * @param hundred This is a Ge'ez number string between 1 and 99.
     * @return Returns the number that is converted to Arabic numerals.
     */
    private static long convertTens(String hundred) {
        switch (hundred.length()) {
            case 0:
                return 1;
            case 1:
                return GEEZ_MAP.get(hundred);
            case 2:
                String tens = String.valueOf(hundred.charAt(0));
                String ones = String.valueOf(hundred.charAt(1));
                if (!hundredsValid(hundred)) {
                    throw new IllegalArgumentException("Invalid Ge'ez number.");
                }
                return GEEZ_MAP.get(tens) + GEEZ_MAP.get(ones);
            default:
                throw new IllegalArgumentException("Invalid Ge'ez number.");
        }
    }

    /**
     * This method checks for illegal combination of Ge'ez numerals (1 - 99).
     *
     * @param hundreds The Ge'ez number parameter between 1 - 99 to be validated.
     * @return Returns true if the provided Ge'ez number is valid. Returns false otherwise.
     */
    private static boolean hundredsValid(String hundreds) {
        String tens = String.valueOf(hundreds.charAt(0));
        String ones = String.valueOf(hundreds.charAt(1));

        for (String GEEZ_ONE : GEEZ_ONES) {
            if (tens.equals(GEEZ_ONE)) {
                return false;
            }
        }

        for (String GEEZ_TEN : GEEZ_TENS){
            if (ones.equals(GEEZ_TEN)) {
                return false;
            }
        }

        return true;
    }

    /**
     * A method to split a string into a specified length of characters.
     * For example splitAndGroup("12345", 2) would return {"1", "23", "45"}
     *
     * @param text        The text to be split.
     * @param splitLength The length of the split characters.
     * @return A list of split strings
     */
    private static List<String> splitAndGroup(String text, int splitLength) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < text.length(); i += splitLength) {
            parts.add(text.substring(i, Math.min(text.length(), i + splitLength)));
        }
        return parts;
    }


}
