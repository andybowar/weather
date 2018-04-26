package WeatherStation;

import GetCoordinates.FindCoordinates;
import GetCoordinates.Results;
import GetCoordinates.ZipCode.GetZip;
import WeatherStation.CoordinatesEndpoint.CoordinatesEndpoint;
import WeatherStation.StationsEndpoint.Features;
import WeatherStation.StationsEndpoint.StationsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FindWeatherStation {

    private String forecastUrl;
    private String stationUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetZip getZip;

    @PostConstruct
    public void init() {

        String zipCode = getZip.getZipCode();

        // Hits Google's Geocode API to find coordinates based on a given zip code
        FindCoordinates findCoordinates = restTemplate.getForObject("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + zipCode, FindCoordinates.class);
        findCoordinates.getStatus();
        List<Results> results = findCoordinates.getResults();

        // TODO: Add API authentication
        // Lat and Lng are two separate fields in the Geocode JSON, and
        // the list is contains only one element, so the lat/lon are
        // always the in the first index.
        Double lat = results.get(0).getGeometry().getLocation().getLat();
        Double lng = results.get(0).getGeometry().getLocation().getLng();

        // Concatenate lat/lon into a single string
        String latLon = String.valueOf(lat) + "," + String.valueOf(lng);

        // Get endpoint for the list of observation stations near the given coordinates
        //String latLon = "44.9038,-93.1782";
        CoordinatesEndpoint coordinatesEndpoint = restTemplate
                .getForObject("https://api.weather.gov/points/"
                        + latLon, CoordinatesEndpoint.class);

        // observationStations variable now contains URL for the endpoint needed to find the list of observation stations
        String observationStations = coordinatesEndpoint.getProperties().getObservationStations();
        forecastUrl = coordinatesEndpoint.getProperties().getForecast();

        // Stats of each weather station are stored in the 'features' list
        StationsEndpoint stationsEndpoint = restTemplate.getForObject(observationStations, StationsEndpoint.class);
        List<Features> features = stationsEndpoint.getFeatures();
        stationUrl = features.get(0).getId();
    }

    String getStationUrl() {
        return stationUrl;
    }

    String getForecastUrl() {
        return forecastUrl;
    }
}
