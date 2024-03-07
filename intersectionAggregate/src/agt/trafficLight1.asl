tr_is_red(S)
    :- trState1(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState1(S1) & S1 == "yellow".

tr_is_preGreen(S)
    :- trState1(S1) & S1 == "preGreen".

tr_is_green(S)
    :- trState1(S1) & S1 == "green".

!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_1", "acme.TrafficLight", ["1"], Tr1);
       focus(Tr1);
       .wait(500);
       sendId;
       println("Traffic Light Ready!").

+!routine_traffic_light(S)
    <- !trState1(S).




+!trState1(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       preGreenState.


+!trState1(S): tr_is_preGreen(S)
    <- //println("--- Traffic Light is Pre Green ---");
       greenState.


+!trState1(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState.


+!trState1(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState.

@change_temp_plan [atomic]
+trState1(T) : target_state1(T2) & not tr_is_red(T2) & not .intend(trState1(T2))
    <- !trState1(T2).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }