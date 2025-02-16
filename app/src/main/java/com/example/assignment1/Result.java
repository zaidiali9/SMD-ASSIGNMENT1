package com.example.assignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    String username;
    int score, totalQuestions;
    TextView usernameTextView, scoreTextView;
    Button shareButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        usernameTextView.setText(username);
        scoreTextView.setText(score + "/" + totalQuestions);
        shareButton.setOnClickListener(view -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I scored " + score + " out of " + totalQuestions + " in the quiz!");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
    }
    private void init(){
        shareButton = findViewById(R.id.shareButton);
        usernameTextView = findViewById(R.id.usernameText);
        scoreTextView = findViewById(R.id.scoreText);
        username = getIntent().getStringExtra("name");
        score = getIntent().getIntExtra("score", 0);
        totalQuestions = getIntent().getIntExtra("total", 0);
    }
}