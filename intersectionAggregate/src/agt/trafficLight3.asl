tr_is_red(S)
    :- trState3(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState3(S1) & S1 == "yellow".

tr_is_green(S)
    :- trState3(S1) & S1 == "green".



!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_3", "acme.TrafficLight", ["3"], Tr3);
       focus(Tr3);
       .wait(500);
       sendId;
       println("Traffic Light Ready!").




+!trState3(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       greenState;
       !trState3(S1).

+!trState3(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState;
       !trState3(S1).

+!trState3(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState.




{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }