rootProject.name = "Traffic_Management_System"
include(":userContext")
project(":userContext").projectDir = file("./src/main/userContext")
include("intersectionAggregate")
