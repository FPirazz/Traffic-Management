package acme;

import moise.os.ss.Link;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Lane {

    private Queue<Vehicle> vehiclesOnLane;

    public Lane() {
        this.vehiclesOnLane = new LinkedList<>();
    }
    public Queue<Vehicle> getVehiclesOnLane() {
        return vehiclesOnLane;
    }
}
