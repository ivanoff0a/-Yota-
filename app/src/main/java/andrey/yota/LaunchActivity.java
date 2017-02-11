package andrey.yota;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import static andrey.yota.R.id.checkBox;
import static andrey.yota.R.id.imageView;

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
                        imageView.animate().setDuration(750).scaleY(1).withEndAction(endAction2);

                    }
                };
                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.animate().setDuration(750).scaleY(0).withEndAction(endAction);
            }
        });
    }


}


