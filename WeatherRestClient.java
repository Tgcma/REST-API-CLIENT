import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherRestClient {

    public static void main(String[] args) {

        try {
            
            String apiUrl = "https://api.open-meteo.com/v1/forecast?"
                    + "latitude=17.3850&longitude=78.4867&current_weather=true";

            
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

           
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject currentWeather = jsonObject.getJSONObject("current_weather");

            
            double temperature = currentWeather.getDouble("temperature");
            double windSpeed = currentWeather.getDouble("windspeed");
            int weatherCode = currentWeather.getInt("weathercode");

           
            System.out.println("------ Weather Report ------");
            System.out.println("Location       : Hyderabad");
            System.out.println("Temperature    : " + temperature + " °C");
            System.out.println("Wind Speed     : " + windSpeed + " km/h");
            System.out.println("Weather Code   : " + weatherCode);
            System.out.println("----------------------------");

        } catch (Exception e) {
            System.out.println("Error occurred while fetching weather data.");
            e.printStackTrace();
        }
    }
}
