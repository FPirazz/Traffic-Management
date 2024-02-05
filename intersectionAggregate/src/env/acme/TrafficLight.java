package acme;

import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

public class TrafficLight extends Artifact {

    private String id;
    private TrafficLightState state;

    void init() {
        defineObsProperty("trState", "red");
        this.state = TrafficLightState.RED;
//        this.id = id;
//        log(id + " - Traffic Light RED");
        log("Traffic Light is ready");
    }

    @OPERATION
    public void redState() {
        this.state = TrafficLightState.RED;
        getObsProperty("trState").updateValue("red");
        log("Traffic Light is: " + this.state);
        this.await_time(5_000);

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
        getObsProperty("trState").updateValue("yellow");
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
        getObsProperty("trState").updateValue("green");
        log("Traffic Light is: " + this.state);
        this.await_time(5_000);

        //TODO Send to intersection state
//        try {
//            wait(5_000); // 5 seconds are just for testing, Expected wait time is around 30 seconds
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

}
