package ru.anyfon.common.util

fun <T> Iterable<T>.merge(vararg value: T): List<T> = merge(value.toList())

fun <T> Iterable<T>.merge(values: Iterable<T>): List<T> {
    val list = this.toMutableList()
    list.addAll(values)
    return list.toList()
}
