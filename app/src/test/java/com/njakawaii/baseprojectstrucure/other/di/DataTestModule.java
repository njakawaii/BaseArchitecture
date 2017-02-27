package com.njakawaii.baseprojectstrucure.other.di;


import com.njakawaii.baseprojectstrucure.model.dto.BranchDTO;
import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.other.TestUtils;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoBranchesMapper;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoContributorsMapper;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoListMapper;
import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataTestModule {

    private TestUtils testUtils;

    public DataTestModule() {
        testUtils = new TestUtils();
    }

    @Provides
    @Singleton
    TestUtils provideTestUtils() {
        return testUtils;
    }

    @Provides
    @Singleton
    Repository provideRepository() {
        return new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);
    }


    @Provides
    @Singleton
    List<ContributorDTO> provideContributorDTOList() {
        ContributorDTO[] contributorDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/contributors.json"), ContributorDTO[].class);
        return Arrays.asList(contributorDTOArray);
    }


    @Provides
    @Singleton
    List<BranchDTO> provideBranchDTOList() {
        BranchDTO[] branchDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/branches.json"), BranchDTO[].class);
        return Arrays.asList(branchDTOArray);
    }

    @Provides
    @Singleton
    List<RepositoryDTO> provideRepositoryDTOList() {
        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos.json"), RepositoryDTO[].class);
        return Arrays.asList(repositoryDTOArray);
    }

    @Provides
    @Singleton
    List<Repository> provideRepositoryList(RepoListMapper repoListMapper, List<RepositoryDTO> list) {
        return repoListMapper.call(list);
    }

    @Provides
    @Singleton
    List<Contributor> provideContributorList(RepoContributorsMapper contributorsMapper, List<ContributorDTO> contributorDTOs) {
        return contributorsMapper.call(contributorDTOs);
    }

    @Provides
    @Singleton
    List<Branch> provideBranchList(RepoBranchesMapper branchesMapper, List<BranchDTO> branchDTOs) {
        return branchesMapper.call(branchDTOs);
    }


}
