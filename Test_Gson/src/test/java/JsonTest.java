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
        final NullPointerException nullException = Assertions.assertThrows(NullPointerException.class, () -> Main.getNameFromJson(input));
        Assertions.assertEquals("Json is null!", nullException.getMessage());
    }

    @Test
    void testBadInput() {
        //given
        String input = "BAD";

        //then
        final IllegalStateException illStateEx = Assertions.assertThrows(IllegalStateException.class, () -> Main.getNameFromJson(input));
        Assertions.assertEquals("Not a JSON Object: \"BAD\"", illStateEx.getMessage());
    }
}
