package et.kidus.geez;

/**
 * A simple library to convert numbers to their Ge'ez number representaion.
 *
 * @author Kidus Makonnen
 * @since 2020-Feb-01
 */
public class GeezUtil {
    private static final String[] GEEZ_ONES = {"", "፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱"};
    private static final String[] GEEZ_TENS = {"", "፲", "፳", "፴", "፵", "፶", "፷", "፸", "፹", "፺"};
    private static final String GEEZ_HUNDRED = "፻";
    private static final String GEEZ_TEN_THOUSAND = "፼";

    /**
     * This method converts an arabic numerals (1, 2, 3 etc) to Ge'ez numerals.
     *
     * @param num This is the first parameter to this method, which is any number (long) greater than 0.
     * @return The returned value is the converted Ge'ez number.
     */
    public static String toGeez(long num) {
        if (num < 0) {
            throw new IllegalArgumentException("Value must be greater than 1.");
        }
        //determine the number of digits and append 0 if it's odd
        String number = (int) Math.log10(num) % 2 == 0 ? String.format("0%d", num) : String.valueOf(num);
        //split the number string into groups of two
        String[] splitNumber = number.split("(?<=\\G..)");

        StringBuilder geez = new StringBuilder();

        int groupLength = splitNumber.length - 1;
        for (int i = groupLength; i >= 0; i--) {
            String pair = splitNumber[i];

            //convert each pair
            String convertedPair = GEEZ_TENS[Character.getNumericValue(pair.charAt(0))] + GEEZ_ONES[Character.getNumericValue(pair.charAt(1))];
            String delim = "";

            if (groupLength == i) {
                delim = "";
            } else {
                if ((groupLength - i) % 2 == 0) {
                    delim = GEEZ_TEN_THOUSAND;
                    if (i == 0 && pair.equals("01")) {
                        convertedPair = "";
                    }
                } else {
                    if (!pair.equals("00"))
                        delim = GEEZ_HUNDRED;
                    if (pair.equals("01")) {
                        convertedPair = "";
                    }
                }
            }

            geez.insert(0, convertedPair + delim);
        }

        return geez.toString();
    }


}
