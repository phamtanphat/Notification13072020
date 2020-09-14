package com.example.notification13072020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnNotification;
    String MY_CHANNEL_ID = "MY_CHANNEL_ID";
    int REQUEST_CODE_OPEN_APP = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotification = findViewById(R.id.buttonNotification);

        mBtnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                REQUEST_CODE_OPEN_APP,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder =
                        new NotificationCompat
                                .Builder(MainActivity.this, MY_CHANNEL_ID)
                                .setContentTitle("Thông báo mới")
                                .setContentText("Ứng dụng có bản cập nhật mới")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setShowWhen(true)
                                .setContentIntent(pendingIntent)
                                .setStyle(
                                        new NotificationCompat
                                                .BigPictureStyle()
                                                .bigPicture(
                                                        BitmapFactory.decodeResource(
                                                                getResources(),
                                                                R.drawable.hinhdemo
                                                        )))
                                .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(
                            MY_CHANNEL_ID,
                            "myapp",
                            NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(1, builder.build());

            }
        });
    }
}