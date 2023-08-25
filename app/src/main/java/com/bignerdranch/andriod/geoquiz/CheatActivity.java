package com.bignerdranch.andriod.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends Activity {

    private boolean mAnswerIsTrue;
    private boolean mAnswerShown = false; // To track if the answer has been shown
    private boolean mIsCheater = false; // To track if the user cheated

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.answerTextView);
        mShowAnswer = findViewById(R.id.showAnswerButton);

        // Restore mIsCheater and mAnswerShown from savedInstanceState
        if (savedInstanceState != null) {
            mIsCheater = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
            mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
            if (mAnswerShown) {
                showAnswer();
            }
        }

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true; // Mark answer as shown
                setAnswerShownResult(true);
                mIsCheater = true; // Mark user as a cheater
            }
        });

        ImageButton previousButton = findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerShown) {
                    showToast(getString(R.string.judgment_toast));
                }
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showAnswer() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }
}



