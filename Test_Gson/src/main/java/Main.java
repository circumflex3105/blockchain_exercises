import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        String jsonString = "{\"name\": \"fabi\"}";
        System.out.printf(getNameFromJson(jsonString));
    }

    public static String getNameFromJson(String json) {
        return new JsonParser().parse(json).getAsJsonObject().get("name").getAsString();
    }
}