package com.njakawaii.baseprojectstrucure.presenter.mappers;

import com.njakawaii.baseprojectstrucure.model.dto.ContributorDTO;
import com.njakawaii.baseprojectstrucure.other.BaseTest;
import com.njakawaii.baseprojectstrucure.presenter.vo.Contributor;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class RepoContributorsMapperTest extends BaseTest {

    @Inject
    protected RepoContributorsMapper repoContributorsMapper;

    private List<ContributorDTO> contributorDTOs;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        ContributorDTO[] contributorDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/contributors.json"), ContributorDTO[].class);
        contributorDTOs = Arrays.asList(contributorDTOArray);

    }


    @Test
    public void testCall() throws Exception {
        List<Contributor> contributorList = repoContributorsMapper.call(contributorDTOs);
        assertEquals(11, contributorList.size());

        assertEquals("hotchemi", contributorList.get(0).getName());
        assertEquals("mrmike", contributorList.get(1).getName());
        assertEquals("amitkot", contributorList.get(2).getName());
        assertEquals("maarekj", contributorList.get(10).getName());
    }
}