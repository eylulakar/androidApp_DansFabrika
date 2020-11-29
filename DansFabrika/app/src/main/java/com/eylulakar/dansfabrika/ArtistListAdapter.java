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
public class ArtistListAdapter extends BaseAdapter {
    private List<Artist> listData = new ArrayList<Artist>();
    private LayoutInflater inflater;
    private Context context;

    public ArtistListAdapter(Context context, ArrayList listData) {
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
            v = inflater.inflate(R.layout.artist_item_layout, viewGroup, false);
            v.setTag(R.id.imgArtistListImage, v.findViewById(R.id.imgArtistListImage));
            v.setTag(R.id.txtArtistListTitle, v.findViewById(R.id.txtArtistListTitle));
        }

        imgImage = (ImageView)v.getTag(R.id.imgArtistListImage);
        txtTitle = (TextView)v.getTag(R.id.txtArtistListTitle);

        Artist ArtistItem = (Artist)listData.get(i);

        txtTitle.setText(ArtistItem.getTitle());

        //imgImage.setImageResource(ArtistrItem.getDrawableId());
        Picasso.with(this.context).load(ArtistItem.getImageUrl()).into(imgImage);

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