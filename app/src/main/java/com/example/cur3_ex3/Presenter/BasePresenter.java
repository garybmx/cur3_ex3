package com.example.cur3_ex3.Presenter;

import java.lang.ref.WeakReference;
import android.support.annotation.NonNull;

public abstract class BasePresenter<V> {
    private WeakReference<V> view;

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null;
    }
}
