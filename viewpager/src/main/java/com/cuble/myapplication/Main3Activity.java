package com.cuble.myapplication;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> list;
    private int[] img=new int []{R.drawable.after10,R.drawable.after15,R.drawable.after19,R.drawable.after21};
    private ImageView[] dots=null;
    private SoundPool pool;
    private HashMap<Integer,Integer> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        viewPager= (ViewPager) this.findViewById(R.id.viewPager_main);
        list=new ArrayList<View>();
        for(int i=0;i<img.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(img[i]);
            list.add(imageView);
        }
        viewPager.setAdapter(new MyAdapter(list));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            for(int j=0;j<dots.length;j++){
                dots[j].setEnabled(true);
            }
                dots[position].setEnabled(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initDot();
        initSoundPool();
        playSound(1);
    }
    public void initDot(){
        LinearLayout layout= (LinearLayout) findViewById(R.id.layout_main);
        dots=new ImageView[img.length];
        // 循环取得小点图片

        for (int i = 0; i < img.length; i++) {

// 得到一个LinearLayout下面的每一个子元素
//使用LinearLayout.getChildAt(i)获取一个线性布局的view
            dots[i] = (ImageView) layout.getChildAt(i);

            dots[i].setEnabled(true);

            dots[i].setTag(i);

            dots[i].setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem((Integer) view.getTag());
                }
            });

        }

        dots[0].setEnabled(false);
    }
    public void initSoundPool() {

        pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        map = new HashMap<Integer, Integer>();

        map.put(1, pool.load(this, R.raw.ding, 1));

        map.put(2, pool.load(this, R.raw.ringin, 1));

    }


    public void playSound(int sound) {

        pool.play(map.get(sound), // 播放的音乐id

                200, // 左声道音量

                200, // 右声道音量

                1, // 优先级，0为最低

                0, // 循环次数，0:不循环，-1:永远循环

                1 // 回放速度 ，该值在0.5-2.0之间，1为正常速度

        );

    }

    class MyAdapter extends PagerAdapter{
        private List<View> data;

        public MyAdapter(List<View> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(data.get(position));
            return data.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(data.get(position));
        }
    }
}
