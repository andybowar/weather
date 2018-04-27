package WeatherStation;

import GetCoordinates.FindCoordinates;
import GetCoordinates.Results;
import GetCoordinates.SaveLocation;
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
    private String location;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetZip getZip;

    @Autowired
    private SaveLocation saveLocation;

    @PostConstruct
    public void init() {

        // Tries to get coordinates from file
        String latLon;

        // TODO: Add error handling for the event that these files don't exist or contain bad data
        latLon = saveLocation.getCoords();
        location = saveLocation.getLoc();

        // If we don't find anything in the coords file, ask for
        // zip code and derive coordinates from Google Geocode API
        if (latLon.equals("")) {
            String zipCode = getZip.getZipCode();

            // Hits Google's Geocode API to find coordinates based on a given zip code
            FindCoordinates findCoordinates = restTemplate.getForObject("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + zipCode, FindCoordinates.class);

            // If we've queried the API too many times, it returns an error.
            // getStatus() will catch the error and end the program.
            findCoordinates.getStatus();

            // Stores the results of lat/lon in a one-element long list.
            // Must be a list because of JSON structure
            // List should never have more than one element
            List<Results> results = findCoordinates.getResults();

            // Lat and Lng are two separate fields in the Geocode JSON, and
            // the list is contains only one element, so the lat/lon are
            // always the in the first index.
            Double lat = results.get(0).getGeometry().getLocation().getLat();
            Double lng = results.get(0).getGeometry().getLocation().getLng();
            location = results.get(0).getFormatted_address();

            // Concatenate lat/lon into a single string, then save to file
            latLon = String.valueOf(lat) + "," + String.valueOf(lng);
            saveLocation.saveCoords(latLon);
            saveLocation.saveLoc(location);
        }

        // Get endpoint for the list of observation stations near the given coordinates
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

    String getLocation() {
        return location;
    }
}
