rootProject.name = "Traffic_Management_System"
include(":userContext")
project(":userContext").projectDir = file("./src/main/userContext")
include("intersectionAggregate")
project(":intersectionAggregate").projectDir = file("./intersectionAggregate")
include("tcm_frontend")
project(":tcm_frontend").projectDir = file("./tcm_frontend")