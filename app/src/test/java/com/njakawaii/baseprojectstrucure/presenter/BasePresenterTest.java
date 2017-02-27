package com.njakawaii.baseprojectstrucure.presenter;

import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.view.fragments.RepoListView;
import com.njakawaii.baseprojectstrucure.view.fragments.View;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import rx.Subscription;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasePresenterTest extends BaseTest {

    private final int num_subscription = 100;
    protected View view;
    private BasePresenter basePresenter;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        RepoListView repoListView = mock(RepoListView.class);
        basePresenter = new RepoListPresenter(repoListView);
        view = repoListView;
    }

    @Test
    public void testShowError() throws Exception {
        Throwable throwable = new Throwable(TestConst.TEST_ERROR);
        basePresenter.showError(throwable);
        verify(view).showError(TestConst.TEST_ERROR);
    }

    @Test
    public void testAddSubscription() throws Exception {
        Subscription test = new TestSubscriber<>();
        basePresenter.addSubscription(test);
        assertTrue(basePresenter.compositeSubscription.hasSubscriptions());
    }

    @Test
    public void testOnStop() throws Exception {
        Subscription test = new TestSubscriber<>();
        basePresenter.addSubscription(test);
        basePresenter.onStop();
        assertTrue(test.isUnsubscribed());
    }

    @Test
    public void testOnStopManySubscription() throws Exception {

        ArrayList<Subscription> subscriptionList = new ArrayList<>();

        for (int i = 0; i < num_subscription; i++) {
            Subscription test = new TestSubscriber<>();
            basePresenter.addSubscription(test);
            subscriptionList.add(test);
        }

        basePresenter.onStop();

        for (Subscription subscription : subscriptionList) {
            assertTrue(subscription.isUnsubscribed());
        }
    }
}