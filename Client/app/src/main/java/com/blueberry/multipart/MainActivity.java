package com.blueberry.multipart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blueberry.multipart.model.BinaryService;
import com.blueberry.multipart.model.Callback;
import com.blueberry.multipart.model.MultipartService;
import com.blueberry.multipart.model.RealMultipartService;
import com.blueberry.multipart.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements Callback {

    @BindView(R.id.tv_message)
    TextView tvMessage;

    /**
     * 测试文件
     */
    private Set<File> mFileSet = new TreeSet<>();

    private MultipartService mMultipartService;
    private BinaryService mBinaryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FileUtil.ready(this, new Consumer<File>() {
            @Override
            public void accept(@NonNull File file) throws Exception {
                mFileSet.add(file);
            }
        });

        mMultipartService = RealMultipartService.newInstance();
        mBinaryService = BinaryService.newInstance();
    }


    @OnClick({R.id.btn_single_part,
            R.id.btn_single_request,
            R.id.btn_multi_file_part,
            R.id.btn_multi_file_request,
            R.id.btn_multi_part,
            R.id.btn_multi_request,
            R.id.btn_binary,
            R.id.btn_binary_stream})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_single_part:
                mMultipartService.singlePart(mFileSet.iterator().next(), this);
                break;
            case R.id.btn_single_request:
                mMultipartService.singleRequestBody(mFileSet.iterator().next(), this);
                break;
            case R.id.btn_multi_file_part:
                mMultipartService.multiFilePart(mFileSet, this);
                break;
            case R.id.btn_multi_file_request:
                mMultipartService.multiFileRequestBody(mFileSet, this);
                break;
            case R.id.btn_multi_part:
                mMultipartService.multiPart(mFileSet.iterator().next(), "测试文本", this);
                break;
            case R.id.btn_multi_request:
                mMultipartService.multiRequestBody(mFileSet.iterator().next(), "测试文本", this);
                break;
            case R.id.btn_binary:
                mBinaryService.binaryUpload(mFileSet.iterator().next(), this);
                break;
            case R.id.btn_binary_stream:
                try {
                    mBinaryService.binaryUpload(getAssets().open("image1.jpg"), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void print(String str) {
        tvMessage.setText(str + "");
    }
}
