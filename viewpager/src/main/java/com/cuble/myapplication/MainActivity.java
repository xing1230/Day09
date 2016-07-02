package com.cuble.myapplication;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private List<View> list_views;
    private List<String> list_titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.vp);
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.pts);

        pagerTabStrip.setTabIndicatorColor(Color.RED);
//        设置这个选项卡条是否应该画一个宽屏强调在当前标签指示的颜色。
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setBackgroundColor(Color.BLUE);
//        设置所需的间距标题段
        pagerTabStrip.setTextSpacing(50);
        list_views=new ArrayList<View>();
        list_titles=new ArrayList<String>();
//        它的作用类似于findViewById()。不同点是LayoutInflater是用来找res/layout/下的xml布局文件
        LayoutInflater inflater= LayoutInflater.from(this);
        View view1=inflater.inflate(R.layout.view1,null);
        View view2 =inflater.inflate(R.layout.view2,null);
        View view3=inflater.inflate(R.layout.view3,null);
        View view4=inflater.inflate(R.layout.view4,null);

        list_views.add(view1);
        list_views.add(view2);
        list_views.add(view3);
        list_views.add(view4);

        list_titles.add("美女1");
        list_titles.add("美女2");
        list_titles.add("美女3");
        list_titles.add("美女4");
        viewPager.setAdapter(new MyAdapter(list_views,list_titles));
    }
    class MyAdapter extends PagerAdapter{
        private List<View> list;
        private List<String> data;

        public MyAdapter(List<View> list, List<String> data) {
            this.list = list;
            this.data = data;
        }

        @Override
        public int getCount() {
            if(list!=null){
                return list.size();
            }
            return 0;
        }
//为给定的位置创建页面。
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
//决定是否与一个特定的页面视图返回的关键对象
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
//删除给定位置的页面。
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position);
        }
    }
}
