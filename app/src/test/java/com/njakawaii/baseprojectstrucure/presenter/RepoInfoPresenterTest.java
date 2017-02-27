package com.njakawaii.baseprojectstrucure.presenter;

import android.os.Bundle;


import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.model.dto.BranchDTO;
import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoBranchesMapper;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoContributorsMapper;
import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoInfoView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoPresenterTest extends BaseTest {

    @Inject
    protected List<ContributorDTO> contributorDTOs;

    @Inject
    protected List<BranchDTO> branchDTOs;

    @Inject
    protected List<Contributor> contributorList;

    @Inject
    protected List<Branch> branchList;

    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Inject
    protected RepoContributorsMapper contributorsMapper;

    @Inject
    protected Model model;

    @Inject
    protected Repository repository;

    private RepoInfoView mockView;
    private RepoInfoPresenter repoInfoPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(RepoInfoView.class);
        repoInfoPresenter = new RepoInfoPresenter();
        repoInfoPresenter.onCreate(mockView, repository);

        doAnswer(invocation -> Observable.just(branchDTOs))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        doAnswer(invocation -> Observable.just(contributorDTOs))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
    }


    @Test
    public void testLoadData() {
        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();

        verify(mockView).showBranches(branchList);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        repoInfoPresenter.onCreateView(null);

        verify(mockView, never()).showError(any());
    }


    @Test
    public void testOnErrorBranches() {
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        repoInfoPresenter.onCreateView(null);

        verify(mockView).showError(TestConst.TEST_ERROR);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testOnErrorContributors() {
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);

        repoInfoPresenter.onCreateView(null);

        verify(mockView).showError(TestConst.TEST_ERROR);
        verify(mockView).showBranches(branchList);
    }


    @Test
    public void testOnErrorAll() {
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);


        repoInfoPresenter.onCreateView(null);

        verify(mockView, times(2)).showError(TestConst.TEST_ERROR);
        verify(mockView, never()).showBranches(any());
        verify(mockView, never()).showContributors(contributorList);

    }


    @Test
    public void testSaveState() {
        repoInfoPresenter.onCreateView(null);

        Bundle bundle = Bundle.EMPTY;
        repoInfoPresenter.onSaveInstanceState(bundle);
        repoInfoPresenter.onStop();

        repoInfoPresenter.onCreateView(bundle);

        verify(mockView, times(2)).showBranches(branchList);
        verify(mockView, times(2)).showContributors(contributorList);

        verify(model).getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO);
        verify(model).getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO);
    }


}