package com.njakawaii.baseprojectstrucure.model;


import com.njakawaii.baseprojectstrucure.model.dto.BranchDTO;
import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;

import java.util.List;

import rx.Observable;

public interface Model {

    Observable<List<RepositoryDTO>> getRepoList(String name);

    Observable<List<BranchDTO>> getRepoBranches(String owner, String name);

    Observable<List<ContributorDTO>> getRepoContributors(String owner, String name);

}
