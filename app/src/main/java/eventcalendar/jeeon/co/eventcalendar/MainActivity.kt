package eventcalendar.jeeon.co.eventcalendar

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import java.time.format.TextStyle
import java.util.*

class MainActivity : AppCompatActivity() {

    private var calendar: Calendar ?= null
    private var selectedWeekNumber: Int  ?= null
    private var selectedDayNumber : Int  ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendar = Calendar.getInstance()
        selectedWeekNumber = calendar?.get(Calendar.WEEK_OF_YEAR)
        selectedDayNumber = calendar?.get(Calendar.DAY_OF_WEEK)
        calendar?.set(Calendar.HOUR_OF_DAY, 0)
        calendar?.clear(Calendar.MINUTE)
        calendar?.clear(Calendar.SECOND)
        calendar?.clear(Calendar.MILLISECOND)
        //update day date
        updateDayDateView()
        //set button click listener
        forwardButton.setOnClickListener{
            calendar?.add(Calendar.WEEK_OF_YEAR, 1)
            selectedWeekNumber = selectedWeekNumber?.plus(1)
            //update day date
            updateDayDateView()
        }
        backButton.setOnClickListener{
            calendar?.add(Calendar.WEEK_OF_YEAR, -1)
            selectedWeekNumber = selectedWeekNumber?.minus(1)
            //update day date
            updateDayDateView()
        }
        sunDayDateTextView.setOnClickListener {
            selectedDayNumber = 1
            //update day date
            updateDayDateView()
        }
        monDayDateTextView.setOnClickListener {
            selectedDayNumber = 2
            //update day date
            updateDayDateView()
        }
        tueDayDateTextView.setOnClickListener {
            selectedDayNumber = 3
            //update day date
            updateDayDateView()
        }
        wedDayDateTextView.setOnClickListener {
            selectedDayNumber = 4
            //update day date
            updateDayDateView()
        }
        thuDayDateTextView.setOnClickListener {
            selectedDayNumber = 5
            //update day date
            updateDayDateView()
        }
        friDayDateTextView.setOnClickListener {
            selectedDayNumber = 6
            //update day date
            updateDayDateView()
        }
        satDayDateTextView.setOnClickListener {
            selectedDayNumber = 7
            //update day date
            updateDayDateView()
        }

        addActionButton.setOnClickListener{
            showEventPopView()
        }

    }

    fun updateDayDateView(): Unit {
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 1)
        //update date number in textview
        sunDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        sunDayDateTextView.setBackgroundResource(if(selectedDayNumber == 1)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 2)
        //update date number in textview
        monDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        monDayDateTextView.setBackgroundResource(if(selectedDayNumber == 2)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 3)
        //update date number in textview
        tueDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        tueDayDateTextView.setBackgroundResource(if(selectedDayNumber == 3)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 4)
        //update date number in textview
        wedDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        wedDayDateTextView.setBackgroundResource(if(selectedDayNumber == 4)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 5)
        //update date number in textview
        thuDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        thuDayDateTextView.setBackgroundResource(if(selectedDayNumber == 5)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 6)
        //update date number in textview
        friDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        friDayDateTextView.setBackgroundResource(if(selectedDayNumber == 6)R.drawable.selected_date_shape else Color.TRANSPARENT)
        //set Week  day number
        calendar?.set(Calendar.DAY_OF_WEEK, 7)
        //update date number in textview
        satDayDateTextView.setText(calendar?.get(Calendar.DAY_OF_MONTH).toString())
        //set date background color
        satDayDateTextView.setBackgroundResource(if(selectedDayNumber == 7)R.drawable.selected_date_shape else Color.TRANSPARENT)

        //update month year textview
        calendar?.set(Calendar.DAY_OF_WEEK, selectedDayNumber?:1)
        monthTextView.setText(calendar?.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)+" "+calendar?.get(Calendar.YEAR))


    }


    fun showEventPopView() {
        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //inflate a custom view
        val view = inflater.inflate(R.layout.event_popup_layout, null)

        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        //set ok button
        builder.setPositiveButton(android.R.string.ok){dialogInterface, i ->
            //save event
        }
        //set cancel button
        builder.setNegativeButton(android.R.string.cancel){dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.setCancelable(false)
        //show alert
        builder.show()
    }
}
