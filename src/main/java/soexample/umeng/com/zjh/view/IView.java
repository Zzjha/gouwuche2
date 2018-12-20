package soexample.umeng.com.zjh.view;

public interface IView<T> {
    void success(T success);
    void error(T error);
}
