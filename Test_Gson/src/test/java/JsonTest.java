import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    void test() {
        //given
        String input = "{\"name\": \"fabi\"}";

        //when
        String output = Main.getNameFromJson(input);

        //then
        Assertions.assertEquals("fabi", output);
    }

    @Test
    void testNull() {
        //given
        String input = null;

        //then
        Assertions.assertThrows(NullPointerException.class, () -> Main.getNameFromJson(input));
    }
}
