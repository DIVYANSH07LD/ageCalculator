package com.example.calculate_age_in_minute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
      private var showSelectedDate : TextView? = null
      private var showFinalTime : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.date_button)
        showSelectedDate = findViewById(R.id.showDate)
        showFinalTime = findViewById(R.id.showTime)
        btnDatePicker.setOnClickListener {
            clickOnSelectDate()
        }
    }

   private fun clickOnSelectDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val finalDatePicker  =  DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                val date =  "$dayOfMonth/${month + 1}/$year"
                showSelectedDate?.text = date

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(date)
                theDate?.let {
                    val time = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    ///let -> it execute the code if the time is not empty
                    currentDate?.let {
                        val currentTIme = currentDate.time/60000
                        val differenceInTime = currentTIme - time
                        showFinalTime?.text = differenceInTime.toString()
                    }
                }
            },
            year, month, day
        )
        finalDatePicker.datePicker.maxDate = System.currentTimeMillis() - 86400000
       finalDatePicker.show()

    }
}