package CurrentWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {

    private Double value;
    private String unitCode;
    private String qualityControl;

    public Double getValue() {
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
        Double celsius = getValue();
        Double fah = ((celsius*9/5)+32);

        return Math.round(fah);
    }

}
