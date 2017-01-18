package com.bwie.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bwie.okHttpUtils.okHttp;

import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.Callback;

/**
 * Created by lishaocong on 2016/11/10.
 */
public abstract class BaseActivity extends AppCompatActivity implements IOnCreate{


    private View mRootView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(bindLayout()!=0){
            mRootView = View.inflate(this,bindLayout(),null);
            setContentView(mRootView);
            ButterKnife.bind(this);
            initData();
            initView(savedInstanceState);
            loadData();
        }else{
            Log.e("Activity","bindLayout return 0 !");
        }

    }

    /**
     *
     * @return
     */
    public View getmRootView() {
        return mRootView;
    }

    /**
     *异步请求
     * @param url
     * @param callback
     */
    public void Ansyget(String url, Callback callback){
        okHttp.getAnsy(url,callback);
    }

    /**
     * 异步Map请求
     * @param url
     * @param map
     * @param callback
     */
    public void Ansymap(String url, Map<String,String > map, Callback callback){
        okHttp.postAnsyMap(url,map,callback);
    }

    /**
     *吐司
     * @param text
     */
    public void showToast(CharSequence text){

        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转
     * @param cls
     */
    public void startActivitys(Class<? extends BaseActivity> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);

    }

    /**
     *传值跳转
     * @param cls
     * @param bundle
     */
    public void startAct(Class<BaseActivity> cls,Bundle bundle){
        Intent intent = new Intent(this,cls);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     *添加Fragment
     *
     */

    public void addFragment(Fragment fragment,String tag){

        getSupportFragmentManager().beginTransaction().add(fragment,tag).commit();
    }

    /**
     *移除Fragment
     *
     */
    public void removeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    /**
     *替换Fragment
     *
     */
    public void replaceFragment(int containerViewid,Fragment fragment){
            getSupportFragmentManager().beginTransaction().replace(containerViewid,fragment).commit();
    }

}
