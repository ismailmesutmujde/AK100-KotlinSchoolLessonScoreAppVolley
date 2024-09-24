package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.R
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.model.LessonScores
import com.ismailmesutmujde.kotlinschoollessonscoreappvolley.view.LessonScoreDetailScreenActivity

class LessonScoresRecyclerViewAdapter (private val mContext: Context, private val lessonScoreList:List<LessonScores>)
    : RecyclerView.Adapter<LessonScoresRecyclerViewAdapter.CardDesignHolder>(){

    inner class CardDesignHolder(view: View) : RecyclerView.ViewHolder(view) {
        var lessonScore_card: CardView
        var textViewLesson: TextView
        var textViewScore1: TextView
        var textViewScore2: TextView

        init {
            lessonScore_card = view.findViewById(R.id.lessonScore_card)
            textViewLesson = view.findViewById(R.id.textViewLesson)
            textViewScore1 = view.findViewById(R.id.textViewScore1)
            textViewScore2 = view.findViewById(R.id.textViewScore2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design, parent,false)
        return CardDesignHolder(design)
    }

    override fun getItemCount(): Int {
        return lessonScoreList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val lessonScore = lessonScoreList.get(position)

        holder.textViewLesson.text = lessonScore.lesson_name
        holder.textViewScore1.text = (lessonScore.score1).toString()
        holder.textViewScore2.text = (lessonScore.score2).toString()

        holder.lessonScore_card.setOnClickListener {
            val intent = Intent(mContext, LessonScoreDetailScreenActivity::class.java)
            intent.putExtra("obj",lessonScore)
            mContext.startActivity(intent)
        }

    }


}