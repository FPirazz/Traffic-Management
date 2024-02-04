package acme;

import cartago.Artifact;
import cartago.OPERATION;

public class TrafficLight extends Artifact {

    private String id;
    private TrafficLightState state;

    void init(final String id, final TrafficLightState state) {
        defineObsProperty("state", state);
        this.state = state;
        this.id = id;
        log(id + " - Traffic Light RED");
    }

    @OPERATION
    public void redState() {
        this.state = TrafficLightState.RED;
        getObsProperty("state").updateValue(TrafficLightState.RED);
        //TODO Send to intersection state
        try {
            wait(10_000); // 10 seconds are just for testing, Expected wait time is around 60 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @OPERATION
    public void yellowState() {
        this.state = TrafficLightState.RED;
        getObsProperty("state").updateValue(TrafficLightState.YELLOW);
        //TODO Send to intersection state
        try {
            wait(7_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @OPERATION
    public void greenState() {
        this.state = TrafficLightState.RED;
        getObsProperty("state").updateValue(TrafficLightState.GREEN);
        //TODO Send to intersection state
        try {
            wait(30_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}
