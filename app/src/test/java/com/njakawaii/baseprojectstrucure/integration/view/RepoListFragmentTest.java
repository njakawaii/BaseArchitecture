package com.njakawaii.baseprojectstrucure.integration.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.njakawaii.baseprojectstrucure.R;
import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.MainActivity;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListFragment;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class RepoListFragmentTest extends IntegrationBaseTest {

    @Inject
    protected List<Repository> repoList;

    private RepoListFragment repoListFragment;

    private MainActivity activity;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        repoListFragment = spy(new RepoListFragment());
        activity = Robolectric.setupActivity(MainActivity.class);
        repoListFragment.onAttach(activity);
        doAnswer(invocation -> TestConst.TEST_OWNER)
                .when(repoListFragment)
                .getUserName();

    }

    @Test
    public void testOnCreate() {
        repoListFragment.onCreate(null);
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup)
                activity.findViewById(R.id.container), null);
        repoListFragment.onClickSearch(null);
        repoListFragment.onStop();
        verify(repoListFragment).showRepoList(repoList);
    }

    @Test
    public void testOnCreateWithError() {
        setErrorAnswerWebServer();
        repoListFragment.onCreate(null);
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup)
                activity.findViewById(R.id.container), null);
        repoListFragment.onClickSearch(null);
        repoListFragment.onStop();
        verify(repoListFragment).showError(TestConst.ERROR_RESPONSE_500);
    }

}