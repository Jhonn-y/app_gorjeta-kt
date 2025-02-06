package com.example.app_gorjeta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_gorjeta.databinding.ActivityResumeBinding

const val KEY_TOTAL_CHECK = "ResultActivity.KEY_TOTAL_CHECK"
const val KEY_TOTAL_PEOPLE = "ResultActivity.KEY_TOTAL_PEOPLE"
const val KEY_PERCENTAGE = "ResultActivity.KEY_PERCENTAGE"
const val KEY_RESULT_TOTAL = "ResultActivity.KEY_RESULT_TOTAL"

private lateinit var binding: ActivityResumeBinding

class ResumeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val check = intent.getDoubleExtra(KEY_TOTAL_CHECK, 0.0)
        val percentage = intent.getStringExtra(KEY_PERCENTAGE)
        val people = intent.getIntExtra(KEY_TOTAL_PEOPLE, 0)
        val tips = intent.getDoubleExtra(KEY_RESULT_TOTAL, 0.0)

        binding.tvTotalBillValue.text = String.format("%.2f", check)
        binding.tvPercentageValue.text = percentage + "%"
        binding.tvTotalTipsValue.text = String.format("%.2f", tips)
        binding.tvTotalPeopleValue.text = people.toString()

        binding.btnRecalculate.setOnClickListener {
            finish()
        }

    }
}
