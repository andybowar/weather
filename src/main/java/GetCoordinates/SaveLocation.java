package GetCoordinates;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.*;

public class SaveLocation {

    public String getCoords() {
        String coords = "";

        int i;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("coords.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        try {
            do {
                i = fileInputStream.read();
                if(i != -1) coords = String.valueOf((char) i).concat(coords);
            } while(i != -1);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        try {
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Error closing file.");
        }

        StringBuilder result = new StringBuilder();
        for (int x = coords.length() - 1; x >= 0; x--) {
            result.append(coords.charAt(x));
        }

        return result.toString();
    }

    public String getLoc() {
        String coords = "";

        int i;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("loc.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        try {
            do {
                i = fileInputStream.read();
                if(i != -1) coords = String.valueOf((char) i).concat(coords);
            } while(i != -1);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        try {
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Error closing file.");
        }

        StringBuilder result = new StringBuilder();
        for (int x = coords.length() - 1; x >= 0; x--) {
            result.append(coords.charAt(x));
        }

        return result.toString();
    }

    public void saveCoords(String coords) {
        try (PrintWriter out = new PrintWriter("coords.txt")) {
            out.println(coords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLoc(String loc) {
        try (PrintWriter out = new PrintWriter("loc.txt")) {
            out.println(loc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
