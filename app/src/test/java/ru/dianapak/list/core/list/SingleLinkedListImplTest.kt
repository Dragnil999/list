package ru.dianapak.list.core.list

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class SingleLinkedListImplTest {

    @Test
    fun testCreatedListIsEmpty() {
        val list = SingleLinkedListImpl<Int>()
        assertEquals(0, list.size)
    }

    @Test
    fun testInsertSizeIncreased() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(0)
        assertEquals(1, list.size)
    }

    @Test
    fun testRemoveElementSizeDecreased() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(0)
        list.insert(1)
        list.remove(1)
        assertEquals(1, list.size)
    }

    @Test
    fun testRemoveSingleElementSizeIsZero() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(0)
        list.remove(0)
        assertEquals(0, list.size)
    }

    @Test
    fun testRemoveElementFromEmptyListThrowsIndexOutOfBoundsException() {
        val list = SingleLinkedListImpl<Int>()
        assertThrows(IndexOutOfBoundsException::class.java) {
            list.remove(0)
        }
    }

    @Test
    fun testInsertByPosElementIsInRightPosition() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        list.insert(12, 1)
        assertEquals(12, list.get(1))
    }

    @Test
    fun testInsertToLastPosElementIsInRightPosition() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        list.insert(12, 5)
        assertEquals(12, list.get(list.size - 1))
    }

    @Test
    fun testInsertInFirstPosElementInRightPosition() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        list.insert(12, 0)
        assertEquals(12, list.get(0))
    }

    @Test
    fun testInsertByPosToEmptyListElementInTheBeginning() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(1, 0)
        assertEquals(1, list.get(0))
    }

    @Test
    fun testInsertByPosToInvalidPositionThrownIndexOutOfBoundsException() {
        val list = SingleLinkedListImpl<Int>()
        assertThrows(IndexOutOfBoundsException::class.java) {
            list.insert(12, 12)
        }
    }

    @Test
    fun testRemoveElementIsGone() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        list.remove(4)
        list.forEach {
            assertNotEquals(4, it)
        }
    }

    @Test
    fun testRemoveElementFromTheMiddleAndItIsGone() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        list.remove(2)
        list.forEach {
            assertNotEquals(2, it)
        }
    }

    @Test
    fun testGetElementFromEmptyListThrowsIndexOutOfBoundsException() {
        val list = SingleLinkedListImpl<Int>()
        assertThrows(IndexOutOfBoundsException::class.java) {
            list.get(0)
        }
    }

    @Test
    fun testGetElementFromInvalidPositionThrowsIndexOutOfBoundsException() {
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            list.get(12)
        }
    }

    @Test
    fun testToList() {
        val expected = listOf(0, 1, 2, 3, 4)
        val list = SingleLinkedListImpl<Int>()
        for (i in 0..< 5) {
            list.insert(i)
        }
        val actual = list.toList()
        assertEquals(expected, actual)
    }

    @Test
    fun testSortEmptyListNothingChanged() {
        val list = SingleLinkedListImpl<Int>()
        list.quickSort()
        assertEquals(emptyList<Int>(), list.toList())
    }

    @Test
    fun testSortListWithSingleElementNothingChanged() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(1)
        list.quickSort()
        assertEquals(listOf(1), list.toList())
    }

    @Test
    fun testSortListIsSorted() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(1)
        list.insert(14)
        list.insert(3)
        list.insert(2)
        list.quickSort()
        assertEquals(listOf(1, 2, 3, 14), list.toList())
    }

    @Test
    fun testSortAndMaxValInBeginningListIsSorted() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(14)
        list.insert(1)
        list.insert(3)
        list.insert(2)
        list.quickSort()
        assertEquals(listOf(1, 2, 3, 14), list.toList())
    }

    @Test
    fun testSortAndMaxValInMiddleListIsSorted() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(1)
        list.insert(3)
        list.insert(-1)
        list.insert(14)
        list.insert(2)
        list.quickSort()
        assertEquals(listOf(-1, 1, 2, 3, 14), list.toList())
    }

    @Test
    fun testSortAndMaxValInTheEndListIsSorted() {
        val list = SingleLinkedListImpl<Int>()
        list.insert(1)
        list.insert(3)
        list.insert(2)
        list.insert(14)
        list.quickSort()
        assertEquals(listOf(1, 2, 3, 14), list.toList())
    }
}