package fr.alexflex.imagegallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var images = emptyList<Int>()
    var currentIndex = 0
    var countDownTimer : CountDownTimer? = null
    var currentTimerValue:Int = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        images = listOf(R.drawable.iron_man_1, R.drawable.iron_man_2, R.drawable.iron_man_3, R.drawable.iron_man_4)
        currentIndex = savedInstanceState?.getInt("currentIndex") ?: 0
        imageView.setImageResource(images[currentIndex])
        reloadCounter()
    }

    override fun onResume() {
        super.onResume()
        reloadCounter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt("currentIndex", currentIndex)
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

        reloadCounter()
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

        reloadCounter()
    }

    private fun reloadCounter(){

        stopCounter()
        currentTimerValue = LocalPreferences(this).timerValue * 1000
        countDownTimer = object : CountDownTimer(currentTimerValue.toLong(), 1000) {
            override fun onFinish() {

                onNextButtonClicked(nextBtn)
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
        countDownTimer?.start()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item != null){
            when(item.itemId){
                R.id.settings -> displaySettingsActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun displaySettingsActivity(){

        stopCounter()
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra("currentTimerValue", currentTimerValue/1000)
        startActivity(intent)
    }

    private fun stopCounter(){
        if(countDownTimer != null){
            countDownTimer?.cancel()
            countDownTimer = null
        }
    }
}
