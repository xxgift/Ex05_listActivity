package com.egco428.ex05_listactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val iterator = data.iterator()
        val builder = StringBuilder()
        while(iterator.hasNext()){
        val course = iterator.next()
           builder.append(course.title).append("\n")
       }
       courseListView.text = builder.toString()*/

        val data = DataProvider.getData()
        val courseArrayAdapter = CourseArrayAdapter(this,R.drawable.image10101,data)
        list.setAdapter(courseArrayAdapter)
        list.setOnItemClickListener { adapterView, view, position, id ->
            val course = data.get(position)
            displayDetail(course,position)
        }
    }

    private fun displayDetail(course: Course, position: Int){
        //Log.d("CourseCatelog","Course: ${course.title}")
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle",course.title)
        intent.putExtra("courseDesc",course.description)
        intent.putExtra("courseIcon",position%3+1)
        intent.putExtra("courseNumber",course.courseNumber)
        intent.putExtra("courseCredit",course.credits)

        startActivity(intent)
    }
    private class CourseArrayAdapter(var context: Context, var resource: Int, var objects: ArrayList<Course>): BaseAdapter(){

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
            val course = objects[position]
            val courseItem:View

            if(convertView == null){
                val layoutInflator = LayoutInflater.from(parent!!.context)
                courseItem = layoutInflator.inflate(R.layout.course_item , parent, false)
                val viewHolder = ViewHolder(courseItem.title_Text,courseItem.imageCourse)
                courseItem.tag = viewHolder
            }else{
                courseItem = convertView
            }

            val viewHolder = courseItem.tag as ViewHolder
            val ImgPos = position%3+1
            var res = context.resources.getIdentifier("image1010$ImgPos","drawable",context.packageName)
            viewHolder.imageView.setImageResource(res)
            /*when(position%3){
                0 -> viewHolder.imageView.setImageResource(R.drawable.image10101)
                1 -> viewHolder.imageView.setImageResource(R.drawable.image10102)
                2 -> viewHolder.imageView.setImageResource(R.drawable.image10103)

            }*/
            viewHolder.titleTextView.text = objects.get(position).title
        return courseItem
        }
        }
        private class ViewHolder(val titleTextView: TextView, val imageView: ImageView)
    }


