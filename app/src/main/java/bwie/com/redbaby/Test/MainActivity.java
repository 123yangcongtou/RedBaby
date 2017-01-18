package bwie.com.redbaby.Test;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bwie.BaseActivity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import bwie.com.redbaby.Fragment.FragmentCla;
import bwie.com.redbaby.Fragment.FragmentHome;
import bwie.com.redbaby.Fragment.FragmentMybuy;
import bwie.com.redbaby.Fragment.FragmentShopping;
import bwie.com.redbaby.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.fragmentlayout)
    FrameLayout fragmentlayout;
    @Bind(R.id.home)
    TextView home;
    @Bind(R.id.cla)
    TextView cla;
    @Bind(R.id.mybuy)
    TextView mybuy;
    @Bind(R.id.shopping)
    TextView shopping;


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

        home.setOnClickListener(this);
        //默认第一页
        home.setSelected(true);
        replaceFragment(R.id.fragmentlayout, new FragmentHome());
        cla.setOnClickListener(this);
        mybuy.setOnClickListener(this);
        shopping.setOnClickListener(this);

    }

    @Override
    public void loadData() {

    }


    @Override
    public void onClick(View v) {
        clearTab();
        v.setSelected(true);
        switch (v.getId()) {
            case R.id.home:
                replaceFragment(R.id.fragmentlayout, new FragmentHome());
                break;
            case R.id.cla:
                replaceFragment(R.id.fragmentlayout, new FragmentCla());
                break;
            case R.id.shopping:
                replaceFragment(R.id.fragmentlayout, new FragmentShopping());
                break;
            case R.id.mybuy:
                replaceFragment(R.id.fragmentlayout, new FragmentMybuy());
                break;
        }
    }

    private void clearTab() {
        home.setSelected(false);
        cla.setSelected(false);
        shopping.setSelected(false);
        mybuy.setSelected(false);
    }

}
