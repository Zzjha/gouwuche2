package soexample.umeng.com.zjh.callback;

public interface MyCallBack<T> {
    void setSuccess(T successData);
    void setError(T errorData);
}
