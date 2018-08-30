package com.example.pic.picmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {
    ArrayList<? extends String> paths = new ArrayList<>();
    String dirName;
    List<String> images = new ArrayList<>();
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        gridView = findViewById(R.id.gridView);
        initData();
        initAdapter();
    }

    private void initAdapter() {
        MyAdapter myAdapter = new MyAdapter(ChildActivity.this,images);
        gridView.setAdapter(myAdapter);
    }

    private void initData() {
        Intent intent = getIntent();
        paths = intent.getParcelableArrayListExtra("paths");
        dirName = intent.getStringExtra("dirName");
        for (int i = 0;i<paths.size();i++){
            File parentFile = new File(paths.get(i)).getParentFile();
            String parentFileString = parentFile.toString();
            if (parentFileString.substring(parentFileString.lastIndexOf("/")+1).equals(dirName)){
                images.add(paths.get(i));
            }
        }

    }
}
