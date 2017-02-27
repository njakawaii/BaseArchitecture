package com.njakawaii.baseprojectstrucure.view.fragments;

import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;

import java.util.List;

public interface RepoListView extends View {

    void showRepoList(List<Repository> vo);

    void showEmptyList();

    String getUserName();

    void startRepoInfoFragment(Repository repository);

}
