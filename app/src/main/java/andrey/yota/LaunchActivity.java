package andrey.yota;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

/**
 * Created by BananaAdmin on 04.02.2017.
 */

public class LaunchActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final Button button = (Button) findViewById(R.id.button);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                startAnimation();
            }
        });
    }

    private void startTimer() {
        CountDownTimer timer = new CountDownTimer(2000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {
                long сколькоПрошлоМС = 2000 - millisUntilFinished;
                double процент = (double) сколькоПрошлоМС / 2000;;
                int целыепроценты = (int) Math.round(процент * 100);
                Log.v("JJJ", "# " + целыепроценты);

                showNotification2(целыепроценты);
            }


            @Override
            public void onFinish(){
            }

        };
        timer.start();
    }

    private void startAnimation() {
        Runnable endAction = new Runnable() {
            public void run() {
                Runnable endAction2 = new Runnable() {
                    public void run() {
                        // когда анимация сработает
                        Intent intenttoMainA = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intenttoMainA);
                        finish();
                    }
                };
                imageView.animate().setDuration(1000).scaleY(1).withEndAction(endAction2);

            }
        };
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.animate().setDuration(1000).scaleY(0).withEndAction(endAction);
    }

    private void showNotification2(int progress) {
        // создаём переменную, которая будет хранить контекст
        Context context = this;// создаём конверт, он запустит нашу главную активность
        Intent intent = new Intent(context, MainActivity.class);// оборачиваем наш конверт в другой, который даёт специальные права
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // создаём уведомление
        Notification notification = new NotificationCompat.Builder(context) // создаём строителя
                .setSmallIcon(R.drawable.service_yota_network_big) // устанавливаем маленькую иконку.setContentTitle("Привет от Yota") // заголовок
                .setColor(context.getResources().getColor(R.color.colorAccent))
                .setContentText("Обработка данных...") // текст
                .setContentIntent(pendingIntent) // действие по нажатию на уведомление
                .setDeleteIntent(pendingIntent) // действие при удалении нашего уведомления
                .setPriority(NotificationCompat.PRIORITY_MAX) // приоритет (чем больше, тем выше уведомление)
                .setShowWhen(true) // показывать ли время в уведомлении
                .setTicker("YOTA") // текст, который будет отображаться в status bar, когда в первый раз отобразится
                //.setDefaults(Notification.DEFAULT_VIBRATE) // добавляет вибрацию и звук (другие варианты: DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS)
                .setProgress(100, progress, false)
                .build(); // создаёт уведомлений
        // находим в контексте диспетчер уведомлений
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // отображаем уведомление
        manager.notify(0, notification);

    }
}


