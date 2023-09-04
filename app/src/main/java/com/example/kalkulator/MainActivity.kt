package com.example.kalkulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var inputEditText: EditText
    private var operand1: Double = 0.0
    private var operator: String? = null
    private var isOperatorClicked = false
    private val decimalFormat = DecimalFormat("#,###.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentLocale = Locale.getDefault()
        val decimalFormatSymbols = DecimalFormatSymbols(currentLocale)
        decimalFormatSymbols.decimalSeparator = '.'

// Buat DecimalFormat dengan simbol desimal yang disesuaikan
        val decimalFormat = DecimalFormat("#,###.##########", decimalFormatSymbols)


        resultTextView = findViewById(R.id.resultTextView)
        inputEditText = findViewById(R.id.inputEditText)

        val btnOne = findViewById<Button>(R.id.btnOne)
        val btnTwo = findViewById<Button>(R.id.btnTwo)
        val btnThree = findViewById<Button>(R.id.btnThree)
        val btnFour = findViewById<Button>(R.id.btnFour)
        val btnFive = findViewById<Button>(R.id.btnFive)
        val btnSix = findViewById<Button>(R.id.btnSix)
        val btnSeven = findViewById<Button>(R.id.btnSeven)
        val btnEight = findViewById<Button>(R.id.btnEight)
        val btnNine = findViewById<Button>(R.id.btnNine)
        val btnZero = findViewById<Button>(R.id.btnZero)
        val btnKoma = findViewById<Button>(R.id.btnKoma)


        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnTimes = findViewById<Button>(R.id.btnTimes)
        val btnFor = findViewById<Button>(R.id.btnFor)


        // Mengaitkan fungsi-fungsi dengan tombol-tombol
        btnEquals.setOnClickListener { onEqualsClick(it) }
        btnClear.setOnClickListener { onClearClick(it) }
        btnOne.setOnClickListener { onButtonClick(it) }
        btnTwo.setOnClickListener { onButtonClick(it) }
        btnThree.setOnClickListener { onButtonClick(it) }
        btnFour.setOnClickListener { onButtonClick(it) }
        btnFive.setOnClickListener { onButtonClick(it) }
        btnSix.setOnClickListener { onButtonClick(it) }
        btnSeven.setOnClickListener { onButtonClick(it) }
        btnEight.setOnClickListener { onButtonClick(it) }
        btnNine.setOnClickListener { onButtonClick(it) }
        btnZero.setOnClickListener { onButtonClick(it) }
        btnKoma.setOnClickListener { onButtonClick(it) }
        btnPlus.setOnClickListener { onOperatorClick(it) }
        btnMinus.setOnClickListener { onOperatorClick(it) }
        btnTimes.setOnClickListener { onOperatorClick(it) }
        btnFor.setOnClickListener { onOperatorClick(it) }

    }

//fungsi untuk menampilkan angka yang diklik
    fun onButtonClick(view: View) {
        if (view is Button) {
            val buttonText = view.text.toString()
            val currentText = inputEditText.text.toString()

            // Menghindari angka 0 di awal input
            if (currentText == "0" || isOperatorClicked) {
                inputEditText.text.clear()
                isOperatorClicked = false
            }

            inputEditText.append(buttonText)
        }
    }

    //fungsi untuk operator-operator seperti tambah,bagi,kurang
    fun onOperatorClick(view: View) {
        if (view is Button) {
            val buttonText = view.text.toString()
            val currentText = inputEditText.text.toString()

            // Cek apakah ada input sebelum operator
            if (currentText.isNotEmpty()) {
                operand1 = currentText.replace(",", "").toDouble()
                operator = buttonText
                isOperatorClicked = true
            }
        }
    }

    //fungsi logika perhitungan ketika menekan sama dengan
    fun onEqualsClick(view: View) {
        val newText = inputEditText.text.toString().replace(",", "")

        if (operator != null && newText.isNotEmpty()) {
            val operand2 = newText.toDouble()
            val result = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> {
                    if (operand2 != 0.0) {
                        operand1 / operand2
                    } else {
                        resultTextView.text = "Error"
                        return
                    }
                }
                else -> {
                    resultTextView.text = "Error"
                    return
                }
            }

            resultTextView.text = decimalFormat.format(result)
            operator = null
            isOperatorClicked = false
        }
    }

    //fungsi menghapus semuanya ketika klik "C"
    fun onClearClick(view: View) {
        inputEditText.text.clear()
        resultTextView.text = "0"
        operator = null
        isOperatorClicked = false
    }
}


