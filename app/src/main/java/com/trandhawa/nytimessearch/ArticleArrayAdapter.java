package com.trandhawa.nytimessearch;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by trandhawa on 9/24/17.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter(Context context, List<Article> articles){
        super(context, android.R.layout.simple_list_item_1, articles);
    }
}
