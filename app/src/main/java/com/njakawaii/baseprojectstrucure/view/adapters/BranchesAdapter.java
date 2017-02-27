package com.njakawaii.baseprojectstrucure.view.adapters;

import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;

import java.util.List;

public class BranchesAdapter extends BaseAdapter<Branch> {

    public BranchesAdapter(List<Branch> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        String text = list.get(position).getName();
        holder.text.setText(text);
    }


}
