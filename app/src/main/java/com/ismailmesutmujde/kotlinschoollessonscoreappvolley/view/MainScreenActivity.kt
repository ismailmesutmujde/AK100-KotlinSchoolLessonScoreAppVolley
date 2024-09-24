package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.R
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.adapter.LessonScoresRecyclerViewAdapter
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.model.LessonScores
import org.json.JSONObject


class MainScreenActivity : AppCompatActivity() {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var lessonScoreList:ArrayList<LessonScores>
    private lateinit var adapter: LessonScoresRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Lesson Score App"
        bindingMainScreen.toolbar.subtitle = "Average : 0"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        /*
        lessonScoreList = ArrayList()

        val l1 = LessonScores(1,"History", 40, 80)
        val l2 = LessonScores(2,"Chemistry", 70, 50)
        val l3 = LessonScores(3,"Physics",30,60)

        lessonScoreList.add(l1)
        lessonScoreList.add(l2)
        lessonScoreList.add(l3)

        adapter = LessonScoresRecyclerViewAdapter(this, lessonScoreList)
        bindingMainScreen.recyclerView.adapter = adapter*/

        allLessonScores()

        bindingMainScreen.fab.setOnClickListener {
            val intent = Intent(this@MainScreenActivity, LessonScoreRecordScreenActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun allLessonScores() {
        val url = "http://kasimadalan.pe.hu/notlar/tum_notlar.php"
        val request = StringRequest(Request.Method.GET, url, Response.Listener { response->
            try {
                var sum = 0

                lessonScoreList = ArrayList()

                val jsonObject = JSONObject(response)
                val lessonScores = jsonObject.getJSONArray("notlar")

                for (i in 0 until lessonScores.length()) {
                    val ls = lessonScores.getJSONObject(i)

                    val score1 = ls.getInt("not1")
                    val score2 = ls.getInt("not2")

                    val lessonScore = LessonScores(ls.getInt("not_id")
                        ,ls.getString("ders_adi"),score1,score2)
                    lessonScoreList.add(lessonScore)
                    sum = sum + (score1+score2)/2
                }

                adapter = LessonScoresRecyclerViewAdapter(this@MainScreenActivity, lessonScoreList)
                bindingMainScreen.recyclerView.adapter = adapter

                if (sum != 0) {
                    bindingMainScreen.toolbar.subtitle = "Average : ${sum/lessonScoreList.size}"
                }
            } catch (e:Exception) {
                e.printStackTrace()
            }
        },Response.ErrorListener {  })
        Volley.newRequestQueue(this@MainScreenActivity).add(request)
    }
}