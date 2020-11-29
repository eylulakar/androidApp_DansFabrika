package com.eylulakar.dansfabrika;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eylul on 20.10.2014.
 */
public class LocationPhotoListAdapter extends BaseAdapter {
    private List<LocationPhoto> listData = new ArrayList<LocationPhoto>();
    private LayoutInflater inflater;
    private Context context;

    public LocationPhotoListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView imgImage;
        TextView name;

        if(v == null) {
            v = inflater.inflate(R.layout.location_photo_list_row_layout, viewGroup, false);
            v.setTag(R.id.imgLocationPhotoListRowImage, v.findViewById(R.id.imgLocationPhotoListRowImage));
            //  v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        LocationPhoto LocationPhotoItem = (LocationPhoto)listData.get(i);
        //  name = (TextView)v.getTag(R.id.text);

        imgImage = (ImageView)v.getTag(R.id.imgLocationPhotoListRowImage);
        Picasso.with(this.context).load(LocationPhotoItem.getImageUrl()).into(imgImage);
        //imgImage.setImageResource(item.drawableId);

        return v;
    }

}