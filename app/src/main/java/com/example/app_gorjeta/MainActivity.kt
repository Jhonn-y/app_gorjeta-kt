package com.example.app_gorjeta

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_gorjeta.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var numberOfPeople: Int = 0

        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spTotalPeople.adapter = adapter
        binding.spTotalPeople.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long,
                ) {
                    if (p2 == 11) {
                        binding.totalPeople.visibility = View.VISIBLE
                    } else {
                        binding.totalPeople.visibility = View.GONE
                        numberOfPeople = p2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    binding.spTotalPeople.getItemAtPosition(0)
                }
            }

        binding.btnCalculate.setOnClickListener {
            val percentage = binding.itPercentage.text
            val itTotal = binding.itTotalText.text
            val itTotalPeople = binding.itTotalPeople.text
            val totalPeople: Int = if (binding.spTotalPeople.selectedItemPosition == 11) {
                binding.itTotalPeople.text.toString().toInt()
            } else {
                binding.spTotalPeople.selectedItemPosition
            }
            var totalWithTips = 0.0
            if (itTotal?.isEmpty() == true || totalPeople == 0 || percentage?.isEmpty() == true ||
                (totalPeople == 11 && itTotalPeople?.isEmpty() == true)
            ) {
                Snackbar.make(
                    binding.itTotalText,
                    "Preencha todos os campos, selecione uma quantidade de pessoas ou a porcentagem para gorjeta",
                    Snackbar.LENGTH_LONG,
                )
                    .show()
            } else {
                val itTotalValue = itTotal.toString().toDouble()
                val totalForPeople = itTotalValue / totalPeople
                val tips = totalForPeople * percentage.toString().toInt() / 100
                totalWithTips = totalForPeople + tips
                val intent = Intent(this, ResumeActivity::class.java)
                intent.apply {
                    putExtra(KEY_TOTAL_CHECK, itTotalValue)
                    putExtra(KEY_TOTAL_PEOPLE, totalPeople)
                    putExtra(KEY_PERCENTAGE, percentage.toString())
                    putExtra(KEY_RESULT_TOTAL, totalWithTips)

                startActivity(intent)
                }
                clean()
            }
        }

        binding.btnClean.setOnClickListener {
            clean()
        }

    }

    private fun clean() {
        binding.itTotalText.setText("")
        binding.itPercentage.setText("")
        binding.itTotalPeople.setText("")
        binding.spTotalPeople.setSelection(0)
    }
}