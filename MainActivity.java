package com.example.dialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    TextView numberDisplay;
    String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberDisplay = findViewById(R.id.number_display);

        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonStar, R.id.buttonHash
        };

        for (int id : buttonIds) {
            Button b = findViewById(id);
            b.setOnClickListener(v -> {
                phoneNumber += b.getText().toString();
                numberDisplay.setText(phoneNumber);
            });
        }

        ImageButton callButton = findViewById(R.id.call_button);
        callButton.setOnClickListener(v -> makeCall());

        ImageButton backspaceButton = findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(v -> {
            if (!phoneNumber.isEmpty()) {
                phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                numberDisplay.setText(phoneNumber);
            }
        });
    }

    private void makeCall() {
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }

        // 🔥 Open custom calling screen
        Intent intent = new Intent(MainActivity.this, CallingActivity.class);
        intent.putExtra("number", phoneNumber);
        startActivity(intent);
    }
}
