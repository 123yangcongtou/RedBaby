package bwie.com.redbaby.Adapter.Holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bwie.com.redbaby.R;
import bwie.com.redbaby.Utils.HomeBean;

/**
 * Created by lishaocong on 2016/11/17.
 */
public class MyViewHolder0 extends BaseViewHolder<HomeBean.DataBean> {

    private final RecyclerView gride_rv;

    public MyViewHolder0(View itemView) {
        super(itemView);
        gride_rv = (RecyclerView) itemView.findViewById(R.id.gride_rv);
    }

    @Override
    public void setData(final Context context, final HomeBean.DataBean dataBean) {
        GridLayoutManager manager=new GridLayoutManager(context,4);
        gride_rv.setLayoutManager(manager);
        gride_rv.setAdapter(new RecyclerView.Adapter<MyHolder0>(){

            @Override
            public MyHolder0 onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyHolder0(LayoutInflater.from(context)
                            .inflate(R.layout.home_rv_grideview_item,parent,false));
            }

            @Override
            public void onBindViewHolder(MyHolder0 holder, int position) {
                holder.setData(context,dataBean.tag.get(position));

            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });


    }

    private class MyHolder0 extends BaseViewHolder<HomeBean.DataBean.TagBean> {
        ImageView iv;
        TextView tv;

        public MyHolder0(View itemView) {
            super(itemView);
             iv=  (ImageView) itemView.findViewById(R.id.grideview_iv);
            tv= (TextView) itemView.findViewById(R.id.grideview_tv);
        }

        @Override
        public void setData(Context context, HomeBean.DataBean.TagBean tagBean) {
            tv.setText(tagBean.elementName);
            Glide.with(context)
                    .load("http://image1.suning.cn"+tagBean.picUrl)
                    .into(iv);

        }
    }
}
