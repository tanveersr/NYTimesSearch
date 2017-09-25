package com.trandhawa.nytimessearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by trandhawa on 9/24/17.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter(Context context, List<Article> articles){
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // get the data item for the current position
        Article article = getItem(position);

        // check to see if existing view can be reused
        // not using recycled view -> inflate layout
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
        }


        // find the image view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);

        // clear out the recycled image from convertView from last time
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(article.getHeadline());

        // populate the thumbnail image
        // remote download the image in the background
        String thumbNail = article.getThumbNail();

        if(!TextUtils.isEmpty(thumbNail)) {
            Picasso.with(getContext()).load(thumbNail).into(imageView);
        }

        return convertView;
    }
}
