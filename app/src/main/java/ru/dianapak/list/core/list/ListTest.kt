package ru.dianapak.list.core.list

import java.io.File
import kotlin.random.Random
import kotlin.system.measureNanoTime

fun main() {
    /*val list = generateList(95000)
    saveListToFile(list)*/
    val list = getFromFile()
    var count = 5000

    repeat(19) {
        val testingList = getTestingList(list, count)

        val runtime = Runtime.getRuntime()
        val memBeforeSort = (runtime.totalMemory() - runtime.freeMemory())
        val result = measureNanoTime {
            testingList.quickSort()
        }
//        println(result)
        val memAfterSort = (runtime.totalMemory() - runtime.freeMemory())
        println(memAfterSort - memBeforeSort)

        count += 5000
    }
}

private fun getTestingList(list: List<Int>, countOfElements: Int): SingleLinkedList<Int> {
    val testingList = SingleLinkedListImpl<Int>()
    for (i in 0..<countOfElements) {
        testingList.insert(list[i])
    }
    return testingList
}

private fun getFromFile(): List<Int> {
    val file = File("/Users/dromanov/pak_diana_generated_numbers.txt")
    file.reader().use { input ->
        return input.readLines().map(String::toInt)
    }
}

private fun saveToFile(list: SingleLinkedList<Int>) {
    val file = File("/Users/dromanov/pak_diana_generated_numbers.txt")
    file.printWriter().use { out ->
        list.forEach { element ->
            out.println(element)
        }
    }
}

private fun generateList(count: Int): SingleLinkedList<Int> {
    val list = SingleLinkedListImpl<Int>()
    repeat(count) {
        list.insert(Random.nextInt(0, 500000))
    }
    return list
}