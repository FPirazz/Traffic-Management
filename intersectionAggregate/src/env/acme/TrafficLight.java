package acme;

import cartago.*;

public class TrafficLight extends Artifact {

    private String id;
    private TrafficLightState state;

    void init(final String id) {
        defineObsProperty("trState" + id, "red");
        defineObsProperty("target_state" + id, "red");
        this.state = TrafficLightState.RED;
        this.id = id;
//        log(id + " - Traffic Light RED");
        log("Traffic Light is ready");
    }

    @OPERATION
    public void redState() {
        this.state = TrafficLightState.RED;
        getObsProperty("trState" + id).updateValue("red");
        log("Traffic Light is: " + this.state);
        this.await_time(5_000);
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
        this.await_time(5_000);

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
        this.await_time(5_000);

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

}
