package com.njakawaii.baseprojectstrucure.model.api;

import com.njakawaii.baseprojectstrucure.global.Const;
import com.njakawaii.baseprojectstrucure.model.dto.BranchDTO;
import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ApiInterface {

    @GET(Const.API_USER_REPOS)
    Observable<List<RepositoryDTO>> getRepositories(@Path(Const.API_PARAM_USER) String user);

    @GET(Const.API_GET_REPO_CONTRIBUTORS)
    Observable<List<ContributorDTO>> getContributors(@Path(Const.API_PARAM_OWNER) String owner, @Path(Const.API_PARAM_REPO) String repo);

    @GET(Const.API_GET_REPO_BRANCHES)
    Observable<List<BranchDTO>> getBranches(@Path(Const.API_PARAM_OWNER) String owner, @Path(Const.API_PARAM_REPO) String repo);

}
