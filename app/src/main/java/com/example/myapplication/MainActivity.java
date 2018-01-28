package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        ArrayList<String> times = new ArrayList<>();
        times.add("2018-01-28 00:22:00");
        times.add("2018-01-28 00:23:00");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int delay = 5000;
        for(int i=0; i < 5; i++){
            Date date = null;
//            try {
//                date = formatter.parse(times.get(i));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            long milliseconds = date.getTime();

//            Integer id = Long.valueOf(date.getTime()).intValue();

            Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
            notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, i);
            notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, getNotification());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            //Debug. Schedule at 5 seconds later.
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+delay, pendingIntent);
            delay += 1000;

        }

    }


    private Notification getNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Title");
        builder.setContentText("Context");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        return builder.build();
    }





//    public void scheduleNotification(Context context, long futureInMillis, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "channel_id")
//                .setContentTitle("title")
//                .setContentText("text")
//                .setContentInfo("Info")
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.ic_launcher_foreground);
//
//        Intent intent = new Intent(context, MainActivity.class);
//        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
//        builder.setContentIntent(activity);
//
//        Notification notification = builder.build();
//
//        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, 0);
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
//    }




//    public void setTime(PendingIntent pendingIntent, long time){
//        NotificationCompat.Builder b = new NotificationCompat.Builder(MainActivity.this, "channel_id")
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(time)
//                .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
//                .setContentTitle("Default notification")
//                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentInfo("Info")
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, b.build());
//    }
}
