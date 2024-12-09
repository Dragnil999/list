package ru.dianapak.list.core.list

interface SingleLinkedList<T : Comparable<T>> {

    val size: Int

    fun insert(value: T)

    fun insert(value: T, pos: Int)

    fun remove(pos: Int)

    fun get(pos: Int): T

    fun forEach(action: (T) -> Unit)

    fun quickSort()

    fun toList(): List<T>
}