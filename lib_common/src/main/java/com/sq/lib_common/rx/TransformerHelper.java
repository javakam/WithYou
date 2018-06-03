package com.sq.lib_common.rx;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


/**
 * Created by monday on 2016/10/24.
 */
public class TransformerHelper {

    private final static FlowableTransformer TRANSFORMER = upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    /**
     * io线程-主线程
     */
    public static <T> FlowableTransformer<T, T> io2main() {
        return (FlowableTransformer<T, T>) TRANSFORMER;
    }
}
