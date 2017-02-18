package andrey.yota;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;
import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

/**
 * Created by С новым годом!!! on 09.02.2017.
 */

public class ChatActivity extends AppCompatActivity {
    MessageAdapter messageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        ListView listView = (ListView) findViewById(R.id.listView);
        Context context = this;
        int resourse = android.R.layout.simple_list_item_1;
        final List<String> message = new ArrayList<>();
        MessageAdapter = new MessageAdapter(context, resourse, messages);
        listView.setAdapter(MessageAdapter);


        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_DONE) {
                    String message = editText.getText().toString();
                    addMessage(message);
                }
                return true;
            }

        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Чат");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black_36dp);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;

    }

    private void addMessage(String message) {
        messageAdapter.add(message);
        if (message.equals("Привет") || message.equals("Здравствуй")) {
            messageAdapter.add("Привет, пользователь!");
        }


    }
}

