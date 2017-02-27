package com.njakawaii.baseprojectstrucure.integration.presenter;

import android.os.Bundle;

import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoListMapper;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoListPresenterTest extends IntegrationBaseTest {

    @Inject
    protected RepoListMapper repoListMapper;

    @Inject
    protected Model model;

    @Inject
    protected List<Repository> repoList;

    private RepoListView mockView;
    private RepoListPresenter repoListPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(RepoListView.class);
        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(mockView)
                .getUserName();
        repoListPresenter = new RepoListPresenter(mockView);
    }


    @Test
    public void testLoadData() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        verify(mockView).showRepoList(repoList);
    }


    @Test
    public void testEmptyName() {
        doAnswer(invocation -> "")
                .when(mockView)
                .getUserName();

        repoListPresenter.onSearchButtonClick();

        verify(mockView, never()).showEmptyList();
        verify(mockView, never()).showRepoList(repoList);

    }

    @Test
    public void testClickRepo() {
        Repository repository = new Repository(TestConst.TEST_REPO, TestConst.TEST_OWNER);

        repoListPresenter.clickRepo(repository);

        verify(mockView).startRepoInfoFragment(repository);
    }

    @Test
    public void testLoadDataWithError() {
        setErrorAnswerWebServer();
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        verify(mockView).showError(TestConst.ERROR_RESPONSE_500);
    }


    @Test
    public void testSaveState() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();

        Bundle bundle = Bundle.EMPTY;
        repoListPresenter.onSaveInstanceState(bundle);
        repoListPresenter.onStop();

        repoListPresenter.onCreateView(bundle);

        verify(mockView, times(2)).showRepoList(repoList);
    }


}