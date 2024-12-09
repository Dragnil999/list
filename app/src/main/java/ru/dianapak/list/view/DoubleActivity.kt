package ru.dianapak.list.view

class DoubleActivity : BaseListActivity<Double>() {

    override val parser: ValueParser<Double> = ValueParser { value ->
        value.toDouble()
    }
    override val type: String = "Double"

    override val valueInputHint: String = "Double, e.g. 1.15"
}