
!start.



+!start : message(X) <- .print(X).
+!start              <- .print("Hello World").

{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }
{ include("$moise/asl/org-obedient.asl") }