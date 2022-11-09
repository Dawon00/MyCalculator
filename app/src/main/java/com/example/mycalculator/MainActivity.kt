package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text) //버튼일 경우에 text 를 가져온다
        lastNumeric = true
        lastDot = false

    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view:View){
        if(lastNumeric && !lastDot){ //마지막 입력값이 숫자이고 소수점이 아님
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true // 마지막 입력값이 소수점
        }
    }

    fun onOperator(view: View){
        tvInput?.text?.let{ //text 입력값이 존재하면 이 코드를 실행
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }

        }

    }

    private fun isOperatorAdded(value : String) : Boolean{ //연산자가 추가되었는지
        return if(value.startsWith("-")){
            false //마이너스 기호를 무시함
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }
}