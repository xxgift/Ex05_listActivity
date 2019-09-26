package com.egco428.ex05_listactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val courseTitle = intent.getStringExtra("courseTitle")
        val courseDesc = intent.getStringExtra("courseDesc")
        val courseImg = intent.getIntExtra("courseIcon",0)
        val courseNumber = intent.getIntExtra("courseNumber",0)
        val courseCredit = intent.getDoubleExtra("courseCredit",0.toDouble())
        var res = this.resources.getIdentifier("image1010$courseImg","drawable",this.packageName)
        title_Text.text = courseTitle
        descriptiontext.text = courseDesc
        courseIcon.setImageResource(res)
        courseNo.text = "Course No#: \n$courseNumber"
        creditt.text = "Credits: \n$courseCredit"
    }
}
