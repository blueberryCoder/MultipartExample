package com.blueberry.multipart.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by blueberry on 7/7/2017.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * 将assets中的图片报错到sdcard
     *
     * @param context
     */
    public static void ready(final Context context, Consumer<File> fileObserver) {
        Observable.just("image1.jpg", "image2.jpg", "image3.jpg", "image4.png")
                .subscribeOn(Schedulers.io())
                .concatMap(new Function<String, ObservableSource<File>>() {
                    @Override
                    public ObservableSource<File> apply(@NonNull String s) throws Exception {
                        File outFile = writeAssetFile(s, context);
                        return Observable.just(outFile);
                    }
                })
                .subscribe(fileObserver);

    }

    @android.support.annotation.NonNull
    private static File writeAssetFile(@NonNull String s, Context context) throws IOException {
        final AssetManager assetManager = context.getApplicationContext()
                .getAssets();
        File outFile =
                new File(context
                        .getExternalFilesDir(Environment.DIRECTORY_PICTURES), s);
        InputStream in = null;
        FileOutputStream fos = null;
        try {
            in = assetManager.open(s);
            fos = new FileOutputStream(outFile);
            int len = -1;
            byte[] bytes = new byte[8192];
            while ((len = in.read(bytes)) != -1) {
                fos.write(bytes);
            }
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != fos) {
                fos.close();
            }
        }
        return outFile;
    }

}
