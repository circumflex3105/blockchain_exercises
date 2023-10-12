import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    void test() {
        //given
        String input ="{\"name\": \"fabi\"}";

        //when
        String output = Main.getNameFromJson(input);

        //then
        Assertions.assertEquals("fabi", output);
    }
}
