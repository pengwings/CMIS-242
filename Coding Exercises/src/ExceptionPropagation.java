import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionPropagation {

    public static void main(String[] args) {
        //handles Parsing and IO Exceptions from parseJSONfile()
        try {
            JSONObject weatherJSON = parseJSONfile();
            String[] weatherInformation = readJSONfile(weatherJSON);
            System.out.println("The weather in " + weatherInformation[1] + ", " + weatherInformation[2] + " is " + weatherInformation[3] + " degrees Farenheit.");

        } catch (IOException fileNotFound) {
            System.out.println("File not found.");
        } catch (ParseException parseError) {
            System.out.println("JSON file not in correct format.");
        }

    }
    // reads JSON object and specifies Parsing and IO Exceptions
    public static JSONObject parseJSONfile() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("C:/temp/weather.json");
        Object obj = parser.parse(reader);
        JSONObject weather = (JSONObject) obj;

        return weather;
    }

    public static String[] readJSONfile(JSONObject weatherJSON) {

        String[] weatherInformation = new String[4];
        weatherInformation[0] = (String) weatherJSON.get("zipCode");
        weatherInformation[1] = (String) weatherJSON.get("city");
        weatherInformation[2] = (String) weatherJSON.get("state");
        weatherInformation[3] = (String) weatherJSON.get("temperature");

        return weatherInformation;
    }
}
