package com.njakawaii.baseprojectstrucure.integration.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.njakawaii.baseprojectstrucure.R;
import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.presenter.vo.Branch;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;
import com.njakawaii.baseprojectstrucure.presenter.vo.Repository;
import com.njakawaii.baseprojectstrucure.view.MainActivity;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoInfoFragment;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RepoInfoFragmentTest extends IntegrationBaseTest {

    @Inject
    protected List<Contributor> contributorList;

    @Inject
    protected List<Branch> branchList;

    @Inject
    protected Repository repository;

    private RepoInfoFragment repoInfoFragment;
    private MainActivity activity;


    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        repoInfoFragment = spy(RepoInfoFragment.newInstance(repository));
        activity = Robolectric.setupActivity(MainActivity.class);
        repoInfoFragment.onAttach(activity);
    }


    @Test
    public void testLoadData() {
        repoInfoFragment.onCreate(null);
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoInfoFragment).showBranches(branchList);
        verify(repoInfoFragment).showContributors(contributorList);

    }


    @Test
    public void testLoadDataWithError() {
        setErrorAnswerWebServer();
        repoInfoFragment.onCreate(null);
        repoInfoFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoInfoFragment, times(2)).showError(TestConst.ERROR_RESPONSE_500);
    }
}