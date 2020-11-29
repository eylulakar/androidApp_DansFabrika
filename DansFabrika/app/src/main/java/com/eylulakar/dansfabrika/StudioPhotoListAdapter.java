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
public class StudioPhotoListAdapter extends BaseAdapter {
    private List<StudioPhoto> listData = new ArrayList<StudioPhoto>();
    private LayoutInflater inflater;
    private Context context;

    public StudioPhotoListAdapter(Context context, ArrayList listData) {
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
            v = inflater.inflate(R.layout.studio_photo_item_layout, viewGroup, false);
            v.setTag(R.id.imgStudioPhotoListImage, v.findViewById(R.id.imgStudioPhotoListImage));
            //  v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        StudioPhoto studioPhotoItem = (StudioPhoto)listData.get(i);
        //  name = (TextView)v.getTag(R.id.text);

        imgImage = (ImageView)v.getTag(R.id.imgStudioPhotoListImage);
        Picasso.with(this.context).load(studioPhotoItem.getImageUrl()).into(imgImage);
        //imgImage.setImageResource(item.drawableId);

        return v;
    }

}