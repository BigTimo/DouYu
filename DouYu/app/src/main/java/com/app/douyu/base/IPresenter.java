package com.app.douyu.base;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/28
 */
public interface IPresenter<V, M> {
    /**
     * 绑定View控件
     */
    void attachView(V view);

    /**
     * 绑定Model
     */
    void attachModel(M model);

    /**
     * 注销View控件
     */
    void detachView();

    /**
     * 注销Model对象
     */
    void detachModel();

}
