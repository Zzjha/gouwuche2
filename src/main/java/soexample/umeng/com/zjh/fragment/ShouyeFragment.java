package soexample.umeng.com.zjh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.zjh.R;
import soexample.umeng.com.zjh.adapter.BottomAdapter;
import soexample.umeng.com.zjh.adapter.TopAdapter;
import soexample.umeng.com.zjh.bean.BottomBean;
import soexample.umeng.com.zjh.bean.TopBean;
import soexample.umeng.com.zjh.presenter.MyPresenter;
import soexample.umeng.com.zjh.presenter.MyPresenter2;
import soexample.umeng.com.zjh.view.IView;

public class ShouyeFragment<T> extends Fragment implements IView<T> {

    private String url1 = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private String url2 = "http://www.xieast.com/api/news/news.php?page=";
    private RecyclerView topRecy;
    private RecyclerView bottonRecy;
    private List<TopBean.DataBean> topList = new ArrayList<>();
    private List<BottomBean.DataBean> bottomList = new ArrayList<>();
    private TopAdapter topAdapter;
    private BottomAdapter bottomAdapter;
    private MyPresenter myPresenter;
    private MyPresenter2 myPresenter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        initView(view);
        myPresenter = new MyPresenter(this);
        myPresenter.startRequest(url1,1);

        myPresenter2 = new MyPresenter2(this);
        myPresenter2.startRequest(url2,2);
        return view;
    }

    private void initView(View view) {
        topRecy = view.findViewById(R.id.topRecy);
        GridLayoutManager manager1 = new GridLayoutManager(getActivity(),4);
        topRecy.setLayoutManager(manager1);

        bottonRecy = view.findViewById(R.id.bottonRecy);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        bottonRecy.setLayoutManager(manager2);
    }

    @Override
    public void success(T success) {
        if(success instanceof TopBean){
            TopBean topBean = (TopBean) success;
            topList.addAll(topBean.getData());
            topAdapter = new TopAdapter(topList,getActivity());
            topRecy.setAdapter(topAdapter);
            topAdapter.notifyDataSetChanged();
        }

        if (success instanceof BottomBean){
            BottomBean bottomBean = (BottomBean) success;
            bottomList.addAll(bottomBean.getData());
            bottomAdapter = new BottomAdapter(bottomList,getActivity());
            bottonRecy.setAdapter(bottomAdapter);
            bottomAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void error(T error) {

    }
}
