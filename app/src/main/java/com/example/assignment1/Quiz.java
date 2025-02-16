package com.example.assignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {
    TextView questionNumber, questionText;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4;
    Button nextButton;
    String username ;
    // Questions, options, and answers
    String[] questions = {
            "What is the capital of Canada?",
            "Which planet is known as the Red Planet?",
            "Who wrote the play 'Romeo and Juliet'?",
            "What is the chemical symbol for Gold?",
            "What is the largest ocean on Earth?"
    };

    String[][] options = {
            {"Toronto", "Vancouver", "Ottawa", "Montreal"},
            {"Venus", "Mars", "Jupiter", "Saturn"},
            {"William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"},
            {"Ag", "Au", "Pb", "Fe"},
            {"Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean"}
    };

    int[] answers = {2, 1, 0, 1, 2}; // Correct answers (0-based index)
    int currentQuestionIndex = 0;
    int score = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();
        loadQuestion();

        nextButton.setOnClickListener(view -> {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                return;
            }

            checkAnswer();

            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                loadQuestion();
            } else {
                // Last question, change button to "Submit"
                nextButton.setText("Submit");
                nextButton.setOnClickListener(v -> {
                    checkAnswer();
                    // Move to the result activity


                    nextButton.setEnabled(false); // Disable the button when the quiz is over
                });
            }
        });
    }

    private void init() {
        username = getIntent().getStringExtra("name");
        questionNumber = findViewById(R.id.questionNumber);
        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);
    }

    @SuppressLint("SetTextI18n")
    private void loadQuestion() {
        radioGroup.clearCheck(); // Clear previous selection
        questionNumber.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.length);
        questionText.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);

        // Update button text based on question index
        if (currentQuestionIndex == questions.length - 1) {
            nextButton.setText("Submit");
        } else {
            nextButton.setText("Next");
        }
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        int selectedIndex = -1;

        if (selectedId == R.id.option1) selectedIndex = 0;
        else if (selectedId == R.id.option2) selectedIndex = 1;
        else if (selectedId == R.id.option3) selectedIndex = 2;
        else if (selectedId == R.id.option4) selectedIndex = 3;

        if (selectedIndex == answers[currentQuestionIndex]) {
            score++;
        }
    }
}
