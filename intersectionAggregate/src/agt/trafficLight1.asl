tr_is_red(S)
    :- trState1(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState1(S1) & S1 == "yellow".

tr_is_green(S)
    :- trState1(S1) & S1 == "green".



!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_1", "acme.TrafficLight", ["1"], Tr1);
       focus(Tr1);
       println("Traffic Light Ready!");
       !trState1("red").




+!trState1(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       greenState;
       !trState1(S1).

+!trState1(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState;
       !trState1(S1).

+!trState1(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState;
       !trState1(S1).




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }