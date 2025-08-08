package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

import Lib.Lib;

/**
 * Xử lí cho màn hình loading sử dụng khi Tải app lên, Chuyển ngôn ngữ
 */
public class LoadingActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);

        progressBar = findViewById(R.id.progressBar);

        // Nhận langCode từ RegisterActivity
        String langCode = getIntent().getStringExtra("LANG_CODE");
        if (langCode != null) {
            changeLanguage(langCode);
        }

        // Tiến trình loading
        new Thread(() -> {
            while (progress < 100) {
                progress += 10;
                handler.post(() -> {
                    progressBar.setProgress(progress);
                });

                try {
                    Thread.sleep(100); // Mỗi lần tăng 5% sau 100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Sau khi hoàn tất, quay lại RegisterActivity
            handler.post(() -> {
                Intent intent = new Intent(LoadingActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            });

        }).start();
    }

    /**
     * Hàm thay đổi config ngôn ngữ của ứng dụng
     * @author thang
     * @param langCode
     */
    private void changeLanguage(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
