package soexample.umeng.com.zjh.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.zjh.bean.BottomBean;
import soexample.umeng.com.zjh.bean.Food;
import soexample.umeng.com.zjh.bean.TopBean;
import soexample.umeng.com.zjh.callback.MyCallBack;
import soexample.umeng.com.zjh.utils.OkhttpUtils;

public class MyModel implements IModel {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;
            Gson gson = new Gson();
            switch (type){
                case 1:
                    TopBean topBean = gson.fromJson(s, TopBean.class);
                    myCallBack.setSuccess(topBean);
                    break;
                case 2:
                    BottomBean bottomBean = gson.fromJson(s, BottomBean.class);
                    myCallBack.setSuccess(bottomBean);
                    break;
                case 3:
                    Food food = gson.fromJson(s, Food.class);
                    myCallBack.setSuccess(food);
                    break;
            }
        }
    };

    private int type;
    private MyCallBack myCallBack;

    @Override
    public void getData(String url, int type, MyCallBack myCallBack) {

        this.type = type;
        this.myCallBack = myCallBack;

        OkhttpUtils.getInstance().getAsync(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                handler.sendMessage(handler.obtainMessage(0,string));
            }
        });
    }
}
