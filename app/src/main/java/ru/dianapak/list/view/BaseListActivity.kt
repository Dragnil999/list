package ru.dianapak.list.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ru.dianapak.list.R
import ru.dianapak.list.core.list.SingleLinkedList
import ru.dianapak.list.core.list.SingleLinkedListImpl
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

abstract class BaseListActivity<T : Comparable<T>> : AppCompatActivity() {

    private lateinit var list: RecyclerView
    private lateinit var valueInput: TextInputEditText
    private lateinit var indexInput: TextInputEditText
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var sortButton: Button
    private lateinit var serializeButton: Button
    private lateinit var deserializeButton: Button

    private var singleLinkedList: SingleLinkedList<T> = SingleLinkedListImpl()

    abstract val parser: ValueParser<T>
    abstract val type: String
    abstract val valueInputHint: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupActionBar()
        initViews()
        initRecyclerView()
        initListeners()
    }

    private fun initViews() {
        list = findViewById(R.id.list)
        valueInput = findViewById(R.id.valueInput)
        indexInput = findViewById(R.id.indexInput)
        addButton = findViewById(R.id.addButton)
        removeButton = findViewById(R.id.removeButton)
        sortButton = findViewById(R.id.sortButton)
        serializeButton = findViewById(R.id.serializeButton)
        deserializeButton = findViewById(R.id.deserializeButton)

        valueInput.hint = valueInputHint
    }

    private fun setupActionBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(type)
        toolbar.setNavigationIcon(R.drawable.arrow_back)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        list.adapter = ListAdapter(singleLinkedList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initListeners() {
        addButton.setOnClickListener {
            val value = valueInput.text?.toString()
            val index = indexInput.text?.toString()
            if (value.isNullOrBlank()) {
                showDialog("Value is empty")
                return@setOnClickListener
            }

            if (index.isNullOrBlank()) {
                try {
                    singleLinkedList.insert(parser.parse(value))
                    list.adapter?.notifyDataSetChanged()
                    list.smoothScrollToPosition(singleLinkedList.size - 1)
                } catch (_: NumberFormatException) {
                    showDialog("Value isn't $type")
                }
            } else {
                try {
                    singleLinkedList.insert(parser.parse(value), index.toInt())
                    list.adapter?.notifyDataSetChanged()
                    list.smoothScrollToPosition(index.toInt())
                } catch (_: NumberFormatException) {
                    showDialog("Value isn't $type or index isn't Integer")
                }
            }
        }

        removeButton.setOnClickListener {
            val index = indexInput.text?.toString()
            if (index.isNullOrBlank()) {
                showDialog("Index is empty")
                return@setOnClickListener
            } else {
                try {
                    singleLinkedList.remove(index.toInt())
                    list.adapter?.notifyDataSetChanged()
                    list.smoothScrollToPosition(index.toInt())
                } catch (_: NumberFormatException) {
                    showDialog("Index isn't Integer")
                } catch (_: IndexOutOfBoundsException) {
                    showDialog("Index out of bounds")
                }
            }
        }

        sortButton.setOnClickListener {
            singleLinkedList.quickSort()
            list.adapter?.notifyDataSetChanged()
        }

        serializeButton.setOnClickListener {
            val file = File(filesDir, type)
            file.outputStream().use { fos ->
                val oos = ObjectOutputStream(fos)
                oos.writeObject(singleLinkedList)
            }
        }

        deserializeButton.setOnClickListener {
            try {
                val file = File(filesDir, type)
                file.inputStream().use { fis ->
                    val ois = ObjectInputStream(fis)
                    val restoredList = ois.readObject() as? SingleLinkedList<T>
                    restoredList?.let {
                        singleLinkedList = it
                        list.adapter = ListAdapter(singleLinkedList)
                    }
                }
            } catch (_: Exception) {
            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}