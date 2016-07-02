package com.cuble.myapplication;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private List<View> list;
    private TextView[] arr=null;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        viewPager = (ViewPager) this.findViewById(R.id.vpp);
        list = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.view1, null);
        View view2 = inflater.inflate(R.layout.view2, null);
        View view3 = inflater.inflate(R.layout.view3, null);
        View view4 = inflater.inflate(R.layout.view4, null);

        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        viewPager.setAdapter(new MyAdapter(list));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当页面切换改变时，让所有的“点”都变成可操作。

                for (int j = 0; j < arr.length; j++) {

                    arr[j].setEnabled(true);

                    arr[j].setBackgroundColor(Color.GRAY);

                }

// 让当前点击的“点”变成不可以操作。

                arr[position].setEnabled(false);

                arr[position].setBackgroundColor(Color.BLUE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        inintTitle();
    }
    private void inintTitle(){
        LinearLayout layout=(LinearLayout)findViewById(R.id.layout_main_tabtitle);
        arr=new TextView[4];
        for(int i=0;i<4;i++){
            TextView textView= (TextView) layout.getChildAt(i);
            arr[i] =textView;
            arr[i].setEnabled(true);
            arr[i].setBackgroundColor(Color.RED);
            arr[i].setTag(i);
            arr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem((Integer) view.getTag());
                }
            });
        }
        arr[0].setEnabled(false);
        arr[0].setBackgroundColor(Color.BLUE);
    }

    class MyAdapter extends PagerAdapter {
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
            return view == object;
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
