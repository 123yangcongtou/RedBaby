package bwie.com.redbaby.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.com.redbaby.R;
import bwie.com.redbaby.Utils.Bean;

/**
 * Created by lishaocong on 2016/11/12.
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder>{

    private final List<Bean.RsBean> list1;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public LinearAdapter(List<Bean.RsBean> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_one,parent,false);
        return new MyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list1.get(position).dirName);
        if(list1.get(position).isChecked){
            holder.left_view.setVisibility(View.VISIBLE);
            holder.tv.setTextColor(context.getResources().getColor(R.color.leftRecyclertextColor));
            holder.itemView.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }else{
            holder.left_view.setVisibility(View.INVISIBLE);
            holder.tv.setTextColor(Color.BLACK);
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }


    /**
     * 设置数据和复用优化
     */
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        View left_view;

        public MyViewHolder(View itemView, final LinearAdapter.OnItemClickListener listener) {
            super(itemView);
           tv=  (TextView) itemView.findViewById(R.id.rv_testview);
            left_view = itemView.findViewById(R.id.left_view);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        //如果用户点击了Item,就获返回当前点击Item的位置
                        listener.onItemClick(v,getPosition());
                    }
                }
            });
        }
    }

    //接口回调监听
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    /**
     * 设置条目点击事件方法
     *
     * @param onItemClickListener 回调接口
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
