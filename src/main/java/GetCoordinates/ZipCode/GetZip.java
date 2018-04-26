package GetCoordinates.ZipCode;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetZip {

    private String zipCode;

    public String getZipCode() {

        // TODO: Probably not the best place to request user input. Find a different way to do it.
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your zip code?");

        return zipCode = scanner.next();
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
