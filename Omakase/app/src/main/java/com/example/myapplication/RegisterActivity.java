package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnChangeLanguageVi = findViewById(R.id.btn_change_language_vi);
        ImageButton btnChangeLanguageEn = findViewById(R.id.btn_change_language_en);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Sử dụng mã ngôn ngữ "vi" và "en"
        btnChangeLanguageVi.setOnClickListener(v -> toggleLanguage("vi"));
        btnChangeLanguageEn.setOnClickListener(v -> toggleLanguage("en"));
    }

    private void toggleLanguage(String langCode) {
        String currentLang = getResources().getConfiguration().locale.getLanguage();
        if (currentLang.equals(langCode)) {
            return;
        }

        // Chuyển sang LoadingActivity và gửi langCode
        Intent intent = new Intent(RegisterActivity.this, LoadingActivity.class);
        intent.putExtra("LANG_CODE", langCode); // Gửi mã ngôn ngữ
        startActivity(intent);
        finish(); // Đóng RegisterActivity
    }


}
