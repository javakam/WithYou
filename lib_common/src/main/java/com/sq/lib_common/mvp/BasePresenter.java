package com.sq.lib_common.mvp;

import android.content.Context;

import com.sq.data.wandroid.net.http.RetrofitModule;
import com.sq.data.wandroid.repository.Repository;
import com.sq.data.wandroid.repository.server.NetRepositoryImpl;
import com.sq.lib_common.base.BaseApplication;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenter弱引用View层，放止内存泄漏
 *
 * @param <T>
 * @author javakam
 * @date 2018-6-2 17:49:32
 */
public abstract class BasePresenter<T extends BaseView> implements IPresenter<T> {
    /**
     * Presenter持有View层的实例引用,为了防止内存泄露采用弱应用的形式保存
     */
    private Reference<T> mViewRef;
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private Repository mRepository;

    public BasePresenter(Context context) {
        this.mContext = context;
        //todo 默认使用的是WANAndroid的baseUrl
        mRepository = new Repository(new NetRepositoryImpl
                (RetrofitModule.getRequestApi(BaseApplication.baseUrl)));
    }

    @Override
    public void attachView(T view) {
        this.mViewRef = new WeakReference<>(view);
        this.mCompositeDisposable = new CompositeDisposable();
        onPresenterStart();
    }

    /**
     * Presenter层创建后，在onStart可注册一些监听
     */
    protected void onPresenterStart() {
    }

    @Override
    public void setLocal(boolean isLocal) {
        mRepository.setLocal(isLocal);
    }

    @Override
    public boolean isLocal() {
        return mRepository.isLocal();
    }

    /**
     * 判断View是否还与该Presenter绑定
     *
     * @return true stand on attached
     */
    public boolean isAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取View
     *
     * @return
     */
    public T getView() {
        return isAttached() == false ? null : mViewRef.get();
    }

    /**
     * 解除view与presenter的绑定
     */
    @Override
    public void detachView() {
        unSubscribe();
        mContext = null;
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 添加订阅
     */
    public void addSubscriber(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    /**
     * 取消订阅
     */
    public void unSubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
