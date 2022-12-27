package com.example.ecoclub.Notifications;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;

//esta notificacion confirmará si se creo o no la comunidad y/o actividad
public class NotificacionConfirmation {
    private static final String CHANNEL_ID = "canal";
    private PendingIntent pendingIntent;

    private FragmentActivity fragmentActivity;
    private String titulo, contenido;

    public NotificacionConfirmation(FragmentActivity fragmentActivity, String titulo, String contenido){
        this.fragmentActivity = fragmentActivity;
        this.titulo = titulo;
        this.contenido = contenido;
    }
    public void enviarNotificacion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //es android sdk 26 o superior
            showNotifications();
        }else{
            showNewNotifications();       
        }
    }

    private void showNotifications() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) fragmentActivity
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);

        showNewNotifications();
    }

    private void showNewNotifications() {
        setPendingIntent(MainActivity.class);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this.fragmentActivity, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_vector)
                .setContentTitle(this.titulo)
                .setContentText(this.contenido)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setColor(Color.CYAN)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this.fragmentActivity);
        managerCompat.notify(1, builder.build());

    }

    private void setPendingIntent(Class<MainActivity> mainActivityClass) {
        Intent intent = new Intent(this.fragmentActivity, mainActivityClass);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.fragmentActivity);
        taskStackBuilder.addParentStack(mainActivityClass);
        taskStackBuilder.addNextIntent(intent);

        pendingIntent = taskStackBuilder.getPendingIntent(1,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
