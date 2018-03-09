package com.pabi.my_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Admin on 3/1/2018.
 */

public class GalleryActivity extends AppCompatActivity {

    private Integer[] images = {R.drawable.kong11, R.drawable.kong2, R.drawable.kong3,
            R.drawable.kong4, R.drawable.kong5, R.drawable.kong6};
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_list_layout);

        Gallery imgGallery = (Gallery) findViewById(R.id.gallery);

        imgGallery.setAdapter(new GalleryAdapter(this));
        imageview = (ImageView) findViewById(R.id.imageView);
        imgGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getApplicationContext(), "Image " + arg2, Toast.LENGTH_SHORT).show();
                imageview.setImageResource(images[arg2]);
            }
        });
    }

    public class GalleryAdapter extends BaseAdapter {

        private Context context;
        int image;

        public GalleryAdapter(Context context) {

            this.context = context;
        }


        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(images[arg0]);
            return imageView;

            //return null;
        }
    }
}
