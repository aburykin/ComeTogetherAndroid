package ru.bur.cometogetherandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.bur.cometogetherandroid.R;
import ru.bur.cometogetherandroid.model.Partner;

public class PartnerAdapter extends BaseAdapter {

    private ArrayList<Partner> mPartnerData = new ArrayList<>();
    private LayoutInflater mInflaterPartnerItems;

    @Override
    public int getCount() {
        return mPartnerData.size();
    }

    @Override
    public Object getItem(int position) {
        return mPartnerData.get(position);
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
            convertView = mInflaterPartnerItems.inflate(R.layout.partner_item, null);
            holder.sPartnerName = (TextView) convertView.findViewById(R.id.partnerName);
            holder.sPartnerImage = (ImageView) convertView.findViewById(R.id.partnerImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Change the content here
        if (mPartnerData.get(position) != null) {
            holder.sPartnerName.setText(mPartnerData.get(position).getName());
            holder.sPartnerImage.setImageResource(mPartnerData.get(position).getPhotoPath());
        }

        return convertView;
    }

    public PartnerAdapter(Context context, ArrayList<Partner> partnerData) {
        mPartnerData = partnerData;
        mInflaterPartnerItems = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        ImageView sPartnerImage;
        TextView sPartnerName;
    }
}
