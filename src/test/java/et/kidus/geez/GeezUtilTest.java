package et.kidus.geez;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeezUtilTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/toGeezData.csv")
    void testToGeez(String input, String expected) {
        assertEquals(expected, GeezUtil.toGeez(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/fromGeezData.csv")
    void testFromGeez(String input, String expected) {
        assertEquals(expected, String.valueOf(GeezUtil.fromGeez(input)));
    }

    @ParameterizedTest
    @MethodSource("oneUptoTenThousandProvider")
    void testConvertFromGeezUptoTenThousand(int num) {
        String expected = String.valueOf(num);
        String expectedGeez = GeezUtil.toGeez(expected);
        assertEquals(expected, GeezUtil.fromGeez(expectedGeez));
    }

    @ParameterizedTest
    @ValueSource(strings = {"፩፩፩፻፻፻፻፩፩", "፩፩", "፴፫፻፪፪", "፭፭፭", "፩፲", "፲፲", "፻፻", "፼፼፼፼፼፻፻", "፻፻፼"})
    void testShouldThrowExceptionInvalidGeezNumber(String invalidGeez) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> GeezUtil.fromGeez(invalidGeez)
        );
        assertEquals("Invalid Ge'ez number.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-23", "-1", "0", "00", "00000", "000000000000"})
    void testShouldThrowExceptionOnValueLessThanOne(String num) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> GeezUtil.toGeez(num)
        );
        assertEquals("Invalid input.", exception.getMessage());
    }

    private static IntStream oneUptoTenThousandProvider() {
        return IntStream.range(1, 10000);
    }
}