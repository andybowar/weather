package WeatherStation;

import WeatherStation.CoordinatesEndpoint.CoordinatesEndpoint;
import WeatherStation.StationsEndpoint.Features;
import WeatherStation.StationsEndpoint.StationsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FindWeatherStation {

    private String latLon = "44.9038,-93.1782";
    private String[] latLonString = latLon.split(",");

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private List<Features> features;

    private ArrayList<Double> latList = new ArrayList<>();
    private ArrayList<Double> lonList = new ArrayList<>();
    private ArrayList<Double[]> latLonList = new ArrayList<>();
    private List<Double> lengths = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Get endpoint for the list of observation stations near the given coordinates
        CoordinatesEndpoint coordinatesEndpoint = restTemplate
                .getForObject("https://api.weather.gov/points/"
                        + latLon, CoordinatesEndpoint.class);

        // observationStations variable now contains URL for the endpoint needed to find the list of observation stations
        String observationStations = coordinatesEndpoint.getProperties().getObservationStations();

        // Stats of each weather station are stored in the 'features' list
        StationsEndpoint stationsEndpoint = restTemplate.getForObject(observationStations, StationsEndpoint.class);
        features = stationsEndpoint.getFeatures();

    }

    /** The functions used to determine the nearest weather station to the given
     * set of coordinates is not necessary because api.weather.com does that for
     * you when you insert a set of coordinates in the endpoint. The weather
     * stations are already ordered by proximity to the given coordinates, so
     * the closest station will always be the first station listed.
     *
     * Therefore, this class could be drastically simplified by simply taking
     * the first set of coordinates from the JSON.
     */

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point
     * el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    private static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public void getCoordinates() {
        for (Features x : features) {
            // Grab each set of coordinates and put them in 'coords' variable
            Double[] coords = x.getGeometry().getCoordinates();

            // Put each set of coordinates in an array
            latLonList.add(coords);

            // Add latitude and longitude to array
            latList.add(coords[1]);
            lonList.add(coords[0]);
        }

        // Calculate distances between given coordinates and coordinates
        // of each stations and add distances to lengths array
        for (int i = 0; i < latList.size(); i++) {
            lengths.add(distance(latList.get(i),
                    Double.valueOf(latLonString[0]),
                    lonList.get(i),
                    Double.valueOf(latLonString[1]), 0, 0));
        }

        //System.out.println(lengths);
        //System.out.println("The smallest value is: " + getMinValue(lengths));
        //System.out.println(lengths.indexOf(getMinValue(lengths)));
        //
        //// Unnecessary
        //String finalCoords = reverseCoords();
        //System.out.println(finalCoords);
    }

    public String getStationUrl() {
        // Store index of the smallest value of lengths. Probably will always
        // be 0. This means that either something in here is wrong or the first
        // set of coordinates in latLonList will always be the closest set to
        // the given coordinates.
        int lengthIndex = lengths.indexOf(getMinValue(lengths));

        // Get the set of coordinates in latLonList that corresponds to the
        // shortest distance.
        String stringFinalCoordsSet = Arrays.toString(latLonList.get(lengthIndex));

        String stationUrl = "";
        String finalCoords = reverseCoords();

        for (Features x : features) {
            if (stringFinalCoordsSet.equals(Arrays.toString(x.getGeometry().getCoordinates()))) {
                System.out.println("\nGIVEN COORDINATES: " + latLon);
                System.out.println("CALCULATED COORDINATES: " + finalCoords);
                System.out.println("STATION COORDINATES: " + Arrays.toString(x.getGeometry().getCoordinates()));
                System.out.println("STATION URL: " + x.getId() + "\n");
                stationUrl = x.getId();
            }
        }
        return stationUrl;
    }

    /** Because JSON provides coordinates as longitude then latitude, we can use
     * this method to reverse them so that they're ordered correctly
     */
    // TODO: Make this method a constructor that can take any set of coordinates
    // TODO: and reorder them
    private String reverseCoords () {
        // Store index of the smallest value of lengths. Probably will always
        // be 0. This means that either something in here is wrong or the first
        // set of coordinates in latLonList will always be the closest set to
        // the given coordinates.
        int lengthIndex = lengths.indexOf(getMinValue(lengths));

        // Get the set of coordinates in latLonList that corresponds to the
        // shortest distance.
        String stringFinalCoordsSet = Arrays.toString(latLonList.get(lengthIndex));

        // Switch lon/lat so the set is lat/lon
        String[] stringCoordsSetList = stringFinalCoordsSet.replaceAll("]", "").replaceAll("\\[", "").replaceAll(" ", "").split(",");

        return stringCoordsSetList[1] + "," + stringCoordsSetList[0];
    }

    //Find lowest value in array using loop
    private static Double getMinValue(List<Double> numbers) {
        Double minValue = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++){
            if(numbers.get(i) < minValue){
                minValue = numbers.get(i);
            }
        }
        return minValue;
    }
}
