package andrey.yota;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.id.message;


/**
 * Created by С новым годом!!! on 12.02.2017.
 */


public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Message message = (Message) getItem(position);
        View view;
        if (message.sender == Message.SENDER_BOT) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.messagefrombot, parent, false);
        }else{
            view= LayoutInflater.from(getContext()).inflate(R.layout.messagefromuser, parent, false);
        }

        TextView messageTV = (TextView) view.findViewById(R.id.textView);
        messageTV.setText(message.text);


        return view;
    }

}