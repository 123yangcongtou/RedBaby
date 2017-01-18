package bwie.com.redbaby.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.RecycleView.DividerItemDecoration;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bwie.com.redbaby.R;
import bwie.com.redbaby.Adapter.GridAdapter;
import bwie.com.redbaby.Adapter.LinearAdapter;
import bwie.com.redbaby.Utils.Bean;

/**
 * Created by lishaocong on 2016/11/10.
 */
public class FragmentCla extends Fragment {

    private View view;
    private List<Bean.RsBean> list1 = new ArrayList<>();
    private String str;
    private RecyclerView two;
    private RecyclerView one;
    private LinearAdapter adapter1;
    private Bean bean;
    private ArrayList<Bean.RsBean.ChildrenBean> list2=new ArrayList();
    private GridAdapter adapter2;
    private int num=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =View.inflate(getActivity(),R.layout.fragment_cla,null);
        one = (RecyclerView) view.findViewById(R.id.one);
        two = (RecyclerView) view.findViewById(R.id.two);
        
        //左
        one.setLayoutManager(new LinearLayoutManager(getActivity()));
        one.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        //右
        GridLayoutManager gridelayout=new GridLayoutManager(getActivity(),3);
        //头
        gridelayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {
                return list2.get(position).isHeader?3:1;
            }
        });
        two.setLayoutManager(gridelayout);
//        two.addItemDecoration(new DividerItemDecoration2(getActivity()));

        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        DividerLine dividerLine1=new DividerLine(DividerLine.HORIZONTAL);
        dividerLine.setSize(8);
        dividerLine1.setSize(8);
        dividerLine.setColor(Color.parseColor("#EEEEEE"));
        dividerLine1.setColor(Color.parseColor("#EEEEEE"));
        two.addItemDecoration(dividerLine);
        two.addItemDecoration(dividerLine1);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //获得数据
        initData();

        super.onActivityCreated(savedInstanceState);
    }
    private void initData() {
        new Thread(){
            @Override
            public void run() {

                InputStream stream = null;
                try {
                    stream = getActivity().getAssets().open("category.txt");
                    //将字节流转换成字符流
                    BufferedReader br=new BufferedReader(new InputStreamReader(stream,"utf-8"));
                    //将字符流转换成字符串
                    StringBuilder build=new StringBuilder();
                    String content=null;
                    while((content=br.readLine())!=null){
                        build.append(content.trim());
                    }
                    //获得字符串
                    str = build.toString();
                    Gson gson=new Gson();
                    bean = gson.fromJson(str,Bean.class);
                    List<Bean.RsBean> rs = bean.rs;
                    list1.addAll(rs);

                    //适配器
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //展示左边数据
                            adapter1s();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        }.start();

    }

    public void adapter1s(){
        //设置默认展示第一页
        list1.get(0).isChecked=true;
        adapter1 = new LinearAdapter(list1,getActivity());
        one.setAdapter(adapter1);

        //设置Item点击事件
        adapter1.setOnItemClickListener(new MyclickListener());
        //展示默认数据
        updata(0);
        //展示右边数据
        adapter2s();

    }

    private class MyclickListener implements LinearAdapter.OnItemClickListener {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(getActivity(), "你点击了" +list1.get(position).dirName, Toast.LENGTH_SHORT).show();
            //其余设置不选中
            list1.get(num).isChecked=false;
            //所点击条目设置选中
            list1.get(position).isChecked=true;
            //刷新数据
            adapter1.notifyDataSetChanged();
            num=position;
            //设置右边数据,点击条目展示对应的数据
            updata(position);
            //刷新数据
            adapter2.notifyDataSetChanged();
        }
    }

    private void updata(int index) {
        list2.clear();
        List<Bean.RsBean.ChildrenBean> children = bean.rs.get(index).children;
        for (int i=0;i<children.size();i++){
            children.get(i).isHeader=true;
            //放入集合
            list2.add(children.get(i));
            list2.addAll(children.get(i).children);
        }
    }
    public void adapter2s(){
        adapter2 = new GridAdapter(getActivity(), list2);
        two.setAdapter(adapter2);
    }

    class MyListener implements RecyclerView.RecyclerListener {

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {

        }
    }


}
