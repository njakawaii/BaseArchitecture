package com.njakawaii.baseprojectstrucure.integration.other;

import com.njakawaii.baseprojectstrucure.model.api.ApiInterface;
import com.njakawaii.baseprojectstrucure.model.api.ApiModule;
import com.njakawaii.baseprojectstrucure.other.TestConst;
import com.njakawaii.baseprojectstrucure.other.TestUtils;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import java.io.IOException;

public class IntegrationApiModule {

    public ApiInterface getApiInterface(MockWebServer mockWebServer) throws IOException {
        mockWebServer.start();
        TestUtils testUtils = new TestUtils();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/" + TestConst.TEST_OWNER + "/repos")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/repos.json"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/branches")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/branches.json"));
                } else if (request.getPath().equals("/repos/" + TestConst.TEST_OWNER + "/" + TestConst.TEST_REPO + "/contributors")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("json/contributors.json"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        mockWebServer.setDispatcher(dispatcher);
        HttpUrl baseUrl = mockWebServer.url("/");
        return ApiModule.getApiInterface(baseUrl.toString());
    }
}
