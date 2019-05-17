package com.capstone.opsr.introslider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capstone.opsr.R;
import com.capstone.opsr.authentication.LoginActivity;

public class IntroSlider extends AppCompatActivity {

    private ViewPager viewpager;
    private MyViewPageAdapter myViewPageAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip , btnNext;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro_slider);

        //Check LẦN ĐẦU mở app
        prefManager = new PrefManager(this);
        if(!prefManager.IsFirstTimeLaunch()){
            launchHomeScreen();
            finish();
        }


        viewpager =  findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnSkip =  findViewById(R.id.btnSkip);
        btnNext =  findViewById(R.id.btnNext);

        layouts = new int[]{
                R.layout.layout_intro1,
                R.layout.layout_intro2,
                R.layout.layout_intro3};

        //add DOTS biểu thị số sang và trang đang selected tương ứng
        addBottomDots(0);


        myViewPageAdapter = new MyViewPageAdapter();
        viewpager.setAdapter(myViewPageAdapter);
        viewpager.addOnPageChangeListener(viewPagerPageChangeListener);


        //Event when click Skip button
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        //Event when click Next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewpager.getCurrentItem() +1 ;
                if(current < layouts.length){
                    viewpager.setCurrentItem(current);
                }else {
                    launchHomeScreen();
                }

            }
        });



    }

    private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];

        int [] colorActive = getResources().getIntArray(R.array.array_dot_active);
        int [] colorInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for(int i = 0 ; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[currentPage].setTextColor(colorActive[currentPage]);
        }
    }


    private void launchHomeScreen(){
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this , LoginActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addBottomDots(i);
            if( i == layouts.length -1){
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.GONE);
            }else {
                btnNext.setText("Tiếp Tục");
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    public class MyViewPageAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPageAdapter() {
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position] , container , false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container , int position , Object o){
            View view = (View) o;
            container.removeView(view);
        }

    }
}


