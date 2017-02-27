package com.njakawaii.baseprojectstrucure.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.njakawaii.baseprojectstrucure.R;
import com.njakawaii.baseprojectstrucure.di.view.DaggerViewComponent;
import com.njakawaii.baseprojectstrucure.di.view.ViewComponent;
import com.njakawaii.baseprojectstrucure.di.view.ViewDynamicModule;
import com.njakawaii.baseprojectstrucure.presenter.BasePresenter;
import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.ActivityCallback;
import com.njakawaii.baseprojectstrucure.view.adapters.RepoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepoListFragment extends BaseFragment implements RepoListView {

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Bind(R.id.edit_text)
    protected EditText editText;

    @Bind(R.id.button_search)
    protected Button searchButton;

    @Inject
    protected RepoListPresenter presenter;

    private RepoListAdapter adapter;

    private ActivityCallback activityCallback;

    private ViewComponent viewComponent;

    @OnClick(R.id.button_search)
    public void onClickSearch(View v) {
        if (presenter != null) {
            presenter.onSearchButtonClick();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    public void setViewComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepoListAdapter(new ArrayList<>(), presenter);
        recyclerView.setAdapter(adapter);

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showRepoList(List<Repository> repoList) {
        adapter.setRepoList(repoList);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    public String getUserName() {
        return editText.getText().toString();
    }

    @Override
    public void startRepoInfoFragment(Repository repository) {
        activityCallback.startRepoInfoFragment(repository);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
