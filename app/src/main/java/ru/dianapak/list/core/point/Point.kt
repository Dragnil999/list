package ru.dianapak.list.core.point

import java.io.Serializable

data class Point(
    val x: Int,
    val y: Int,
) : Comparable<Point>, Serializable {

    override fun compareTo(point: Point): Int =
        this.x * this.x + this.y * this.y - point.x * point.x - point.y * point.y

    override fun toString() =
        "x=$x, y=$y"
}
