package ru.anyfon.pbx.common.domain

import javax.xml.crypto.Data

interface Response {
    val requestId: RequestId

    class Success<Result>(
        val result: Result,
        override val requestId: RequestId
    ) : Response

    class Failed<Data>(
        val error: Error<Data>,
        override val requestId: RequestId
    ) : Response {

        constructor(
            message: String,
            requestId: RequestId,
            data: Data? = null
        ) : this(Error(message, data), requestId)

    }

    class Error<Data>(
        val message: String,
        val data: Data?
    )
}
