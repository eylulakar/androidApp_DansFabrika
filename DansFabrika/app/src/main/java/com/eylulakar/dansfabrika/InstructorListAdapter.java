package com.eylulakar.dansfabrika;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Eylul on 15.10.2014.
 */
public class InstructorListAdapter extends BaseAdapter {

    private ArrayList listData;
    private Context context;
    private LayoutInflater layoutInflater;

    public InstructorListAdapter(Context context, ArrayList listData) {
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
            convertView = layoutInflater.inflate(R.layout.instructor_list_row_layout, null);
            holder = new ViewHolder();

            holder.firstNameView = (TextView) convertView.findViewById(R.id.txtInstructorListFirstName);
            holder.branchView = (TextView) convertView.findViewById(R.id.txtInstructorListBranch);
            holder.imageView = (SquareImageView) convertView.findViewById(R.id.imgInstructorListImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Instructor instructorItem = (Instructor)listData.get(position);
        holder.firstNameView.setText(instructorItem.getFirstName() + " " + instructorItem.getLastName());
        holder.branchView.setText(instructorItem.getBranch());
        Picasso.with(context).load(instructorItem.getImageUrl()).into(holder.imageView);

        return convertView;
    }

    static class ViewHolder {
        TextView firstNameView;
        SquareImageView imageView;
        TextView branchView;
    }

}
