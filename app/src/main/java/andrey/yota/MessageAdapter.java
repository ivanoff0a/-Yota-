package andrey.yota;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.id.message;


/**
 * Created by С новым годом!!! on 12.02.2017.
 */


public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (sender == 1) {
            View view2 = LayoutInflater.from(getContext()).inflate(R.layout.messagefrombot, parent, false);
            {else}
            View view = LayoutInflater.from(getContext()).inflate(R.layout.messagefromuser, parent, false);
        }



        //View view = LayoutInflater.from(getContext()).inflate(R.layout.messagefromuser, parent, false);
        //View view2 = LayoutInflater.from(getContext()).inflate(R.layout.messagefrombot, parent, false);

        Message message = (Message) getItem(position);

        TextView messageuser = (TextView) view.findViewById(R.id.usertext);
        messageuser.setText(message.text);
        TextView messagebot = (TextView) view.findViewById(R.id.bottext);
        messagebot.setText(message.text);


        return view;
    }

}