package com.njakawaii.baseprojectstrucure.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.njakawaii.baseprojectstrucure.R;
import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.other.di.view.DaggerTestViewComponent;
import com.njakawaii.baseprojectstrucure.other.di.view.TestViewComponent;
import com.njakawaii.baseprojectstrucure.other.di.view.TestViewDynamicModule;
import com.njakawaii.baseprojectstrucure.presenter.RepoListPresenter;
import com.njakawaii.baseprojectstrucure.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

public class RepoListFragmentTest extends BaseTest {

    @Inject
    protected RepoListPresenter repoListPresenter;

    private RepoListFragment repoListFragment;
    private MainActivity activity;

    private Bundle bundle;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        TestViewComponent testViewComponent = DaggerTestViewComponent.builder()
                .testViewDynamicModule(new TestViewDynamicModule())
                .build();

        testViewComponent.inject(this);

        repoListFragment = new RepoListFragment();
        activity = Robolectric.setupActivity(MainActivity.class);
        bundle = Bundle.EMPTY;

        repoListFragment.setViewComponent(testViewComponent);

        repoListFragment.onCreate(null); // need for DI
    }


    @Test
    public void testOnCreateView() {
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), null);
        verify(repoListPresenter).onCreateView(null);
    }

    @Test
    public void testOnCreateViewWithBundle() {
        repoListFragment.onCreateView(LayoutInflater.from(activity), (ViewGroup) activity.findViewById(R.id.container), bundle);
        verify(repoListPresenter).onCreateView(bundle);
    }

    @Test
    public void testOnStop() {
        repoListFragment.onStop();
        verify(repoListPresenter).onStop();
    }

    @Test
    public void testOnSaveInstanceState() {
        repoListFragment.onSaveInstanceState(null);
        verify(repoListPresenter).onSaveInstanceState(null);
    }

    @Test
    public void testOnSaveInstanceStateWithBundle() {
        repoListFragment.onSaveInstanceState(bundle);
        verify(repoListPresenter).onSaveInstanceState(bundle);
    }


}