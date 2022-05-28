package ru.anyfon.pbx.common.domain

interface FilterParams {
    val offset: Int
    val limit: Int
    val sortBy: Iterable<String>
}
