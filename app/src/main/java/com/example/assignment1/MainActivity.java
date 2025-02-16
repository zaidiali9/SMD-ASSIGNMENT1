package com.example.assignment1;

import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.startButton);
        EditText name = findViewById(R.id.nameEditText);

        btn.setOnClickListener(view -> {
            String userName = name.getText().toString().trim();

            if (userName.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("MainActivity", "Start button clicked. Name: " + userName);
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                intent.putExtra("name", userName);
                startActivity(intent);
            }
        });
    }
}
