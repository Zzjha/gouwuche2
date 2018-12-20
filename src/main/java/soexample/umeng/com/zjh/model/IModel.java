package soexample.umeng.com.zjh.model;

import soexample.umeng.com.zjh.callback.MyCallBack;

public interface IModel {
    void getData(String url,int type,MyCallBack myCallBack);
}
