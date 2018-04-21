import CurrentWeather.WeatherCat;
import WeatherStation.FindWeatherStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApplicationRunner implements ApplicationRunner {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FindWeatherStation findWeatherStation;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
