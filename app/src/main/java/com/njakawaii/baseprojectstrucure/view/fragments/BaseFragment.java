package com.njakawaii.baseprojectstrucure.view.fragments;

import android.support.v4.app.Fragment;

import com.njakawaii.baseprojectstrucure.presenter.Presenter;

public abstract class BaseFragment extends Fragment implements View {

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}

