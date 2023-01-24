import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightApp {
    private static final int MAX_FLIGHTS = 20;

    public String getAllFlights() {
        return this.getFlightsFromDatabase("SELECT * FROM flights");
    }

    public String getLongestFlight() {
        return this.getFlightsFromDatabase("SELECT * FROM flights ORDER BY (arr_time - dep_time) DESC LIMIT 1");
    }

    public String getShortestFlight() {
        return this.getFlightsFromDatabase("SELECT * FROM flights ORDER BY (arr_time - dep_time) ASC LIMIT 1");
    }

    public String getFlightsByDay(String day) {
        String numberFromDay = this.getNumberFromDay(day);
        return this.getFlightsFromDatabase("SELECT * FROM flights WHERE day_of_week = " + numberFromDay);
    }

    public String getCancelledFlights() {
        return this.getFlightsFromDatabase("SELECT * FROM flights WHERE cancelled = 1");
    }

    public String getDivertedFlights() {
        return this.getFlightsFromDatabase("SELECT * FROM flights WHERE diverted = 1");
    }

    private String getFlightsFromDatabase(String s) {
        ArrayList<Flight> flights = new ArrayList<>();

        try (Connection conn = this.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s);

            while (rs.next()) {
                flights.add(new Flight(
                        rs.getInt("day_of_week"),
                        rs.getInt("dep_time"),
                        rs.getInt("arr_time"),
                        rs.getInt("dep_delay"),
                        rs.getInt("arr_delay"),
                        rs.getInt("air_time"),
                        rs.getInt("distance"),
                        rs.getInt("cancelled"),
                        rs.getInt("diverted")));
                if (flights.size() >= MAX_FLIGHTS) {
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.formatFlights(flights);
    }

    private String formatFlights(ArrayList<Flight> flights) {
        return (new Gson()).toJson(flights);
    }

    private Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:flights.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private String getNumberFromDay(String day) {
        return switch (day) {
            case "monday" -> "1";
            case "tuesday" -> "2";
            case "wednesday" -> "3";
            case "thursday" -> "4";
            case "friday" -> "5";
            case "saturday" -> "6";
            case "sunday" -> "7";
            default -> "0";
        };
    }
}