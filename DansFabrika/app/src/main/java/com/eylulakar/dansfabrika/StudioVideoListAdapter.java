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
public class StudioVideoListAdapter extends BaseAdapter {
    private List<StudioVideo> listData = new ArrayList<StudioVideo>();
    private LayoutInflater inflater;
    private Context context;

    public StudioVideoListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView imgImage;
        TextView txtTitle;
        TextView txtOther;

        if(v == null) {
            v = inflater.inflate(R.layout.studio_video_item_layout, viewGroup, false);
            v.setTag(R.id.imgStudioVideoListImage, v.findViewById(R.id.imgStudioVideoListImage));
            v.setTag(R.id.txtStudioVideoListTitle, v.findViewById(R.id.txtStudioVideoListTitle));
        }

        imgImage = (ImageView)v.getTag(R.id.imgStudioVideoListImage);
        txtTitle = (TextView)v.getTag(R.id.txtStudioVideoListTitle);

        StudioVideo studioVideoItem = (StudioVideo)listData.get(i);
        txtTitle.setText(studioVideoItem.getTitle());
        Picasso.with(this.context).load(studioVideoItem.getImageUrl()).into(imgImage);
        //imgImage.setImageResource(studioVideoItem.getDrawableId());


        return v;
    }


}