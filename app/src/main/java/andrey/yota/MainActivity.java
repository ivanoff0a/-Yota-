package andrey.yota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import static andrey.yota.R.id.rouble;

public class MainActivity extends AppCompatActivity {
    int minutes;
    boolean smsEnabled;
    int roubles;
    TextView roubleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.timeChooser);
        Switch smsSwitch = (Switch) findViewById(R.id.bezlimitsms);
        roubleTV = (TextView) findViewById(R.id.amount);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minutes = progress * 200 + 200;
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
}