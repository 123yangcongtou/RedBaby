package bwie.com.redbaby.HomeUtils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import bwie.com.redbaby.R;

/**
 * Created by lishaocong on 2016/11/15.
 */
public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    private final Context context;
    private final ArrayList<ImageView> dot_list;

    public MyPageChangeListener(Context context, ArrayList<ImageView> dot_list) {
        this.context = context;
        this.dot_list = dot_list;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //当被选中的时候
        for (int i = 0; i < dot_list.size(); i++) {
            //选中
            if(position%dot_list.size()==i){
                dot_list.get(i).setImageResource(R.drawable.circle);
            }else{
                dot_list.get(i).setImageResource(R.drawable.circle_white);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
