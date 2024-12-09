package ru.dianapak.list.view

class StringActivity : BaseListActivity<String>() {

    override val parser: ValueParser<String> = ValueParser { value ->
        value
    }
    override val type: String = "String"

    override val valueInputHint: String = "String, e.g. qwerty"
}