tr_is_red(S)
    :- trState(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState(S1) & S1 == "yellow".

tr_is_green(S)
    :- trState(S1) & S1 == "green".



!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light", "acme.TrafficLight", [], Tr);
       focus(Tr);
       println("Traffic Light Ready!");
       !trState("red").




+!trState(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       greenState;
       !trState(S1).

+!trState(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState;
       !trState(S1).

+!trState(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState;
       !trState(S1).




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }