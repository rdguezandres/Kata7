import java.time.DayOfWeek;
import java.time.LocalTime;

public class Flight {
    private int dayOfWeek;
    private int departureTime;
    private int arrivalTime;
    private int departureDelay;
    private int arrivalDelay;
    private int duration;
    private int distance;
    private int cancelled;
    private int diverted;

    public Flight(int dayOfWeek, int departureTime, int arrivalTime, int departureDelay, int arrivalDelay, int duration, int distance, int cancelled, int diverted) {
        this.dayOfWeek = dayOfWeek;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDelay = departureDelay;
        this.arrivalDelay = arrivalDelay;
        this.duration = duration;
        this.distance = distance;
        this.cancelled = cancelled;
        this.diverted = diverted;
    }

    public String toString() {
        DayOfWeek dayOfWeek = DayOfWeek.of(this.dayOfWeek);
        return "Flight{dayOfWeek=" + dayOfWeek + "," +
                " departureTime=" + LocalTime.of(this.departureTime / 100, this.departureTime % 100) + "," +
                " arrivalTime=" + LocalTime.of(this.arrivalTime / 100, this.arrivalTime % 100) + "," +
                " departureDelay=" + this.departureDelay + "," +
                " arrivalDelay=" + this.arrivalDelay + "," +
                " duration=" + this.duration + "," +
                " distance=" + this.distance + "," +
                " cancelled=" + this.cancelled + "," +
                " diverted=" + this.diverted + "}";
    }
}
