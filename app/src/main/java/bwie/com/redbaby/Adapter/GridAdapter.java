package bwie.com.redbaby.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import bwie.com.redbaby.R;
import bwie.com.redbaby.Utils.Bean;

/**
 * Created by lishaocong on 2016/11/12.
 */
public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final Context context;
    private final ArrayList<Bean.RsBean.ChildrenBean> list2;
    private int TITLE=0;
    private int CONTENT=1;
    private View view;

    public GridAdapter(Context context, ArrayList<Bean.RsBean.ChildrenBean> list2) {
        this.context = context;
        this.list2 = list2;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if(viewType==TITLE){
            holder=new GridAdapter.MyViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.rv_two_title,parent,false));

        }else{
            holder=new GridAdapter.MyViewHolder2(LayoutInflater.from(context)
                    .inflate(R.layout.rv_two_content,parent,false));
        }
        return  holder;
    }


    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        //如果myViewHolder是holder的子类，给标题设置数据
        if(holder instanceof MyViewHolder){
            ((MyViewHolder) holder).tv_tltle.setText(list2.get(position).dirName);
        }else if(holder instanceof MyViewHolder2){ //如果myViewHolder2是holder的子类，给内容设置数据
            ((MyViewHolder2) holder).tv.setText(list2.get(position).dirName);
            Glide.with(context)
                    .load(list2.get(position).imgApp)
                    .into(((MyViewHolder2) holder).iv);
        }
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    @Override//获得条目类型
    public int getItemViewType(int position) {

        boolean isHeader=list2.get(position).isHeader;
        if(isHeader){
            return TITLE;
        }else{
            return  CONTENT;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_tltle;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_tltle=  (TextView) itemView.findViewById(R.id.rv_two_title);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            tv=  (TextView) itemView.findViewById(R.id.rv_two);
            iv=  (ImageView) itemView.findViewById(R.id.rv_pic);
        }
    }

}
