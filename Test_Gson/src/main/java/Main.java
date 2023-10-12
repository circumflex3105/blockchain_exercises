import com.google.gson.JsonParser;
import org.slf4j.*;


public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Application starts...");
        String jsonString = "{\"name\": \"fabi\"}";
        logger.info("Name: {}", getNameFromJson(jsonString));
    }

    public static String getNameFromJson(String json) {
        return new JsonParser().parse(json).getAsJsonObject().get("name").getAsString();
    }
}