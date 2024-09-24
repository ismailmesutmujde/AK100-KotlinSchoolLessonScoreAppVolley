package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.R
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.databinding.ActivityLessonScoreRecordScreenBinding

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

            if(TextUtils.isEmpty(score1)){
                Snackbar.make(bindingLessonScoreRecord.toolbarLessonScoreRecord, "Enter 2nd Score", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this@LessonScoreRecordScreenActivity, MainScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}