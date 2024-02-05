tr_is_red(S)
    :- trState4(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState4(S1) & S1 == "yellow".

tr_is_green(S)
    :- trState4(S1) & S1 == "green".



!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_4", "acme.TrafficLight", ["4"], Tr);
       focus(Tr);
       println("Traffic Light Ready!");
       !trState4("red").




+!trState4(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       greenState;
       !trState4(S1).

+!trState4(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState;
       !trState4(S1).

+!trState4(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState;
       !trState4(S1).




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }