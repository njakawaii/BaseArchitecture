package com.njakawaii.baseprojectstrucure.view.fragments;

import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.presenter.BasePresenter;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

public class BaseFragmentTest extends BaseTest {

    @Inject
    protected Repository repository;
    private BaseFragment baseFragment;
    private BasePresenter basePresenter;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        RepoInfoFragment repoInfoFragment = RepoInfoFragment.newInstance(repository);
        baseFragment = repoInfoFragment;
        baseFragment.onCreate(null); //for Di

        basePresenter = repoInfoFragment.presenter;
    }

    @Test
    public void testOnStop() {
        baseFragment.onStop();
        verify(basePresenter).onStop();
    }
}