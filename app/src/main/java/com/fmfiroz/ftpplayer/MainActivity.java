package com.fmfiroz.ftpplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    FTPHelper ftpHelper;
    String host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = getIntent().getStringExtra("FTP_IP");
        GridView gridView = findViewById(R.id.grid_view);
        ftpHelper = new FTPHelper();

        new Thread(() -> {
            boolean connected = ftpHelper.connect(host, 21);
            if (connected) {
                String[] files = ftpHelper.listFiles("/");
                runOnUiThread(() -> {
                    if (files != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_list_item_1, files);
                        gridView.setAdapter(adapter);

                        gridView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
                            String fileName = files[position];
                            if (fileName.endsWith(".mp4") || fileName.endsWith(".mkv")) {
                                String videoUrl = "ftp://" + host + "/" + fileName;
                                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                                intent.putExtra("VIDEO_URL", videoUrl);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "Not a video file!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            } else {
                runOnUiThread(() ->
                        Toast.makeText(this, "FTP connection failed!", Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}
