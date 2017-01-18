package bwie.com.redbaby.Adapter.Holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import bwie.com.redbaby.R;
import bwie.com.redbaby.Utils.HomeBean;

/**
 * Created by lishaocong on 2016/11/17.
 */
public class MyViewHolder1 extends BaseViewHolder<HomeBean.DataBean> {

    private final RecyclerView scroll_rv;
    private final ImageView title_iv;

    public MyViewHolder1(View itemView) {
        super(itemView);
        scroll_rv = (RecyclerView) itemView.findViewById(R.id.scroll_rv);
        title_iv = (ImageView) itemView.findViewById(R.id.title_iv);
    }

    @Override
    public void setData(final Context context, final HomeBean.DataBean dataBean) {

        HomeBean.DataBean.TagBean bean = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+bean.picUrl)
                .into(title_iv);

        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        scroll_rv.setLayoutManager(manager);
        scroll_rv.setAdapter(new RecyclerView.Adapter<MyHolder1>(){

            @Override
            public MyHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyHolder1(LayoutInflater.from(context)
                        .inflate(R.layout.home_rv_scrollview_item,parent,false));
            }

            @Override
            public void onBindViewHolder(MyHolder1 holder, int position) {
                holder.setData(context, dataBean.tag.get(position));
            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });

    }

    private class MyHolder1 extends BaseViewHolder<HomeBean.DataBean.TagBean>{

        ImageView scroll_iv;

        public MyHolder1(View itemView) {
            super(itemView);
            scroll_iv = (ImageView) itemView.findViewById(R.id.scroll_iv);
        }

        @Override
        public void setData(Context context, HomeBean.DataBean.TagBean tagBean) {
            Glide.with(context)
                    .load("http://image1.suning.cn"+tagBean.picUrl)
                    .into(scroll_iv);
        }
    }

}
