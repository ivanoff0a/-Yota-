package andrey.yota;

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
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int minutes;
    boolean smsEnabled;
    int roubles;
    TextView roubleTV;
    TextView minutesTV;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.timeChooser);
        Switch smsSwitch = (Switch) findViewById(R.id.bezlimitsms);
        roubleTV = (TextView) findViewById(R.id.amount);
        minutesTV = (TextView) findViewById(R.id.time);

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
                .setContentText("Настройте свой тариф")
                .build();

        NotificationManager managerCompat = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        managerCompat.notify(0, notification);

        managerCompat.cancel(1);
    }

    public static class ConfirmationDialogFragment extends DialogFragment {

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("вы уверины, шо хотите перийти в чатек?)");
            builder.setPositiveButton("Ое", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getContext(), ChatActivity.class);
                    startActivity(intent);

                }
            });
            builder.setNegativeButton("не, спасиба", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            return  builder.create();
        }


    }

}











