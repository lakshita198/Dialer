package com.example.dialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CallingActivity extends AppCompatActivity {

    TextView tvNumber;
    Button btnEnd;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        tvNumber = findViewById(R.id.tvNumber);
        btnEnd = findViewById(R.id.btnEnd);

        number = getIntent().getStringExtra("number");
        tvNumber.setText(number);

        // 🔥 Start real call
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        startActivity(callIntent);

        // End button (sirf UI band karega)
        btnEnd.setOnClickListener(v -> finish());
    }
}
