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
public class VideoListAdapter extends BaseAdapter {
    private List<Video> listData = new ArrayList<Video>();
    private LayoutInflater inflater;
    private Context context;

    public VideoListAdapter(Context context, ArrayList listData) {
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
            v = inflater.inflate(R.layout.video_item_layout, viewGroup, false);
            v.setTag(R.id.imgVideoListImage, v.findViewById(R.id.imgVideoListImage));
            v.setTag(R.id.txtVideoListTitle, v.findViewById(R.id.txtVideoListTitle));
        }

        imgImage = (ImageView)v.getTag(R.id.imgVideoListImage);
        txtTitle = (TextView)v.getTag(R.id.txtVideoListTitle);

        Video VideoItem = (Video)listData.get(i);
        txtTitle.setText(VideoItem.getTitle());
        Picasso.with(this.context).load(VideoItem.getImageUrl()).into(imgImage);
        //imgImage.setImageResource(VideoItem.getDrawableId());


        return v;
    }


}