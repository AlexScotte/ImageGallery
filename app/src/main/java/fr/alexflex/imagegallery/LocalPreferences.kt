package fr.alexflex.imagegallery

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class LocalPreferences(private val context: Context) {


    var sharedPreserences = context.getSharedPreferences("fr.alexflex.imagegallery", Context.MODE_PRIVATE)

    var timerValue:Int
    get(){
        return sharedPreserences.getInt("timerValue", 3)
    }
    set(value){
        sharedPreserences.edit().putInt("timerValue", value).apply()
    }
}