package GetCoordinates;

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

    public void getStatus() {
        if(status.equals("OVER_QUERY_LIMIT")) {
            System.out.println("Too many queries. Please Try again later.");
            System.exit(0);
        } else {
            System.out.println("Status: " + status);
        }
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
