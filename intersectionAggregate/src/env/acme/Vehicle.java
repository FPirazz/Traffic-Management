package acme;

public class Vehicle {

    private VehicleType type;

    public Vehicle(final VehicleType type) {
        this.type = type;
    }
    public VehicleType getType() {
        return type;
    }
}
