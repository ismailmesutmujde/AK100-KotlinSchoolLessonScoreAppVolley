package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.R
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.databinding.ActivityLessonScoreDetailScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.model.LessonScores

class LessonScoreDetailScreenActivity : AppCompatActivity() {

    private lateinit var bindingLessonScoreDetailScreen : ActivityLessonScoreDetailScreenBinding

    private lateinit var lessonScore : LessonScores
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLessonScoreDetailScreen = ActivityLessonScoreDetailScreenBinding.inflate(layoutInflater)
        val view = bindingLessonScoreDetailScreen.root
        setContentView(view)

        bindingLessonScoreDetailScreen.toolbarLessonScoreDetail.title = "Lesson Score Detail"
        setSupportActionBar(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail)

        lessonScore = intent.getSerializableExtra("obj") as LessonScores

        bindingLessonScoreDetailScreen.editTextLessonDetail.setText(lessonScore.lesson_name)
        bindingLessonScoreDetailScreen.editTextScore1Detail.setText((lessonScore.score1).toString())
        bindingLessonScoreDetailScreen.editTextScore2Detail.setText((lessonScore.score2).toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete -> {
                Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail, "Delete it?",
                    Snackbar.LENGTH_SHORT)
                    .setAction("YES") {
                        lessonScoreDelete(lessonScore.lesson_id)
                        startActivity(Intent(this@LessonScoreDetailScreenActivity, MainScreenActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_edit -> {
                val lesson_name = bindingLessonScoreDetailScreen.editTextLessonDetail.text.toString().trim()
                val score1 = bindingLessonScoreDetailScreen.editTextScore1Detail.text.toString().trim()
                val score2 = bindingLessonScoreDetailScreen.editTextScore2Detail.text.toString().trim()

                if(TextUtils.isEmpty(lesson_name)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter Lesson Name",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(score1)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter 1st Score",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(score2)){
                    Snackbar.make(bindingLessonScoreDetailScreen.toolbarLessonScoreDetail,"Enter 2nd Score",
                        Snackbar.LENGTH_SHORT).show()
                    return false
                }

                lessonScoreUpdate(lessonScore.lesson_id, lesson_name, score1.toInt(),score2.toInt())

                val intent = Intent(this@LessonScoreDetailScreenActivity, MainScreenActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> return false
        }
    }
    fun lessonScoreUpdate(lesson_id:Int, lesson_name:String, score1:Int, score2:Int) {
        val url = "http://kasimadalan.pe.hu/notlar/update_not.php"
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response->

        }, Response.ErrorListener {       }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["not_id"] = lesson_id.toString()
                params["ders_adi"] = lesson_name
                params["not1"] = score1.toString()
                params["not2"] = score2.toString()
                return params
            }
        }
        Volley.newRequestQueue(this@LessonScoreDetailScreenActivity).add(request)
    }

    fun lessonScoreDelete(lesson_id:Int) {
        val url = "http://kasimadalan.pe.hu/notlar/delete_not.php"
        val request = object : StringRequest(Request.Method.POST, url, Response.Listener { response->

        }, Response.ErrorListener {       }){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["not_id"] = lesson_id.toString()
                return params
            }
        }
        Volley.newRequestQueue(this@LessonScoreDetailScreenActivity).add(request)
    }
}