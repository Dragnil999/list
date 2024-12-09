package ru.dianapak.list.core.list

import java.io.Serializable
import java.util.Collections

class SingleLinkedListImpl<T : Comparable<T>> : SingleLinkedList<T>, Serializable {

    override var size: Int = 0
        private set

    private var head: Node<T>? = null

    override fun insert(value: T) {
        if (size == 0) {
            head = Node(value)
            size++
            return
        }
        insertAfterTail(value)
    }

    override fun insert(value: T, pos: Int) {
        if (pos == size) {
            insert(value)
            return
        }
        checkPos(pos)
        val oldNode = getNode(pos)
        oldNode?.next = Node(oldNode?.value!!, oldNode.next!!)
        oldNode.value = value
        size++
    }

    override fun remove(pos: Int) {
        checkPos(pos)
        if (pos == 0) {
            head = head?.next
            size--
            return
        }
        val beforeNode = getNode(pos - 1)
        beforeNode?.next = beforeNode?.next?.next
        size--
    }

    override fun get(pos: Int): T {
        checkPos(pos)
        return getNode(pos)?.value!!
    }

    override fun forEach(action: (T) -> Unit) {
        var current: Node<T>? = head
        while (current != null) {
            action(current.value)
            current = current.next
        }
    }

    override fun quickSort() {
        val list = toList().toMutableList()
        quickSort(list, 0, list.size - 1)
        var current: Node<T>? = head
        list.forEach { value ->
            current?.value = value
            current = current?.next
        }
    }

    private fun quickSort(list: MutableList<T>, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = partition(list, low, high)

            quickSort(list, low, pivotIndex - 1)
            quickSort(list, pivotIndex + 1, high)
        }
    }

    private fun partition(list: MutableList<T>, low: Int, high: Int): Int {
        val pivot = list[high]
        var i = low - 1

        for (j in low..< high) {
            if (list[j].compareTo(pivot) <= 0) {
                i++
                Collections.swap(list, i, j)
            }
        }
        Collections.swap(list, i + 1, high)
        return i + 1
    }

    override fun toList(): List<T> {
        val list = emptyList<T>().toMutableList()
        forEach(list::add)
        return list
    }

    private fun insertAfterTail(value: T) {
        getTail()?.next = Node(value)
        size++
    }

    private fun getTail(): Node<T>? {
        var current = head
        while (current?.next != null) {
            current = current.next!!
        }
        return current
    }

    private fun getNode(pos: Int): Node<T>? {
        var current = head
        for (i in 0..< pos) {
            current = current?.next
        }
        return current
    }

    private fun checkPos(pos: Int) {
        if (size == 0) {
            throw IndexOutOfBoundsException("List is empty")
        }
        if (pos < 0 || pos >= size) {
            throw IndexOutOfBoundsException("Position $pos is out of bounds (0, ${size - 1})")
        }
    }

    private companion object {

        class Node<T>(
            var value: T,
            var next: Node<T>?,
        ) : Serializable {

            constructor(value: T) : this(value, null)
        }
    }
}