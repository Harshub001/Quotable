package com.task.quotable.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.task.quotable.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val cardview1 = findViewById<CardView>(R.id.card_view1)
        cardview1.setOnClickListener{
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent) }

        val cardview2 = findViewById<CardView>(R.id.card_view2)
        cardview2.setOnClickListener{
            val intent = Intent(this, AuthorsActivity::class.java)
            startActivity(intent) }

        val cardview3 = findViewById<CardView>(R.id.card_view3)
        cardview3.setOnClickListener{
            val intent = Intent(this, TagsActivity::class.java)
            startActivity(intent) }

    }

}