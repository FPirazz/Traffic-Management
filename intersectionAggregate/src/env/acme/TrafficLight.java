package acme;

import cartago.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

import org.json.*;

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

        File f = new File(getClass().getClassLoader().getResource("config.json").getPath());

        Gson g = new Gson();

        if(f.exists()) {
            JsonObject jsonObject = null;
            try {
                jsonObject = new JsonParser().parse(Files.readString(Path.of(f.getPath()))).getAsJsonObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(jsonObject.get("configs"));
        }

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
    }

    @OPERATION
    public void yellowState() {
        this.state = TrafficLightState.YELLOW;
        getObsProperty("trState" + id).updateValue("yellow");
        log("Traffic Light is: " + this.state);
        this.waitAndSpawnVehicles(yellowWaitTime, 500, 20);
    }

    @OPERATION
    public void greenState() {
        this.state = TrafficLightState.GREEN;
        getObsProperty("trState" + id).updateValue("green");
        log("Traffic Light is: " + this.state);
        this.waitAndRemoveVehicles(greenWaitTime, 500);
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
        Random rnd = new Random(System.currentTimeMillis());
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

    private void waitAndRemoveVehicles(final long timeToWaitMillis, final long checksPerMillis) {
        Vehicle tmpVehicle;
        Queue<Vehicle> lane = this.lane.getVehiclesOnLane();
        for(long i = checksPerMillis; i < timeToWaitMillis; i += checksPerMillis) {
            this.await_time(checksPerMillis);
            if(!lane.isEmpty()){
                tmpVehicle = lane.poll();
                if(tmpVehicle.getType().equals(VehicleType.Normal)){
                    ObsProperty normalCarsProp = getObsProperty("normalVehicles");
                    normalCarsProp.updateValue(normalCarsProp.intValue() - 1);
                    getObsProperty("totalVehicles").updateValue(getObsProperty("totalVehicles").intValue() - 1);
                }
            }
        }
    }

}
