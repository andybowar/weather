package main.java.Forecast;

public class Periods {
    private int number;
    private String name;
    private Boolean isDaytime;
    private int temperature;
    private String windSpeed;
    private String windDirection;
    private String shortForecast;
    private String detailedForecast;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public Boolean getIsDaytime() {
        return isDaytime;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDaytime(Boolean isDaytime) {
        this.isDaytime = isDaytime;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }
}
