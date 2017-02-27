package com.njakawaii.baseprojectstrucure.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njakawaii.baseprojectstrucure.R;
import com.njakawaii.baseprojectstrucure.global.App;
import com.njakawaii.baseprojectstrucure.presenter.BasePresenter;
import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenter;
import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.adapters.BranchesAdapter;
import com.njakawaii.baseprojectstrucure.view.adapters.ContributorsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepoInfoFragment extends BaseFragment implements RepoInfoView {

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Bind(R.id.repo_info)
    protected TextView info;

    @Bind(R.id.recycler_view_branches)
    protected RecyclerView branchesRecyclerView;

    @Bind(R.id.recycler_view_contributors)
    protected RecyclerView contributorsRecyclerView;

    @Bind(R.id.linear_layout)
    protected View layout;

    @Inject
    protected RepoInfoPresenter presenter;

    public static RepoInfoFragment newInstance(Repository repository) {
        RepoInfoFragment myFragment = new RepoInfoFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, repository);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    private Repository getRepositoryVO() {
        return (Repository) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(this, getRepositoryVO());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        ButterKnife.bind(this, view);

        String infoText = getRepositoryVO().getRepoName() + " (" + getRepositoryVO().getOwnerName() + ")";
        info.setText(infoText);

        branchesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contributorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.onCreateView(savedInstanceState);

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }


    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showContributors(List<Contributor> contributors) {
        contributorsRecyclerView.setAdapter(new ContributorsAdapter(contributors));
    }

    @Override
    public void showBranches(List<Branch> branches) {
        branchesRecyclerView.setAdapter(new BranchesAdapter(branches));
    }

}
