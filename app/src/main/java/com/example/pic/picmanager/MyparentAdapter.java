package com.example.pic.picmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyparentAdapter extends BaseAdapter {
    List<String> parentDirs = new ArrayList<>();
    List<String> parentImage;
    Context mContext;
    public MyparentAdapter(Context mContext, List<String> parentDirs, List<String> parentImage) {
        this.parentDirs = parentDirs;
        this.mContext = mContext;
        this.parentImage = parentImage;
    }

    @Override
    public int getCount() {
        return parentDirs.size();
    }

    @Override
    public Object getItem(int i) {
        return parentDirs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.parent_item,null);
        ImageView imageView = view.findViewById(R.id.p_image);
        TextView textView = view.findViewById(R.id.p_text);
        Glide.with(mContext)
                .load(parentImage.get(i))
                .into(imageView);
//        imageView.setImageURI(Uri.parse(parentImage.get(i)));
        textView.setText(parentDirs.get(i));
        return view;
    }
}
