package com.example.pic.picmanager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ContentResolver contentResolver;
    List<String> paths = new ArrayList<>();
    List<String> parentDirs = new ArrayList<>();
    List<String> parentImage= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        contentResolver = this.getContentResolver();
        initAdapter();
        requestPower();
        getImage();
    }


    private void initAdapter() {
        MyparentAdapter myparentAdapter = new MyparentAdapter(MainActivity.this,parentDirs,parentImage);
        gridView.setAdapter(myparentAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
            }
        });
    }

    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);
        }
    }
    private void getImage() {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        // 获得图片
        Cursor mCursor = contentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[] { "image/jpeg", "image/png" },MediaStore.Images.Media.DATE_MODIFIED);

        while (mCursor.moveToNext()){
            String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 路径
            paths.add(path);
            //获取到本机中所有的图片后，要对图片进行分类，因此通过路径中的parentDir文件来问分类
            File file = new File(path);
            File parentFile= file.getParentFile();
            String parentFileString = parentFile.getAbsolutePath();
            String ParentFileName = parentFileString.substring(parentFileString.lastIndexOf("/")+1);
            if (parentDirs.contains(ParentFileName)){
                continue;
            }else {
                parentImage.add(path);
                parentDirs.add(ParentFileName);
            }
        }
    }


}