package com.example.app_gorjeta

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
        var percentage: Double = 0.0

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 0.1
            }
        }
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 0.15
            }
        }
        binding.rbOptionTree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 0.2
            }
        }

        binding.btnCalculate.setOnClickListener {
            val itTotal = binding.itTotalText.text
            val itTotalPeople = binding.itTotalPeople.text
            val totalPeople: Int = if (binding.spTotalPeople.selectedItemPosition == 11) {
                binding.itTotalPeople.text.toString().toInt()
            } else {
                binding.spTotalPeople.selectedItemPosition
            }
            var totalWithTips = 0.0
            if (itTotal?.isEmpty() == true || totalPeople == 0 || percentage == 0.0 ||
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
                val tips = totalForPeople * percentage
                totalWithTips = totalForPeople + tips

            }
            binding.tvResult.text = "Total com gorjeta: " + String.format("%.2f", totalWithTips)

        }

        binding.btnClean.setOnClickListener {
            binding.itTotalText.setText("")
            binding.rbOptionOne.isChecked = false
            binding.rbOptionTwo.isChecked = false
            binding.rbOptionTree.isChecked = false
            binding.spTotalPeople.setSelection(0)
            binding.tvResult.text = ""

        }

    }
}