package com.o2sports.xiaokai.guai.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.o2sports.xiaokai.guai.R;
import com.o2sports.xiaokai.guai.entity.Keyword;

/**
 * Created by hxiao on 4/3/2015.
 */
public class KeywordAdapter extends ArrayAdapter<Keyword>{

    private Context mContext;

    public KeywordAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        final Keyword currentKeyword = getItem(position);

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.list_view_button, parent, false);
        }

        row.setTag(currentKeyword);

        final Button KeywordButton = (Button) row.findViewById(R.id.listButton);
        KeywordButton.setTag(currentKeyword);
        KeywordButton.setText(currentKeyword.word);
        KeywordButton.setEnabled(true);
        //KeywordButton.setOnClickListener((View.OnClickListener)mContext);

        return row;
    }
}
