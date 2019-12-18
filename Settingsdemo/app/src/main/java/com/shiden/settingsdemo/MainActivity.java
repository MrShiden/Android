package com.shiden.settingsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnShowSetting, btnGetInfo;
    TextView txtInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowSetting = (Button) findViewById(R.id.btnShowSettings);
        btnGetInfo = (Button) findViewById(R.id.btnGetInfo);
        txtInfo = (TextView) findViewById(R.id.txtinfo);

        btnShowSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SettingActivity.class));

            }
        });

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                StringBuilder info = new StringBuilder();

                info.append("Name: "+ sharedPreferences.getString("key_full_name",""));
                info.append("\nE-mail: " + sharedPreferences.getString("key_email", ""));
                info.append("\nEnable sleep timer: " + sharedPreferences.getBoolean("enable_timer", false));
                info.append("\nSleep timer: " + sharedPreferences.getString("key_sleep_timer", "-1"));
                info.append("\nMusic Quality: " + sharedPreferences.getString("key_music_quality", "-1"));
                info.append("\nMusic Quality: " + sharedPreferences.getStringSet("music_type", null));

                txtInfo.setText(info);

            }
        });
    }
}
