package com.njakawaii.baseprojectstrucure.view.adapters;

import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;

import java.util.List;

public class RepoListAdapter extends BaseAdapter<Repository> {

    private RepoListPresenter presenter;

    public RepoListAdapter(List<Repository> list, RepoListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        Repository repo = list.get(i);
        viewHolder.text.setText(repo.getRepoName());
        viewHolder.text.setOnClickListener(v ->
                presenter.clickRepo(repo));
    }

    public void setRepoList(List<Repository> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
