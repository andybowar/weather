package main.java.GetCoordinates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindCoordinates {

    private List<Results> results;
    private String status;

    public FindCoordinates() {

    }

    public List<Results> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
