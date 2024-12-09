package ru.dianapak.list

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.dianapak.list.view.DoubleActivity
import ru.dianapak.list.view.IntegerActivity
import ru.dianapak.list.view.PointActivity
import ru.dianapak.list.view.StringActivity

class MainActivity : AppCompatActivity() {

    private lateinit var integerMenuButton: Button
    private lateinit var doubleMenuButton: Button
    private lateinit var stringMenuButton: Button
    private lateinit var pointMenuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        integerMenuButton = findViewById(R.id.integerMenuButton)
        doubleMenuButton = findViewById(R.id.doubleMenuButton)
        stringMenuButton = findViewById(R.id.stringMenuButton)
        pointMenuButton = findViewById(R.id.pointMenuButton)

        initListeners()
    }

    private fun initListeners() {
        integerMenuButton.setOnClickListener {
            val intent = Intent(this, IntegerActivity::class.java)
            startActivity(intent)
        }

        doubleMenuButton.setOnClickListener {
            val intent = Intent(this, DoubleActivity::class.java)
            startActivity(intent)
        }

        stringMenuButton.setOnClickListener {
            val intent = Intent(this, StringActivity::class.java)
            startActivity(intent)
        }

        pointMenuButton.setOnClickListener {
            val intent = Intent(this, PointActivity::class.java)
            startActivity(intent)
        }
    }
}