package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.R
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.adapter.LessonScoresRecyclerViewAdapter
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.databinding.ActivityLessonScoreRecordScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.model.LessonScores
import org.json.JSONObject

class LessonScoreRecordScreenActivity : AppCompatActivity() {

    private lateinit var bindingLessonScoreRecord : ActivityLessonScoreRecordScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLessonScoreRecord = ActivityLessonScoreRecordScreenBinding.inflate(layoutInflater)
        val view = bindingLessonScoreRecord.root
        setContentView(view)

        bindingLessonScoreRecord.toolbarLessonScoreRecord.title = "Lesson Score Record"
        setSupportActionBar(bindingLessonScoreRecord.toolbarLessonScoreRecord)

        bindingLessonScoreRecord.buttonSave.setOnClickListener {
            val lesson_name = bindingLessonScoreRecord.editTextLesson.text.toString().trim()
            val score1 = bindingLessonScoreRecord.editTextScore1.text.toString().trim()
            val score2 = bindingLessonScoreRecord.editTextScore2.text.toString().trim()

            if(TextUtils.isEmpty(lesson_name)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter Lesson Name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(score1)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter 1st Score", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(score2)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter 2nd Score", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lessonScoreRecord(lesson_name, score1.toInt() , score2.toInt())

            val intent = Intent(this@LessonScoreRecordScreenActivity, MainScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun lessonScoreRecord(lesson_name:String, score1:Int, score2:Int) {
        val url = "http://kasimadalan.pe.hu/notlar/insert_not.php"
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response->

        }, Response.ErrorListener {       }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["ders_adi"] = lesson_name
                params["not1"] = score1.toString()
                params["not2"] = score2.toString()
                return params
            }
        }
        Volley.newRequestQueue(this@LessonScoreRecordScreenActivity).add(request)
    }
}