package andrey.yota;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by С новым годом!!! on 12.02.2017.
 */
public class Message extends AppCompatActivity {
    String text;
    int sender;
    static int SENDER_BOT = 0;
    static int SENDER_USER = 1;

  Message(String text,int sender) {
   this.sender = sender;
   this.text = text;
   }
}









