package com.example.rain.gallerydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    Gallery gallery;
    private int[] imgs = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.ImageSwitcher01);
        imageSwitcher.setFactory(new viewFactory());
        imageSwitcher.setInAnimation(AnimationUtils
                .loadAnimation(this, android.R.anim.fade_in) );
        imageSwitcher.setOutAnimation(AnimationUtils
                .loadAnimation(this, android.R.anim.fade_out));
        imageSwitcher.setImageResource(R.drawable.img1);
        gallery = (Gallery) findViewById(R.id.Gallery01);
        gallery.setOnItemSelectedListener(
                new onItemSelectedListener());
        gallery.setSpacing(10);

        gallery.setAdapter(new baseAdapter());

    }

    //通过ViewFactory接口建立一个imageView图像视图
    class viewFactory implements ViewSwitcher.ViewFactory
    {
        @Override
        public View makeView()
        {


        }
    }

    //实现选项监听接口，获取选择到的图片
    class onItemSelectedListener  implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                                   View view,int position, long id)
        {
            imageSwitcher.setImageResource(
                    (int)gallery.getItemIdAtPosition(position));
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {	 }
    }

    //设置一个适配器，安排放在画廊gallery的图片文件及显示方式
    class  baseAdapter extends BaseAdapter
    {
        //取得gallery內的照片数量
        public int getCount()
        {return imgs.length;}
        public Object getItem(int position)
        { return null;		}
        //取得gallery內选择的某一张图片文件
        public long getItemId(int position)
        {	return imgs[position];	}
        //将选择到的图片放置在imageView，且设定显示方式为居中，大小是60x60
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(imgs[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new Gallery.LayoutParams(300, 300));
            return imageView;
        }
    }

}
