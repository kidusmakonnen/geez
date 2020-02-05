package et.kidus.geez;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeezUtilTest {
    private final String[] TEST_INPUTS = {
            "1","10","100","1000","10000","100000","1000000","10000000","100000000","1000000000","10000000000",
            "100000000000","1000000000000","100010000","100100000","100200000","100110000","1","11","111","1111","11111",
            "111111","1111111","11111111","111111111","1111111111","11111111111","111111111111","1111111111111","1","12",
            "123","1234","12345","7654321","17654321","51615131","15161513","10101011","101","1001","1010","1011","1100",
            "1101","1111","10001","10010","10100","10101","10110","10111","100001","100010","100011","100100","101010",
            "1000001","1000101","1000100","1010000","1010001","1100001","1010101","101010101","100010000","100010100",
            "101010100","3","30","33","303","3003","3030","3033","3300","3303","3333","30003","30303","300003","303030",
            "3000003","3000303","3030003","3300003","3030303","303030303","333333333"
    };

    private final String[] TEST_OUTPUTS = {
            "፩", "፲", "፻", "፲፻", "፼", "፲፼", "፻፼", "፲፻፼", "፼፼", "፲፼፼", "፻፼፼", "፲፻፼፼", "፼፼፼", "፼፩፼", "፼፲፼", "፼፳፼",
            "፼፲፩፼", "፩", "፲፩", "፻፲፩", "፲፩፻፲፩", "፼፲፩፻፲፩", "፲፩፼፲፩፻፲፩", "፻፲፩፼፲፩፻፲፩", "፲፩፻፲፩፼፲፩፻፲፩", "፼፲፩፻፲፩፼፲፩፻፲፩",
            "፲፩፼፲፩፻፲፩፼፲፩፻፲፩", "፻፲፩፼፲፩፻፲፩፼፲፩፻፲፩", "፲፩፻፲፩፼፲፩፻፲፩፼፲፩፻፲፩", "፼፲፩፻፲፩፼፲፩፻፲፩፼፲፩፻፲፩", "፩", "፲፪", "፻፳፫", "፲፪፻፴፬",
            "፼፳፫፻፵፭", "፯፻፷፭፼፵፫፻፳፩", "፲፯፻፷፭፼፵፫፻፳፩", "፶፩፻፷፩፼፶፩፻፴፩", "፲፭፻፲፮፼፲፭፻፲፫", "፲፻፲፼፲፻፲፩", "፻፩", "፲፻፩", "፲፻፲", "፲፻፲፩",
            "፲፩፻", "፲፩፻፩", "፲፩፻፲፩", "፼፩", "፼፲", "፼፻", "፼፻፩", "፼፻፲", "፼፻፲፩", "፲፼፩", "፲፼፲", "፲፼፲፩", "፲፼፻", "፲፼፲፻፲", "፻፼፩",
            "፻፼፻፩", "፻፼፻", "፻፩፼", "፻፩፼፩", "፻፲፼፩", "፻፩፼፻፩", "፼፻፩፼፻፩", "፼፩፼", "፼፩፼፻", "፼፻፩፼፻", "፫", "፴", "፴፫", "፫፻፫",
            "፴፻፫", "፴፻፴", "፴፻፴፫", "፴፫፻", "፴፫፻፫", "፴፫፻፴፫", "፫፼፫", "፫፼፫፻፫", "፴፼፫", "፴፼፴፻፴", "፫፻፼፫", "፫፻፼፫፻፫", "፫፻፫፼፫",
            "፫፻፴፼፫", "፫፻፫፼፫፻፫", "፫፼፫፻፫፼፫፻፫", "፫፼፴፫፻፴፫፼፴፫፻፴፫"
    };

    private final String[] INVALID_INPUTS = {
            "፩፩፩፻፻፻፻፩፩", "፩፩", "፴፫፻፪፪", "፭፭፭", "፩፲", "፲፲"
    };



    @Test
    void testToGeez() {
        for (int i = 0; i < TEST_INPUTS.length; i++) {
            assertEquals(GeezUtil.toGeez(TEST_INPUTS[i]), TEST_OUTPUTS[i]);
        }
    }

    @Test
    void testFromGeez() {
        for (int i = 0; i < TEST_INPUTS.length; i++) {
            assertEquals(TEST_INPUTS[i], String.valueOf(GeezUtil.fromGeez(TEST_OUTPUTS[i])));
        }
    }

    @Test
    void testConvertFromGeezUptoTenThousand() {
        for (int i = 1; i < 10000; i++) {
            String expected = GeezUtil.toGeez(String.valueOf(i));
            assertEquals(i, GeezUtil.fromGeez(expected));
        }
    }

    @Test
    void testShouldThrowExceptionInvalidGeezNumber() {
        for (int i = 0; i < INVALID_INPUTS.length; i++) {
            int index = i;
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> GeezUtil.fromGeez(INVALID_INPUTS[index])
            );

            assertEquals("Invalid Ge'ez number.", exception.getMessage());
        }
    }

    @Test
    void testShouldThrowExceptionOnValueLessThanOne() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> GeezUtil.toGeez("0")
        );
        assertEquals("Invalid input.", exception.getMessage());
    }
}