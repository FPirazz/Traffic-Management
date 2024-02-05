tr_is_red(S)
    :- trState4(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState4(S1) & S1 == "yellow".

tr_is_preGreen(S)
    :- trState4(S1) & S1 == "preGreen".

tr_is_green(S)
    :- trState4(S1) & S1 == "green".

!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_4", "acme.TrafficLight", ["4"], Tr4);
       focus(Tr4);
       .wait(500);
       sendId;
       println("Traffic Light Ready!").

+!routine_traffic_light(S)
    <- !trState4(S).




+!trState4(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       preGreenState.


+!trState4(S): tr_is_preGreen(S)
    <- //println("--- Traffic Light is Pre Green ---");
       greenState.


+!trState4(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState.


+!trState4(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState.

@change_temp_plan [atomic]
+trState4(T) : target_state4(T2) & not tr_is_red(T2) & not .intend(trState4(T2))
    <- !trState4(T2).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }