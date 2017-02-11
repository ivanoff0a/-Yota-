package andrey.yota;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import static andrey.yota.R.id.rouble;
import static android.R.id.progress;

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
        menuInflater.inflate(R.menu.menu, menu);


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        menu = (Menu) findViewById(R.menu.menu);
        switch (item.getItemId()) {
            case(R.id.chatbutton) :
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);


                return true;
            default:
                return true;
        }
    }
}






