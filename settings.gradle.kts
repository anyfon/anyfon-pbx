rootProject.name = "anyfon-pbx"

include(":common:common-base")
include(":common:common-domain")
include(":common:common-data-r2dbc")
include(":common:common-web")

include(":web:web-app")
include(":web:web-app-js")
include(":web:web-starter-spring")
include(":web:web-auth-domain")
include(":web:web-auth-data-r2dbc")


include(":asterisk-api:asterisk-api-app-web-spring")
include(":asterisk-api:asterisk-api-domain")
include(":asterisk-api:asterisk-api-data-r2dbc")

include(":pbx-manager:pbx-manager-app-web")
include(":pbx-manager:pbx-manager-domain")
include(":pbx-manager:pbx-manager-data-r2dbc")
