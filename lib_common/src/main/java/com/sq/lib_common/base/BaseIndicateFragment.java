package com.sq.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by javakam on 2018/6/2.
 */
public class BaseIndicateFragment extends Fragment {

    public static final String ARG_POS = "arg";
    public int mParam;

    public static BaseIndicateFragment newInstance(int p) {
        BaseIndicateFragment fragment = new BaseIndicateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POS, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam = getArguments().getInt(ARG_POS);
        }
    }

}
