tr_is_red(S)
    :- trState2(S1) & S1 == "red".

tr_is_yellow(S)
    :- trState2(S1) & S1 == "yellow".

tr_is_preGreen(S)
    :- trState2(S1) & S1 == "preGreen".

tr_is_green(S)
    :- trState2(S1) & S1 == "green".

!initTrafficLight.

+!initTrafficLight
    <- println("Setting up Traffic Light...");
       makeArtifact("traffic_light_2", "acme.TrafficLight", ["2"], Tr2);
       focus(Tr2);
       .wait(500);
       sendId;
       println("Traffic Light Ready!").

+!routine_traffic_light(S)
    <- !trState2(S).




+!trState2(S): tr_is_red(S)
    <- //println("--- Traffic Light is Red ---");
       preGreenState.


+!trState2(S): tr_is_preGreen(S)
    <- //println("--- Traffic Light is Pre Green ---");
       greenState.


+!trState2(S): tr_is_green(S)
    <- //println("--- Traffic Light is Green ---");
       yellowState.


+!trState2(S): tr_is_yellow(S)
    <- //println("--- Traffic Light is Yellow ---");
       redState.

@change_temp_plan [atomic]
+trState2(T) : target_state2(T2) & not tr_is_red(T2) & not .intend(trState2(T2))
    <- !trState2(T2).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }