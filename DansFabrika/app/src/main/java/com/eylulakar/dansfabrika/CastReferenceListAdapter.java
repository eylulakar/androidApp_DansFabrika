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
public class CastReferenceListAdapter extends BaseAdapter {
    private List<CastReference> listData = new ArrayList<CastReference>();
    private LayoutInflater inflater;
    private Context context;

    public CastReferenceListAdapter(Context context, ArrayList listData) {
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

        if(v == null) {
            v = inflater.inflate(R.layout.cast_reference_item_layout, viewGroup, false);
            v.setTag(R.id.imgCastReferenceListImage, v.findViewById(R.id.imgCastReferenceListImage));
            v.setTag(R.id.txtCastReferenceListTitle, v.findViewById(R.id.txtCastReferenceListTitle));
        }

        imgImage = (ImageView)v.getTag(R.id.imgCastReferenceListImage);
        txtTitle = (TextView)v.getTag(R.id.txtCastReferenceListTitle);

        CastReference castReferenceItem = (CastReference)listData.get(i);

        txtTitle.setText(castReferenceItem.getTitle());

        //imgImage.setImageResource(castReferencerItem.getDrawableId());
        Picasso.with(this.context).load(castReferenceItem.getImageUrl()).into(imgImage);

        return v;
    }

      // private class Item {
      //     final Integer id;
      //     final String title;
      //     final int drawableId;

      //     Item(Integer id, String title, int drawableId) {
      //         this.id = id;
      //         this.title = title;
      //         this.drawableId = drawableId;
      //     }
      // }
}