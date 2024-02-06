package acme;

import moise.os.ss.Link;

import java.util.LinkedList;
import java.util.List;

public class Lane {

    private List<Vehicle> vehiclesOnLane;

    public Lane() {
        this.vehiclesOnLane = new LinkedList<>();
    }
    public List<Vehicle> getVehiclesOnLane() {
        return vehiclesOnLane;
    }
}
