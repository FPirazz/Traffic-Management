rootProject.name = "Traffic_Management_System"
include("src:main:userContext")
findProject(":src:main:userContext")?.name = "userContext"
