package com.njakawaii.baseprojectstrucure.integration.model;


import com.njakawaii.baseprojectstrucure.integration.other.IntegrationBaseTest;
import com.njakawaii.baseprojectstrucure.model.Model;
import com.njakawaii.baseprojectstrucure.model.api.ApiInterface;
import com.njakawaii.baseprojectstrucure.model.dto.BranchDTO;
import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.model.dto.RepositoryDTO;
import com.njakawaii.baseprojectstrucure.other.TestConst;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

public class ModelTest extends IntegrationBaseTest {

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    protected Model model;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
    }

    @Test
    public void testGetRepoList() {

        TestSubscriber<List<RepositoryDTO>> testSubscriber = new TestSubscriber<>();
        model.getRepoList(TestConst.TEST_OWNER).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<RepositoryDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(7, actual.size());
        assertEquals("Android-Rate", actual.get(0).getName());
        assertEquals("andrey7mel/Android-Rate", actual.get(0).getFullName());
        assertEquals(26314692, actual.get(0).getId());
    }

    @Test
    public void testGetRepoBranches() {

        TestSubscriber<List<BranchDTO>> testSubscriber = new TestSubscriber<>();
        model.getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<BranchDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(3, actual.size());
        assertEquals("QuickStart", actual.get(0).getName());
        assertEquals("94870e23f1cfafe7201bf82985b61188f650b245", actual.get(0).getCommit().getSha());

    }

    @Test
    public void testGetRepoContributors() {

        TestSubscriber<List<ContributorDTO>> testSubscriber = new TestSubscriber<>();
        model.getRepoContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<ContributorDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(11, actual.size());
        assertEquals("hotchemi", actual.get(0).getLogin());
        assertEquals("User", actual.get(0).getType());
        assertEquals(471318, actual.get(0).getId());

    }
}