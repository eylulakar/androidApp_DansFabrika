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
public class ProgramListAdapter extends BaseAdapter {

    private ArrayList listData;
    private Context context;
    private LayoutInflater layoutInflater;

    public ProgramListAdapter(Context context, ArrayList listData) {
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
            convertView = layoutInflater.inflate(R.layout.program_list_row_layout, null);
            holder = new ViewHolder();
            holder.dayTextView = (TextView) convertView.findViewById(R.id.txtProgramListDayText);
            holder.contentTextView = (TextView) convertView.findViewById(R.id.txtProgramListContentText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Program programItem = (Program)listData.get(position);
        holder.dayTextView.setText(programItem.getDayText());
        holder.contentTextView.setText(programItem.getContentText());

        return convertView;
    }

    static class ViewHolder {
        TextView dayTextView;
        TextView contentTextView;
    }

}
