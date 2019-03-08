package mcdull.com.yue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import mcdull.com.yue.custromview.CustomViewpagerMain;
import mcdull.com.yue.fragment.OneFragment;
import mcdull.com.yue.fragment.TwoFragment;

public class MainActivity extends AppCompatActivity {
   private RadioGroup group_bottom;
   private CustomViewpagerMain main_viewPager;
   private List<Fragment> list;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        group_bottom=findViewById(R.id.main_buttom_group);
        main_viewPager=findViewById(R.id.main_viewpager);
        list=new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        main_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem( int i ) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        main_viewPager.addOnPageChangeListener(new CustomViewpagerMain.OnPageChangeListener() {
            @Override
            public void onPageScrolled( int i, float v, int i1 ) {

            }

            @Override
            public void onPageSelected( int i ) {
                switch (i){
                    case 0:
                        group_bottom.check(R.id.one);
                        break;
                    case 1:
                        group_bottom.check(R.id.two);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged( int i ) {

            }
        });

       group_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged( RadioGroup radioGroup, int i ) {
               switch (i){
                   case R.id.one:
                       main_viewPager.setCurrentItem(0);
                       break;
                   case R.id.two:
                       main_viewPager.setCurrentItem(1);
                       break;
               }
           }
       });
    }
}
