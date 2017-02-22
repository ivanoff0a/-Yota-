package andrey.yota;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import static andrey.yota.R.id.editText;
import static android.R.id.message;
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
        final List<Message> messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(context, resourse, messages);
        listView.setAdapter(messageAdapter);


        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_DONE) {
                    String text = editText.getText().toString();
                    Message message = new Message(text, Message.SENDER_USER);
                    addMessage(message);
                }
                return true;
            }

        });
        Message messagefrombot = new Message("Команда YOTA приветствует вас! Напишите свой вопрос и ответ будет ждать вас в приложении спустя некоторое время. http://www.yota.ru/support/", Message.SENDER_BOT);
        addMessage(messagefrombot);

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

    private void addMessage(final Message message) {
        CountDownTimer timer = new CountDownTimer(1000, 500) {

            @Override
            public void onTick(long millisUntilFinished) {
            }


            @Override
            public void onFinish(){
                messageAdapter.add(message);
            }

        };
        timer.start();
        if (message.text.equals("Привет") || message.text.equals("Здравствуй") || message.text.equals("Здарова")) {
            Message messagefrombot2 = new Message("Здравствйте!\nОпишите свою проблему и мы постараемся вам помочь", Message.SENDER_BOT);
            addMessage(messagefrombot2);
        }
        if (message.text.equals("Покемон")){
            Message messagefrombot4 = new Message("Мы тут делами занимаемся, а не покемонов гоняем:)", Message.SENDER_BOT);
            addMessage(messagefrombot4);

        }

    }
}

