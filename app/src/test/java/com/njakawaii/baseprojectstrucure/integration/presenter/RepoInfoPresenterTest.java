package com.njakawaii.baseprojectstrucure.integration.presenter;

import android.os.Bundle;

import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.RepoInfoPresenter;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoPresenterTest extends IntegrationBaseTest {

    @Inject
    protected List<Contributor> contributorList;

    @Inject
    protected List<Branch> branchList;

    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Inject
    protected RepoContributorsMapper contributorsMapper;

    @Inject
    protected Repository repository;

    @Inject
    protected Model model;

    private RepoInfoView mockView;
    private RepoInfoPresenter repoInfoPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(RepoInfoView.class);
        repoInfoPresenter = new RepoInfoPresenter();
        repoInfoPresenter.onCreate(mockView, repository);
    }


    @Test
    public void testLoadData() {
        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();


        verify(mockView).showBranches(branchList);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testLoadDataWithError() {
        setErrorAnswerWebServer();

        repoInfoPresenter.onCreateView(null);
        repoInfoPresenter.onStop();

        verify(mockView, times(2)).showError(TestConst.ERROR_RESPONSE_500);
    }

    @Test
    public void testOnErrorBranches() {
        setCustomAnswer(false, true);

        repoInfoPresenter.onCreateView(null);

        verify(mockView).showError(TestConst.ERROR_RESPONSE_404);
        verify(mockView).showContributors(contributorList);
    }

    @Test
    public void testOnErrorContributors() {
        setCustomAnswer(true, false);

        repoInfoPresenter.onCreateView(null);

        verify(mockView).showError(TestConst.ERROR_RESPONSE_404);
        verify(mockView).showBranches(branchList);
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
    }




}