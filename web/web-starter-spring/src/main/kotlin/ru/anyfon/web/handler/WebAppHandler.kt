package ru.anyfon.web.handler

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

class WebAppHandler {
    companion object {
        private const val PATH_TO_APP_CSS = "/static/app/app.css"
        private const val PATH_TO_APP_JS = "/static/app/app.js"
    }

    private val template: String

    init {
        val builder = StringBuilder()

        builder.append("<!DOCTYPE html>")
        builder.append("<html lang=\"en\">")
        builder.append("<head>")
        builder.append("<meta charset=\"UTF-8\">")
        builder.append("<title>Anyfon PBX</title>")
        builder.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"$PATH_TO_APP_CSS\"/>")
        builder.append("</head>")
        builder.append("<body>")
        builder.append("<div id=\"desktop-web-app\"></div>")
        builder.append("<script src=\"$PATH_TO_APP_JS\"></script>")
        builder.append("</body>")
        builder.append("</html>")

        this.template = builder.toString()
    }

    suspend fun handle(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_HTML)
            .bodyValueAndAwait(template)
    }
}
