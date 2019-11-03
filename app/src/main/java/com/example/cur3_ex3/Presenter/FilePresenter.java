package com.example.cur3_ex3.Presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.util.Log;

import com.example.cur3_ex3.Views.MainView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FilePresenter extends BasePresenter<MainView> {

    @Override
    protected void updateView() {
        converting();
    }

    public void converting() {
        loadFile().map(img -> convertFile(img))
                .subscribe(new Observer<Boolean>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        view().showConverting();
                    }

                    @Override
                    public void onNext(Boolean b) {
                        if (b) {
                            view().showConverted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Boolean convertFile(Bitmap img){
        SystemClock.sleep(3000);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 0, bytes);
        File file = new File("/storage/emulated/0/DCIM/Camera/temp.png");
        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            fo.flush();
            fo.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Bitmap getFile(){
        Bitmap tgtImg = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20191031_035901.jpg");
        return tgtImg;
    }

    public Observable<Bitmap> loadFile() {
        return Observable.just(getFile()).subscribeOn(Schedulers.io());
    }
}
