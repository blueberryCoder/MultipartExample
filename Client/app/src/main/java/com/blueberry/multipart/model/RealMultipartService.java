package com.blueberry.multipart.model;

import com.blueberry.multipart.entity.UpLoadResponse;
import com.google.gson.Gson;

import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by blueberry on 7/7/2017.
 */

public class RealMultipartService implements MultipartService {

    private MultipartRepository partImpl;
    private MultipartRepository requestBodyImpl;

    public static RealMultipartService newInstance() {
        return new RealMultipartService();
    }

    private RealMultipartService() {
        this.partImpl = MultipartRepositoryPartImpl.newInstance();
        this.requestBodyImpl = MultipartRepositoryRequestBodyImpl.newInstance();
    }

    @Override
    public void singlePart(File file, Callback callback) {
        partImpl.singleFileUpload(file, ObserveAdapter.<UpLoadResponse>of(callback));
    }

    @Override
    public void singleRequestBody(File file, Callback callback) {
        requestBodyImpl.singleFileUpload(file, ObserveAdapter.<UpLoadResponse>of(callback));
    }

    @Override
    public void multiFilePart(Collection<File> files, Callback callback) {
        int i = 0;
        File[] array = new File[files.size()];
        for (File f : files) {
            array[i++] = f;
        }
        partImpl.multiFileUpload(array,
                ObserveAdapter.<UpLoadResponse>of(callback));
    }

    @Override
    public void multiFileRequestBody(Collection<File> files, Callback callback) {
        int i = 0;
        File[] array = new File[files.size()];
        for (File f : files) {
            array[i++] = f;
        }
        requestBodyImpl.multiFileUpload(array,
                ObserveAdapter.<UpLoadResponse>of(callback));
    }

    @Override
    public void multiPart(File file, String str, Callback callback) {
        partImpl.multiUpload(file, str, ObserveAdapter.<UpLoadResponse>of(callback));
    }

    @Override
    public void multiRequestBody(File file, String str, Callback callback) {
        requestBodyImpl.multiUpload(file, str, ObserveAdapter.<UpLoadResponse>of(callback));
    }

    static class ObserveAdapter<T> implements Observer<T> {

        private Reference<Callback> mCallbackReference;

        static <T> ObserveAdapter<T> of(Callback callback) {
            return new ObserveAdapter<T>(callback);
        }

        private ObserveAdapter(Callback callback) {
            this.mCallbackReference = new WeakReference<Callback>(callback);
        }

        @Override
        public void onSubscribe(@NonNull Disposable d) {
        }

        @Override
        public void onNext(@NonNull T t) {
            if (mCallbackReference.get() != null) {
                mCallbackReference.get().print(new Gson().toJson(t));
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            if (mCallbackReference.get() != null) {
                mCallbackReference.get().print(e.getMessage());
            }
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    }
}
