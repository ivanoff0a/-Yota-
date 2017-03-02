package andrey.yota;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    int minutes;
    boolean smsEnabled;
    int roubles;
    TextView roubleTV;
    TextView minutesTV;
    Menu menu;
    Button setDateButton;
    TextView blabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.timeChooser);
        Switch smsSwitch = (Switch) findViewById(R.id.bezlimitsms);
        roubleTV = (TextView) findViewById(R.id.amount);
        minutesTV = (TextView) findViewById(R.id.time);
        setDateButton = (Button) findViewById(R.id.date);
        blabla = (TextView) findViewById(R.id.anothertext);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Баланс 350\u20BD\n");


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minutes = progress * 200 + 200;
                minutesTV.setText(String.valueOf(minutes) + " минут");
                updateRoubles();


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        smsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                smsEnabled = isChecked;
                updateRoubles();
            }
        });
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DateDialogFragment dialog = new DateDialogFragment();
             dialog.show(getSupportFragmentManager(), "смена даты");
            }
        });


        showNotification();
    }

    private void updateRoubles() {
        roubles = minutes / 2;
        if (smsEnabled == true) {
            roubles += 50;
        }


        roubleTV.setText(String.valueOf(roubles));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menue, menu);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        menu = (Menu) findViewById(R.menu.menue);
        switch (item.getItemId()) {
            case (R.id.chatbutton):
                DialogFragment dialog = new ConfirmationDialogFragment();
                dialog.show(getSupportFragmentManager(), "Чат");
                return true;
            default:
                return true;
        }
    }

    private void showNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.circle_service_yota_network_small)
                .setContentTitle("Добро Пожаловать")
                .build();

        NotificationManager managerCompat = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        managerCompat.notify(0, notification);

        managerCompat.cancel(1);
    }

    public static class ConfirmationDialogFragment extends DialogFragment {

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Перейти в Чат?");
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), ChatActivity.class);
                    startActivity(intent);

                }
            });
            builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            return  builder.create();
        }


    }
    public void setDate(int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year,month,dayOfMonth).getTime();
        if(date.getTime() < System.currentTimeMillis()){
            Toast.makeText(this, "Выбранная дата - не очень.(",Toast.LENGTH_LONG).show();
        }else{
            blabla.setText("В месяц\nСледущее списание " + dayOfMonth + "." + (month + 1) + "." + year);
        }


    }
    public static class DateDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance(); // получаем календарь
            int year = c.get(Calendar.YEAR); // определяем текущий год
            int month = c.get(Calendar.MONTH); // месяц
            int day = c.get(Calendar.DAY_OF_MONTH); // и день месяца

            // создаём слушателя выбора даты
            DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    // отображаем выбранную дату
                    mainActivity.setDate(year, month, dayOfMonth);
                }
            };

            // здесь вся магия: создаём диалог и возвращаем
            // параметры тут: контекст, слушатель и дата, к которой календарь в диалоге сразу перейдёт
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }

}











