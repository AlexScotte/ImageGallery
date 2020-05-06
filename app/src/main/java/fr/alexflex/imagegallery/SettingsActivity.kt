package fr.alexflex.imagegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        timerValueSeekBar.progress = intent.getIntExtra("currentTimerValue", 1)
        timerValueSeekBar?.setOnSeekBarChangeListener(this)
        displayTimerValue(timerValueSeekBar.progress)
    }

    override fun onProgressChanged(
        seek: SeekBar,
        progress: Int, fromUser: Boolean
    ) {

        displayTimerValue(timerValueSeekBar.progress)
        LocalPreferences(this).timerValue = progress
    }

    override fun onStartTrackingTouch(seek: SeekBar) {
    }

    override fun onStopTrackingTouch(seek: SeekBar) {
    }

    private fun displayTimerValue(progress:Int){

        timerValueTextView.text = "$progress s"
    }
}
