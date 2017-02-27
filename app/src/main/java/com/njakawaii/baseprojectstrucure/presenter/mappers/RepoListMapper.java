package com.njakawaii.baseprojectstrucure.presenter.mappers;

import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class RepoListMapper implements Func1<List<RepositoryDTO>, List<Repository>> {

    @Inject
    public RepoListMapper() {
    }

    @Override
    public List<Repository> call(List<RepositoryDTO> repositoryDTOs) {
        if(repositoryDTOs == null) {
            return null;
        }
        List<Repository> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new Repository(repoDTO.getName(), repoDTO.getOwner().getLogin()))
                .toList()
                .toBlocking()
                .first();
        return repoList;
    }

}
