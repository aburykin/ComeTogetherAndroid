package ru.bur.lifeofflineandroid.activities.participants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.bur.lifeofflineandroid.R;
import ru.bur.lifeofflineandroid.model.Participant;

public class ParticipantAdapter extends BaseAdapter {

    private ArrayList<Participant> mParticipantData = new ArrayList<>();
    private LayoutInflater mInflaterParticipantItems;

    @Override
    public int getCount() {
        return mParticipantData.size();
    }

    @Override
    public Object getItem(int position) {
        return mParticipantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflaterParticipantItems.inflate(R.layout.participant_item, null);
            holder.sParticipantName = (TextView) convertView.findViewById(R.id.participantName);
            holder.sParticipantImage = (ImageView) convertView.findViewById(R.id.participantImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Change the content here
        if (mParticipantData.get(position) != null) {
            holder.sParticipantName.setText(mParticipantData.get(position).getName());
            holder.sParticipantImage.setImageResource(mParticipantData.get(position).getPhotoPath());
        }

        return convertView;
    }

    public ParticipantAdapter(Context context, ArrayList<Participant> participantData) {
        mParticipantData = participantData;
        mInflaterParticipantItems = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        ImageView sParticipantImage;
        TextView sParticipantName;
    }
}
