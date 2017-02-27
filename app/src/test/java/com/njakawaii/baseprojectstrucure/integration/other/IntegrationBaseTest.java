package com.njakawaii.baseprojectstrucure.integration.other;

import com.njakawaii.baseprojectstrucure.BuildConfig;
import com.njakawaii.baseprojectstrucure.global.App;
import com.njakawaii.baseprojectstrucure.integration.other.di.IntegrationTestComponent;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.other.TestUtils;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = IntegrationTestApp.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class IntegrationBaseTest {

    public IntegrationTestComponent component;
    public TestUtils testUtils;

    @Inject
    protected MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        component = (IntegrationTestComponent) App.getComponent();
        testUtils = new TestUtils();
        component.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    protected void setErrorAnswerWebServer() {
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return new MockResponse().setResponseCode(500);
            }
        });
    }

    protected void setCustomAnswer(boolean enableBranches, boolean enableContributors) {
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/" + TestConst.TEST_OWNER + "/repos")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/repos.json"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches") && enableBranches) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/branches.json"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors") && enableContributors) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/contributors.json"));
                }
                return new MockResponse().setResponseCode(404);
            }
        });

    }

}
