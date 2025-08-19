package com.fmfiroz.ftpplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText ipInput;
    Button connectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ipInput = findViewById(R.id.ipInput);
        connectBtn = findViewById(R.id.connectBtn);

        // Default IP
        ipInput.setText("10.16.100.244");

        connectBtn.setOnClickListener(v -> {
            String ip = ipInput.getText().toString().trim();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("FTP_IP", ip);
            startActivity(intent);
        });
    }
}
