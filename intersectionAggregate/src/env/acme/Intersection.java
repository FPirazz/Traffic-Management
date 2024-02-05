package acme;

import cartago.Artifact;
import cartago.OPERATION;
import com.kitfox.svg.A;

import java.util.ArrayList;
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
    }

}
