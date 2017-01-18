package bwie.com.redbaby.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import bwie.com.redbaby.Adapter.Holder.BaseViewHolder;
import bwie.com.redbaby.Adapter.Holder.MyViewHolder0;
import bwie.com.redbaby.Adapter.Holder.MyViewHolder1;
import bwie.com.redbaby.R;
import bwie.com.redbaby.Utils.HomeBean;

/**
 * Created by lishaocong on 2016/11/17.
 */
public class HomeAdapter2 extends RecyclerView.Adapter<BaseViewHolder> {
    private final Context context;
    private final ArrayList<HomeBean.DataBean> list;
    private static final int TYPE0 = 0;
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;

    public HomeAdapter2(Context context, ArrayList<HomeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //条目类型
    @Override
    public int getItemViewType(int position) {
        return getType(position);
    }

    private int getType(int position) {
        switch (position){
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;
            case 2:
                return TYPE2;
            case 3:
                return TYPE3;
        }
        return  1;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE0:
                return new MyViewHolder0(LayoutInflater.from(context)
                        .inflate(R.layout.home_rv_grideview,parent,false));
            case TYPE1:
                return new MyViewHolder1(LayoutInflater.from(context)
                        .inflate(R.layout.home_rv_scrollview,parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(context,list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
