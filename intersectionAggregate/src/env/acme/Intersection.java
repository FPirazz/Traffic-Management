package acme;

import cartago.*;
import com.kitfox.svg.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    public void trIsDone(final String id) {
        this.tempIds.add(id);
        this.ids.remove(id);

//        log("ID " + id + " arrived");
//        log(String.valueOf(tempIds.size()));
//        log(String.valueOf(ids.size()));

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
