package com.sq.lib_common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by javakam on 2018/6/2.
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 宿主activity
     */
    protected Activity mActivity;
    protected View mView;
    private Unbinder mUnbinder;
    private boolean isActivityCreated;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            //TODO 2018-6-2 19:25:49 预留
        }
        initVariable(savedInstanceState);
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    @LayoutRes
    protected abstract int getContentId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getContentId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        isActivityCreated = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    /**
     * 初始化相关变量
     *
     * @param savedInstanceState
     */
    protected abstract void initVariable(@Nullable Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();

    protected void showSuccessDialog(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
    }

    protected void showFailDialog(String message) {
    }

    protected void showErrorDialog(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
    }

    protected void setAutoCompleteConfig(AutoCompleteTextView autoComplete) {
        autoComplete.setThreshold(1);
        autoComplete.setDropDownWidth(autoComplete.getWidth() * 2);
        autoComplete.isPopupShowing();
    }

    protected void showAutoCompleteConfig(AutoCompleteTextView autoComplete) {
        if (autoComplete.getAdapter() != null) {
            autoComplete.showDropDown();
        }
    }
}
