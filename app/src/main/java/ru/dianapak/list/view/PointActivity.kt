package ru.dianapak.list.view

import ru.dianapak.list.core.point.Point

class PointActivity : BaseListActivity<Point>() {

    override val parser: ValueParser<Point> = ValueParser { value ->
        val coords = value.split(", ")
        val x = coords[0].toInt()
        val y = coords[1].toInt()

        Point(x, y)
    }
    override val type: String = "Point"

    override val valueInputHint: String = "Point, e.g. 2, 5"
}