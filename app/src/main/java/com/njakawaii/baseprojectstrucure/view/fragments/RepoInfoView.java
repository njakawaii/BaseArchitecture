package com.njakawaii.baseprojectstrucure.view.fragments;

import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;

import java.util.List;

public interface RepoInfoView extends View {

    void showContributors(List<Contributor> contributors);

    void showBranches(List<Branch> branches);

}
