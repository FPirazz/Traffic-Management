tr_is_red(S)
    :- trState2(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState2(S1) & S1 == "yellow".

tr_is_green(S)
    :- trState2(S1) & S1 == "green".



!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_2", "acme.TrafficLight", ["2"], Tr);
       focus(Tr);
       println("Traffic Light Ready!");
       !trState2("red").




+!trState2(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       greenState;
       !trState2(S1).

+!trState2(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState;
       !trState2(S1).

+!trState2(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState;
       !trState2(S1).




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }