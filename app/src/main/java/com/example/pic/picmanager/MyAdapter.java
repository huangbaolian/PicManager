package com.example.pic.picmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    List<Bitmap> paths = new ArrayList<>();
    Context mContext;
    public MyAdapter(Context mContext, List<Bitmap> paths) {
        this.paths = paths;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public Object getItem(int i) {
        return paths.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item,null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageBitmap(paths.get(i));
        return view;
    }
}
