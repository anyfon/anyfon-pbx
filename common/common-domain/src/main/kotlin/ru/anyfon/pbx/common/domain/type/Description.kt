package ru.anyfon.pbx.common.domain.type

import ru.anyfon.common.util.StringUtils
import ru.anyfon.pbx.common.domain.Value

class Description(text: String?) : Value.AsString(text, StringUtils.ALLOWED_HTML_SYMBOLS_PATTERN)
