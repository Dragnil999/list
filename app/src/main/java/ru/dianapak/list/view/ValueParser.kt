package ru.dianapak.list.view

fun interface ValueParser<T : Comparable<T>> {

    fun parse(text: String): T
}