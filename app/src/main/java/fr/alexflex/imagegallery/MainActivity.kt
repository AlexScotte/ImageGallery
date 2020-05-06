package fr.alexflex.imagegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var images = emptyList<Int>()
    var currentIndex = 0
    var countDownTimer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images = listOf(R.drawable.iron_man_1, R.drawable.iron_man_2, R.drawable.iron_man_3, R.drawable.iron_man_4)

        currentIndex = savedInstanceState?.getInt("currentIndex") ?: 0
        imageView.setImageResource(images[currentIndex])
        ReloadCounter()
    }

    override fun onResume() {
        super.onResume()
        ReloadCounter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt("currentIndex", currentIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        currentIndex = savedInstanceState?.getInt("currentIndex") ?: 0
    }

    fun onNextButtonClicked(button: View){

        if(currentIndex == images.count() - 1){

            currentIndex = 0
            imageView.setImageResource(images[currentIndex])
        }
        else{
            currentIndex++
            imageView.setImageResource(images[currentIndex])

        }

        ReloadCounter()
    }

    fun onPreviousButtonClicked(button: View){

        if(currentIndex == 0){

            currentIndex = images.count() - 1
            imageView.setImageResource(images[currentIndex])
        }
        else{
            currentIndex--
            imageView.setImageResource(images[currentIndex])
        }

        ReloadCounter()
    }

    private fun ReloadCounter(){

        if(countDownTimer != null){
            countDownTimer?.cancel()
            countDownTimer = null
        }

        countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onFinish() {

                onNextButtonClicked(nextBtn)
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
        countDownTimer?.start()
    }
}
