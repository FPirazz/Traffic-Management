package acme;

import cartago.*;
import com.kitfox.svg.A;

import java.util.*;

public class Intersection extends Artifact {

    private List<String> ids;
    private List<String> tempIds;

    void init() {
        this.ids = new ArrayList<>();
        this.tempIds = new ArrayList<>();

        log("Intersection is ready");
    }

    @OPERATION
    public void storeTrafficLightId(final String id) {
        this.ids.add(id);
        log("Added TR Id: " + id);
        if(ids.size() == 4) {
            Collections.sort(ids);
            this.startTrafficLight(ids);
        }
    }

    @OPERATION
    private void changeAllPace(final String pace, final String id) {
        log("TL " + id + " - With pace " + pace);

        if(pace.equals("emergencyOn") || pace.equals("emergencyDone") || pace.equals("fastDone")){
            List<String> tmp = new ArrayList<>();
            tmp.add("1"); tmp.add("2"); tmp.add("3"); tmp.add("4");
            tmp.remove(id);

            log("Changing pace");
            log("Current pace is");
            log("TL " + id + " is " + pace);
            if(pace.equals("emergencyOn")) {
                log("Others are emergencyOff");
            } else if (pace.equals("fastDone")) {
                log("Other are fastDone");
            } else {
                log("Other are emergencyDone");
            }

            tmp.forEach(tmpId -> {
                try {
                    ArtifactId trafficLight = this.lookupArtifact("traffic_light_" + tmpId);
                    if(pace.equals("emergencyOn")) {
                        execLinkedOp(trafficLight, "changePace", "emergencyOff");
                    } else if (pace.equals("fastDone")) {
                        execLinkedOp(trafficLight, "changePace", "fastDone");
                    } else {
                        execLinkedOp(trafficLight, "changePace", "emergencyDone");
                    }

                } catch (OperationException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {

            String pace1_3;
            String pace2_4;
            if (pace.equals("fast")) {
                if (Integer.parseInt(id) % 2 == 1) {
                    pace1_3 = "fast";
                    pace2_4 = "slow";
                } else {
                    pace1_3 = "slow";
                    pace2_4 = "fast";
                }
            } else {
                pace1_3 = "test";
                pace2_4 = "test";
            }

            log("Changing pace");
            log("Current pace is");
            log("TL1-3 " + pace1_3);
            log("TL2-4 " + pace2_4);

            ArtifactId trafficLight;
            try {
                trafficLight = this.lookupArtifact("traffic_light_1");
                execLinkedOp(trafficLight, "changePace", pace1_3);

                trafficLight = this.lookupArtifact("traffic_light_2");
                execLinkedOp(trafficLight, "changePace", pace2_4);

                trafficLight = this.lookupArtifact("traffic_light_3");
                execLinkedOp(trafficLight, "changePace", pace1_3);

                trafficLight = this.lookupArtifact("traffic_light_4");
                execLinkedOp(trafficLight, "changePace", pace2_4);
            } catch (OperationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OPERATION
    public void trIsDone(final String id) {
        this.tempIds.add(id);
        this.ids.remove(id);

        if(tempIds.size() == 2 && ids.size() == 2) {
            Collections.sort(tempIds);
            try {
                ArtifactId trafficLight = this.lookupArtifact("traffic_light_" + ids.get(0));
                execLinkedOp(trafficLight, "preGreenState");

                trafficLight = this.lookupArtifact("traffic_light_" + ids.get(1));
                execLinkedOp(trafficLight, "preGreenState");
            } catch (OperationException e) {
                throw new RuntimeException(e);
            }
            ids.addAll(tempIds);
            Collections.sort(ids);
            tempIds.clear();
        }
    }

    @OPERATION
    private void startTrafficLight(final List<String> ids) {
        try {
            ArtifactId trafficLight = this.lookupArtifact("traffic_light_" + ids.get(0));
            execLinkedOp(trafficLight, "preGreenState");

            trafficLight = this.lookupArtifact("traffic_light_" + ids.get(2));
            execLinkedOp(trafficLight, "preGreenState");

        } catch (OperationException e) {
            throw new RuntimeException(e);
        }
    }

}
