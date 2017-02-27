package com.njakawaii.baseprojectstrucure.presenter;

import android.os.Bundle;


import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;
import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.mappers.RepoListMapper;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.ActivityCallback;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListView;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class RepoListPresenterTest extends BaseTest {

    @Inject
    protected RepoListMapper repoListMapper;

    @Inject
    protected Model model;

    @Inject
    protected List<Repository> repoList;

    @Inject
    protected List<RepositoryDTO> repositoryDTOs;

    private RepoListView mockView;
    private RepoListPresenter repoListPresenter;
    private ActivityCallback activityCallback;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        activityCallback = mock(ActivityCallback.class);

        mockView = mock(RepoListView.class);
        repoListPresenter = new RepoListPresenter(mockView);
        doAnswer(invocation -> Observable.just(repositoryDTOs))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(mockView)
                .getUserName();
    }


    @Test
    public void testLoadData() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();
        repoListPresenter.onStop();

        verify(mockView).showRepoList(repoList);
    }

    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showEmptyList();
    }

    @Test
    public void testLoadEmptyData() {
        doAnswer(invocation -> Observable.just(Collections.emptyList()))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showEmptyList();
    }


    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .getRepoList(TestConst.TEST_OWNER);

        repoListPresenter.onSearchButtonClick();

        verify(mockView).showError(TestConst.TEST_ERROR);
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
    public void testSaveState() {
        repoListPresenter.onCreateView(null);
        repoListPresenter.onSearchButtonClick();

        Bundle bundle = Bundle.EMPTY;
        repoListPresenter.onSaveInstanceState(bundle);
        repoListPresenter.onStop();

        repoListPresenter.onCreateView(bundle);

        verify(mockView, times(2)).showRepoList(repoList);
        verify(model).getRepoList(TestConst.TEST_OWNER);
    }

}