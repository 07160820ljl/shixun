package com.example.apple.ljl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.ljl.activity.Accountflag;
import com.example.apple.ljl.activity.AddInaccount;
import com.example.apple.ljl.activity.Inaccountinfo;
import com.example.apple.ljl.activity.Showinfo;

import java.util.ArrayList;
import java.util.List;

public class LjlActivity extends AppCompatActivity {
    private GridView gvInfo;
    private String[] titles = new String[] { "新增支出", "新增收入", "我的支出", "我的收入", "数据管理", "系统设置", "收支便签", "退出" };
    private int[] images = new int[] { R.drawable.addoutaccount, R.drawable.addinaccount, R.drawable.outaccountinfo, R.drawable.inaccountinfo,
            R.drawable.showinfo, R.drawable.sysset, R.drawable.accountflag, R.drawable.exit };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ljl);

        gvInfo = (GridView) findViewById(R.id.gvInfo);
        PictureAdapter adapter = new PictureAdapter(titles, images, this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = null;
                switch (arg2) {
                    case 0:
                       /* intent = new Intent(LjlActivity.this, AddOutaccount.class);
                        startActivity(intent);
                        break;*/
                    case 1:
                        intent = new Intent(LjlActivity.this, AddInaccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        /*intent = new Intent(LjlActivity.this, Outaccountinfo.class);
                        startActivity(intent);
                        break;*/
                    case 3:
                        intent = new Intent(LjlActivity.this, Inaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(LjlActivity.this, Showinfo.class);
                        startActivity(intent);
                        break;
                    case 5:
                       /* intent = new Intent(LjlActivity.this, Sysset.class);
                        startActivity(intent);
                        break;*/
                    case 6:
                        intent = new Intent(LjlActivity.this, Accountflag.class);
                        startActivity(intent);
                        break;
                    case 7:
                        finish();// 关闭当前Activity
                }
            }
        });
    }
//    定义文本组件及图片组件
    class ViewHolder{
        public TextView title;
        public ImageView image;
    }
//    定义功能图标及说明文字
    class Picture{
        private String title;
        private int imageId;
        public Picture(){
            super();
        }
        public Picture(String title,int imageId){
            super();
            this.title = title;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }
    class PictureAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<Picture> pictures;

        public PictureAdapter(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++)
            {
                Picture picture = new Picture(titles[i], images[i]);
                pictures.add(picture);
            }
        }

        @Override
        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int arg0) {
            return pictures.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ViewHolder viewHolder;
            if (arg1 == null) {
                arg1 = inflater.inflate(R.layout.gvitem, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);
                viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);
                arg1.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) arg1.getTag();
            }
            viewHolder.title.setText(pictures.get(arg0).getTitle());
            viewHolder.image.setImageResource(pictures.get(arg0).getImageId());
            return arg1;
        }
    }


}
