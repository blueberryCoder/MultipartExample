package com.blueberry.multipart.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by blueberry on 7/7/2017.
 */

public final class TransformUtil {

    private static ObservableTransformer<?, ?> sSchedulerTransformer
            = new ObservableTransformer<Object, Object>() {
        @Override
        public ObservableSource<Object> apply(@NonNull Observable<Object> upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T, T> applySchedulerTransformer() {
        return (ObservableTransformer<T, T>) sSchedulerTransformer;
    }
}
