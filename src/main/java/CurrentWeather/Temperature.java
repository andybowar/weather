package CurrentWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {

    private Double value;
    private String unitCode;
    private String qualityControl;

    private Double getValue() {
        if (value == null) {
            return null;
        }
        return value;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getQualityControl() {
        return qualityControl;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public void setQualityControl(String qualityControl) {
        this.qualityControl = qualityControl;
    }

    public long convertCelToFah() {
        if (value == null) {
            return 0;
        } else {
            Double celsius = getValue();
            Double fah = ((celsius * 9 / 5) + 32);

            return Math.round(fah);
        }
    }

}
