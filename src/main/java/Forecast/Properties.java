package main.java.Forecast;

import java.util.List;

public class Properties {
    private Elevation elevation;
    private List<Periods> periods;

    public Elevation getElevation() {
        return elevation;
    }

    public List<Periods> getPeriods() {
        return periods;
    }

    public void setElevation(Elevation elevation) {
        this.elevation = elevation;
    }

    public void setPeriods(List<Periods> periods) {
        this.periods = periods;
    }
}
