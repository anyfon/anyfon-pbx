package ru.anyfon.pbx.resource.subscriber

import ru.anyfon.pbx.config.ApiRouteConfig
import ru.anyfon.pbx.resource.ResourceHandler

interface SubscriberHandler : ResourceHandler {

    companion object {

        const val BASE_PATH = ApiRouteConfig.BASE_API_PATH.plus("/sip/subscriber")

        const val ID_PARAM = "subscriberId"

        const val FETCH_LIST_PATH = BASE_PATH.plus("/fetch-list")

        const val FETCH_ONE_PATH = BASE_PATH.plus("/fetch-one/{${ID_PARAM}}")

        const val ADD_PATH = BASE_PATH.plus("/add")

    }

}
