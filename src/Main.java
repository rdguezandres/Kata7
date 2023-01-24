import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        FlightApp flightApp = new FlightApp();

        get("/hello", (req, res) -> "Hello World");

        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            return "Hello " + name;
        });

        get("/flights/all", (req, res) -> flightApp.getAllFlights());

        get("/flights/longest", (req, res) -> flightApp.getLongestFlight());

        get("/flights/shortest", (req, res) -> flightApp.getShortestFlight());

        get("/flights/day/:day", (req, res) -> {
            String day = req.params(":day");
            return flightApp.getFlightsByDay(day);
        });

        get("/flights/cancelled", (req, res) -> flightApp.getCancelledFlights());

        get("/flights/diverted", (req, res) -> flightApp.getDivertedFlights());
    }
}