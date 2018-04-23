package main.java.WeatherStation;

import main.java.CurrentWeather.WeatherCat;
import main.java.Forecast.ForecastData;
import main.java.Forecast.Periods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WeatherApplicationRunner implements ApplicationRunner {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FindWeatherStation findWeatherStation;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String stationUrl = findWeatherStation.getStationUrl();
        String forecastUrl = findWeatherStation.getForecastUrl();

        WeatherCat weatherCat = restTemplate.getForObject(stationUrl + "/observations/current", WeatherCat.class);
        ForecastData forecastData = restTemplate.getForObject(forecastUrl, ForecastData.class);

        final long tempValue = weatherCat.getProperties().getTemperature().convertCelToFah();
        final long dewpoint = weatherCat.getProperties().getDewpoint().convertCelToFah();
        final long windChill = weatherCat.getProperties().getWindChill().convertCelToFah();
        final String relativeHumidity = weatherCat.getProperties().getRelativeHumidity().getValue();

        System.out.println("*** CURRENT CONDITIONS ***");
        System.out.println("\nCURRENT TEMP: " + tempValue + " DEGREES.");
        System.out.println("CURRENT DEWPOINT: " + dewpoint + " DEGREES.");

        if (windChill == 0) {
            System.out.println("No wind chill.");
        } else {
            System.out.println("CURRENT WIND CHILL: " + windChill + " DEGREES.");
        }

        System.out.println(relativeHumidity);

        final List<Periods> forecast = forecastData.getProperties().getPeriods();

        System.out.println("\n*** FORECAST ***");
        for (Periods f : forecast) {
            System.out.println("\n" + f.getName() + ": ");
            System.out.println("HIGH TEMP: " + f.getTemperature());
            System.out.println("WIND SPEED: " + f.getWindSpeed());
            System.out.println("WIND DIRECTION: " + f.getWindDirection());
            System.out.println("SUMMARY: " + f.getShortForecast());
            System.out.println("DETAILS: " + f.getDetailedForecast());
        }
    }
}
