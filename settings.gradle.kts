rootProject.name = "oauth2-server"

include("main")

include("domain")

include("tests")
project(":tests").projectDir = File("tests")

include("persistence")
project(":persistence").projectDir = File("secondary/persistence")

include("rest")
project(":rest").projectDir = File("primary/rest")

include("consumer")
project(":consumer").projectDir = File("primary/consumer")
