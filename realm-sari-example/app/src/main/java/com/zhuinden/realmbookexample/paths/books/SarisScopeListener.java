package com.zhuinden.realmbookexample.paths.books;

import android.support.v4.app.Fragment;

import com.zhuinden.realmbookexample.application.RealmManager;


public class SarisScopeListener extends Fragment {
    SarisPresenter sarisPresenter;

    public SarisScopeListener() {
        setRetainInstance(true);
        RealmManager.incrementCount();
        sarisPresenter = new SarisPresenter();
    }

    @Override
    public void onDestroy() {
        RealmManager.decrementCount();
        super.onDestroy();
    }

    public SarisPresenter getPresenter() {
        return sarisPresenter;
    }
}
