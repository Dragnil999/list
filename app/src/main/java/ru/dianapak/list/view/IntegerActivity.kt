package ru.dianapak.list.view

class IntegerActivity : BaseListActivity<Int>() {

    override val parser: ValueParser<Int> = ValueParser { value ->
        value.toInt()
    }
    override val type: String = "Integer"

    override val valueInputHint: String = "Integer, e.g. 1"
}