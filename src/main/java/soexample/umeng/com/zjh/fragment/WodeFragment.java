package soexample.umeng.com.zjh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.zjh.R;
import soexample.umeng.com.zjh.adapter.LeftAdapter;
import soexample.umeng.com.zjh.adapter.RightAdapter;
import soexample.umeng.com.zjh.bean.Food;
import soexample.umeng.com.zjh.presenter.MyPresenter;
import soexample.umeng.com.zjh.view.IView;

public class WodeFragment<T> extends Fragment implements IView<T> {

    private String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private RecyclerView recyLeft;
    private RecyclerView recyRight;
    private List<Food.DataBean> leftList = new ArrayList<>();
    private List<Food.DataBean.SpusBean> rightList =new ArrayList<>();
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private MyPresenter myPresenter;
    private TextView shangjia;
    private TextView jiesuan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        initView(view);
        leftAdapter = new LeftAdapter(leftList, getActivity());
        recyLeft.setAdapter(leftAdapter);
        rightAdapter = new RightAdapter(rightList, getActivity());
        recyRight.setAdapter(rightAdapter);
        myPresenter = new MyPresenter(this);
        myPresenter.startRequest(url, 3);

        leftAdapter.setOnClick(new LeftAdapter.Callback() {
            @Override
            public void setOnItemClick(View view, int position) {
                 rightList.clear();  //数据先清空
                //刚进去给数量num赋值  全部为0
                rightList.addAll(leftList.get(position).getSpus());
                for (int i = 0; i <rightList.size() ; i++) {
                    rightList.get(i).setPraise_num(0);
                }
                rightAdapter.notifyDataSetChanged();
                shangjia.setText(leftList.get(position).getName());
            }
        });
        rightAdapter.setCallBack(new RightAdapter.AdapterCallBack() {
            @Override
            public void shuaxin() {
                jiesuan.setText(rightAdapter.getGoodsPrice()+"");
            }
        });
        return view;
    }

    private void initView(View view) {
        shangjia = view.findViewById(R.id.shangjia);
        jiesuan = view.findViewById(R.id.jiesuan);

        recyLeft = view.findViewById(R.id.recyLeft);
        LinearLayoutManager leftManager = new LinearLayoutManager(getActivity());
        recyLeft.setLayoutManager(leftManager);

        recyRight = view.findViewById(R.id.recyRight);
        LinearLayoutManager rightManager = new LinearLayoutManager(getActivity());
        recyRight.setLayoutManager(rightManager);
    }


    //Iview
    @Override
    public void success(T success) {
        final Food food = (Food) success;
        leftList.addAll(food.getData());
        leftAdapter.notifyDataSetChanged();
    }
    @Override
    public void error(T error) {

    }
}
