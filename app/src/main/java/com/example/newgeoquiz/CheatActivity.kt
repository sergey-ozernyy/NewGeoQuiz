package com.example.newgeoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.security.AccessControlContext

private const val EXTRA_ANSWER_IS_TRUE = "com.example.newgeoquiz.answer_is_true"
private const val EXTRA_ANSWER_SHOWN = "com.example.newgeoquiz.answer_shown"
private const val EXTRA_NUMBER_CHEAT = "com.example.newgeoquiz.number_cheat"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue = false
    private var numberCheat = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            numberCheat = numberCheat + 1
            ///Toast.makeText(this, "Ты подсмотрел $numberCheat раз", Toast.LENGTH_SHORT).show()
            setAnswerShownResult(true, numberCheat)
        }
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean, cheatingNumber: Int) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
            putExtra(EXTRA_NUMBER_CHEAT, cheatingNumber)
        }
        setResult(Activity.RESULT_OK, data)
    }

}