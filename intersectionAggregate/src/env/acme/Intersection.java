package acme;

import cartago.*;
import com.kitfox.svg.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Intersection extends Artifact {

    private List<String> ids;


    void init() {
        this.ids = new ArrayList<>();
        log("Intersection is ready");
    }

    @OPERATION
    public void storeTrafficLightId(final String id) {
        this.ids.add(id);
        log("Added TR Id: " + id);
        if(ids.size() == 4) {
            Collections.sort(ids);
        }
    }

    @OPERATION
    private void startTrafficLight(final List<String> ids) {
        try {
            ArtifactId trafficLight = this.lookupArtifact("traffic_light_" + ids.get(0));
            execLinkedOp(trafficLight, "greenState");
        } catch (OperationException e) {
            throw new RuntimeException(e);
        }
    }

}
