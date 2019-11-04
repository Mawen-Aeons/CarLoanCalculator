package com.example.carloancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.os.ConfigurationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener {
            calculateLoan()
        }
    }

    fun calculateLoan(){
        //TODO get all inputs from user and perform calculation

        if(carPrice_edit.text.isEmpty()){
            carPrice_edit.setError(getString(R.string.error))
            return
        }

        val carPrice = carPrice_edit.text.toString().toInt()
        val downPayment = downPayment_edit.text.toString().toInt()
        val loanPeriod = loanPeriod_edit.text.toString().toInt()
        val interestRate = interestRate_edit.text.toString().toFloat()

        var loan = carPrice - downPayment
        var interest = loan * interestRate * loanPeriod
        var monthlyRepayment = (loan + interest) / loanPeriod / 12

        val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
        val currency = Currency.getInstance(currentLocale)

        //TODO Display the outputs
        loanTextView.setText(getString(R.string.loan)+ "${currency.getSymbol() + loan}")
        interestTextView.setText(getString(R.string.interest)+ "${currency.getSymbol() + interest}")
        repaymentTextView.setText(getString(R.string.repayment)+ "${currency.getSymbol() + monthlyRepayment}")

    }

    fun resetInput(view: View) {
        //TODO: Clear all inputs and outputs

        carPrice_edit.setText("")
        downPayment_edit.setText("")
        loanPeriod_edit.setText("")
        interestRate_edit.setText("")

        loanTextView.setText(getString(R.string.loan) + "")
        interestTextView.setText(getString(R.string.interest) +"")
        repaymentTextView.setText(getString(R.string.repayment) +"")

    }

}
