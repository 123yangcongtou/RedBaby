package bwie.com.redbaby.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.okHttpUtils.okHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bwie.com.redbaby.Adapter.HomeAdapter2;
import bwie.com.redbaby.HomeUtils.MyPageChangeListener;
import bwie.com.redbaby.R;
import bwie.com.redbaby.Adapter.HomeViewPagerAdapter;
import bwie.com.redbaby.Utils.HomeBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lishaocong on 2016/11/10.
 */
public class FragmentHome extends Fragment {


    private View view;
    private ViewPager vp;
    private LinearLayout dot;
    private ArrayList<HomeBean.DataBean.TagBean> list0;
//    private ArrayList<HomeBean.DataBean.TagBean> list1;
    private ArrayList<HomeBean.DataBean> list;
    private ArrayList<ImageView> dot_list;
    private RecyclerView home_rv;
    private HomeBean homeBean;
    private HomeAdapter2 adapter2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment_home, null);
        //加载ViewPager
        initViewPager();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // 找控件
    private void initViewPager() {
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        dot = (LinearLayout) view.findViewById(R.id.dot);
        home_rv = (RecyclerView) view.findViewById(R.id.home_rv);

        //获得数据
        initData();
        //延迟发送消息
        hand.sendEmptyMessageDelayed(0, 2000);
    }
    //handler
    Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            //获得ViewPager条目
            int item = vp.getCurrentItem();
            item++;
            vp.setCurrentItem(item);
            //延迟发送消息
            hand.sendEmptyMessageDelayed(0, 2000);
        }

        ;
    };

    private void initDot() {
        //将小圆点放入集合
        dot_list = new ArrayList<ImageView>();
        for (int i = 0; i < list0.size(); i++) {
            ImageView image = new ImageView(getActivity());
            if (i == 0) {
                image.setImageResource(R.drawable.circle);
            } else {
                image.setImageResource(R.drawable.circle_white);
            }
            //设置小圆点大小,间距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            image.setLayoutParams(params);
            params.setMargins(5, 0, 5, 0);
            //添加到集合
            dot_list.add(image);
            //添加到布局
            dot.addView(image);
        }
        //1
        //ViewPager监听事件
        vp.setOnPageChangeListener(new MyPageChangeListener(getActivity(), dot_list));
    }

    //获得数据
    private void initData() {
        list0 = new ArrayList();
        okHttp.getAnsy("http://mock.eoapi.cn/success/jSWAxchCQfuhI6SDlIgBKYbawjM3QIga", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                homeBean = gson.fromJson(result, HomeBean.class);
                //1
                HomeBean.DataBean data0 = homeBean.data.get(0);
                List<HomeBean.DataBean.TagBean> tag0 = data0.tag;
                for (int i = 0; i < tag0.size(); i++) {
                    HomeBean.DataBean.TagBean tagBean0 = tag0.get(i);
                    list0.add(tagBean0);
                }
                getActivity().runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                //初始化数据
                                initList();
                                //1
                                vp.setAdapter(new HomeViewPagerAdapter(getActivity(), list0));
                                //设置适配器
                                setHomeAdapter();
                                //添加数据
                                addData();
                                //初始化小圆点
                                initDot();
                            }
                        }
                );
            }
        });
    }

    private void addData() {
        list.add(homeBean.data.get(1));
        list.add(homeBean.data.get(2));
        list.add(homeBean.data.get(4));
        list.add(homeBean.data.get(5));
        list.add(homeBean.data.get(6));
        list.add(homeBean.data.get(7));

//        adapter2.notifyDataSetChanged();
    }

    private void initList() {
        list=new ArrayList<>();
    }
    private void setHomeAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        home_rv.setLayoutManager(layoutManager);
        adapter2 = new HomeAdapter2(getActivity(),list);
        home_rv.setAdapter(adapter2);

    }

}
