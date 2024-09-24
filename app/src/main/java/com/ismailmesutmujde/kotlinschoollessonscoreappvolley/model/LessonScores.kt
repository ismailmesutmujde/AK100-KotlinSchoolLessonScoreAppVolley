package com.ismailmesutmujde.kotlinschoollessonscoreappvolley.model

import java.io.Serializable

data class LessonScores (var lesson_id : Int, var lesson_name : String, var score1 : Int, var score2 : Int) : Serializable {

}