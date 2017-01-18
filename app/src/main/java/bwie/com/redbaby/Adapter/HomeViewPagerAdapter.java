package bwie.com.redbaby.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bwie.com.redbaby.Utils.HomeBean;

/**
 * Created by lishaocong on 2016/11/15.
 */
public class HomeViewPagerAdapter extends PagerAdapter {


    private final Context context;
    private final ArrayList<HomeBean.DataBean.TagBean> list;
    private ImageView iv;

    public HomeViewPagerAdapter(Context context, ArrayList<HomeBean.DataBean.TagBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
    }


    @Override

    public Object instantiateItem(ViewGroup container, int position) {

        //图片
        iv = new ImageView(context);

//        Log.e("1111111111111", "instantiateItem: "+list.get(position%list.size()).picUrl );
        Glide.with(context)
                .load("http://image1.suning.cn"+list.get(position%list.size()).picUrl)
                .into(iv);
        //设置图片的宽度和高度
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //添加视图
        container.addView(iv);
        //返回图片
        return iv;

    }
}
