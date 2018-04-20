import CurrentWeather.WeatherCat;
import WeatherStation.FindWeatherStation;
import org.springframework.web.client.RestTemplate;

public class Application {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        FindWeatherStation findWeatherStation = new FindWeatherStation();
        findWeatherStation.getCoordinates();
        String stationUrl = findWeatherStation.getStationUrl();
        WeatherCat weatherCat = restTemplate.getForObject(stationUrl + "/observations/current", WeatherCat.class);

        final long tempValue = weatherCat.getProperties().getTemperature().convertCelToFah();
        final long dewpoint = weatherCat.getProperties().getDewpoint().convertCelToFah();
        final long windChill = weatherCat.getProperties().getWindChill().convertCelToFah();

        System.out.println("\nCURRENT TEMP: " + tempValue + " DEGREES.");
        System.out.println("CURRENT DEWPOINT: " + dewpoint + " DEGREES.");

        if (windChill == 0) {
            System.out.println("No wind chill.");
        } else {
            System.out.println("CURRENT WIND CHILL: " + windChill + " DEGREES.");
        }

    }
}
