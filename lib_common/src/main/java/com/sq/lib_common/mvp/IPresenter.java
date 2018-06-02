package com.sq.lib_common.mvp;

/**
 * mvp的presenter层
 * <p>
 * https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/
 */
public interface IPresenter<T extends IView> {
    /**
     * 绑定View层
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 解绑View层
     */
    void detachView();

    /**
     * 设置数据源
     *
     * @param isLocal
     */
    void setLocal(boolean isLocal);

    boolean isLocal();
}
