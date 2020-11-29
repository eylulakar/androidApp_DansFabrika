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


/**
 * Created by Eylul on 15.10.2014.
 */
public class NewsListAdapter extends BaseAdapter {

    private ArrayList listData;
    private Context context;
    private LayoutInflater layoutInflater;

    public NewsListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);

        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.news_list_row_layout, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.txtNewsListTitle);
            holder.descriptionShortView = (TextView) convertView.findViewById(R.id.txtNewsListDescriptionShort);

            holder.imageView = (ImageView) convertView.findViewById(R.id.imgNewsListImage);
            //holder.imageView = (SquareImageView) convertView.findViewById(R.id.imgNewsListImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News newsItem = (News)listData.get(position);
        holder.titleView.setText(newsItem.getTitle());
        holder.descriptionShortView.setText(newsItem.getDescriptionShort());

        Picasso.with(context).load(newsItem.getImageThumbUrl()).into(holder.imageView);

        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView descriptionShortView;


        ImageView imageView;
        //SquareImageView imageView;

    }

}
