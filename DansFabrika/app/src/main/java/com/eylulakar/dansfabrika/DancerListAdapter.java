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
public class DancerListAdapter extends BaseAdapter {

    private ArrayList listData;
    private Context context;
    private LayoutInflater layoutInflater;

    public DancerListAdapter(Context context, ArrayList listData) {
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
            convertView = layoutInflater.inflate(R.layout.dancer_list_row_layout, null);
            holder = new ViewHolder();
            holder.firstNameView = (TextView) convertView.findViewById(R.id.txtDancerListFirstName);
            //holder.lastNameView = (TextView) convertView.findViewById(R.id.txtLastname);
            //  holder.genderView = (TextView) convertView.findViewById(R.id.txtGender);
            holder.typeView = (TextView) convertView.findViewById(R.id.txtDancerListType);
            //  holder.branchView = (TextView) convertView.findViewById(R.id.txtBranch);

            //holder.imageView = (ImageView) convertView.findViewById(R.id.imgList);
            holder.imageView = (SquareImageView) convertView.findViewById(R.id.imgDancerListImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Dancer dancerItem = (Dancer)listData.get(position);
        holder.firstNameView.setText(dancerItem.getFirstName() + " " + dancerItem.getLastName());
        //holder.branchView.setText(dancerItem.getBranch());
        // holder.lastNameView.setText(dancerItem.getLastName());
        // holder.genderView.setText(dancerItem.getGender());
        holder.typeView.setText(dancerItem.getType());
       // holder.imageView.setImageResource(dancerItem.getImage());

        Picasso.with(context).load(dancerItem.getImageUrl()).into(holder.imageView);

        // Test testClass = new Test();
        // Bitmap bmp = testClass.downloadBitmap(imgFilePath);
        // holder.imageView.setImageBitmap(bmp);




        return convertView;
    }

    static class ViewHolder {
        TextView firstNameView;
        //TextView lastNameView;
        // TextView genderView;
        TextView typeView;

        //ImageView imageView;
        SquareImageView imageView;
        // TextView branchView;
    }

}
