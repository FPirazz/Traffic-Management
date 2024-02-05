package acme;

import cartago.Artifact;
import com.kitfox.svg.A;

import java.util.ArrayList;
import java.util.List;

public class Intersection extends Artifact {

    private List<String> ids;


    void init() {
        this.ids = new ArrayList<>();
    }

}
