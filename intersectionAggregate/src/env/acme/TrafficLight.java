package acme;

import cartago.*;

import java.util.Random;

public class TrafficLight extends Artifact {

    private String id;
    private TrafficLightState state;
    private Lane lane;
    private long redWaitTime;
    private long yellowWaitTime;
    private long greenWaitTime;


    void init(final String id) {
        this.state = TrafficLightState.RED;
        this.id = id;
        this.lane = new Lane();
        redWaitTime = 5_000;
        yellowWaitTime = 5_000;
        greenWaitTime = 5_000;

        defineObsProperty("trState" + id, "red");
        defineObsProperty("normalVehicles", 0);
        defineObsProperty("emergencyVehicles", 0);
        defineObsProperty("totalVehicles", 0);
        defineObsProperty("target_state" + id, "red");

        log("Traffic Light is ready");
    }

    @OPERATION
    public void redState() {
        this.state = TrafficLightState.RED;
        getObsProperty("trState" + id).updateValue("red");
        log("Traffic Light is: " + this.state);
        this.waitAndSpawnVehicles(redWaitTime, 500, 50);
        this.sendRedIntersection();

        //TODO Send to intersection state
//        try {
//            wait(5_000); // 5 seconds are just for testing, Expected wait time is around 60 seconds
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @OPERATION
    public void yellowState() {
        this.state = TrafficLightState.YELLOW;
        getObsProperty("trState" + id).updateValue("yellow");
        log("Traffic Light is: " + this.state);
        this.await_time(yellowWaitTime);

        //TODO Send to intersection state
//        try {
//            wait(5_000); // 5 seconds are just for testing, Expected wait time is around 7 seconds
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @OPERATION
    public void greenState() {
        this.state = TrafficLightState.GREEN;
        getObsProperty("trState" + id).updateValue("green");
        log("Traffic Light is: " + this.state);
        this.await_time(greenWaitTime);

        //TODO Send to intersection state
//        try {
//            wait(5_000); // 5 seconds are just for testing, Expected wait time is around 30 seconds
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @OPERATION
    public void preGreenState() {
        this.state = TrafficLightState.PREGREEN;
        getObsProperty("trState" + id).updateValue("preGreen");
    }

    @OPERATION
    public void sendId() {
        try {
            ArtifactId intersection = this.lookupArtifact("intersection");
            execLinkedOp(intersection, "storeTrafficLightId", this.id);
        } catch (OperationException e) {
            throw new RuntimeException(e);
        }
    }

    @INTERNAL_OPERATION
    private void sendRedIntersection() {
        try {
            ArtifactId intersection = this.lookupArtifact("intersection");
            execLinkedOp(intersection, "trIsDone", this.id);
        } catch (OperationException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitAndSpawnVehicles(final long timeToWaitMillis, final long checksPerMillis, int chanceToSpawnVehicles) {
        int generatedNumber;
        Random rnd = new Random();
        for(long i = checksPerMillis; i < timeToWaitMillis; i += checksPerMillis) {
            this.await_time(checksPerMillis);
            generatedNumber = rnd.nextInt(1, 100);
            if(generatedNumber < chanceToSpawnVehicles) {
                this.lane.getVehiclesOnLane().add(new Vehicle(VehicleType.Normal));
                ObsProperty normalCarsProp = getObsProperty("normalVehicles");
                normalCarsProp.updateValue(normalCarsProp.intValue() + 1);

                getObsProperty("totalVehicles").updateValue(getObsProperty("normalVehicles").intValue() + getObsProperty("emergencyVehicles").intValue());
            }
        }
    }

}
