package com.example.wakefulbroadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScheduleWakeup = (Button) findViewById(R.id.btn_schedule);
        btnScheduleWakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the alarm goes off, we want to broadcast an Intent to our
                // BroadcastReceiver.  Here we make an Intent with an explicit class
                // name to have our own receiver (which has been published in
                // AndroidManifest.xml) instantiated and called, and then create an
                // IntentSender to have the intent executed as a broadcast.
                Intent intent = new Intent(MainActivity.this, SimpleWakefulReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this,
                        0, intent, 0);
                // We want the alarm to go off 30 seconds from now.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.SECOND, 30);
                // Schedule the alarm!
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                // Tell the user about what we did.
                Toast.makeText(MainActivity.this, "Alarm scheduled",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
