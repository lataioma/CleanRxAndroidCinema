package com.crpr.androidcinema.domain.discover;

import com.crpr.androidcinema.base.BaseTest;
import com.crpr.androidcinema.base.ResourceFiles;
import com.crpr.androidcinema.data.api.models.ApiMovie;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by claudioribeiro on 31/07/16.
 */
public class ListMovieModelTest extends BaseTest{

    private ApiMovie mockResponse;

    @Before
    public void setup(){
        mockResponse = loadResource(ResourceFiles.API_MOVIE, ApiMovie.class);
    }

    @Test
    public void builderTest(){
        ListMovieModel model = ListMovieModel
                                .id(mockResponse.getId())
                                .title(mockResponse.getTitle())
                                .imagePath(mockResponse.getBackdropPath())
                                .popularity(mockResponse.getPopularity())
                                .votesAvg(mockResponse.getVoteAverage())
                                .build();



        assertThat(model, notNullValue());
        assertEquals(mockResponse.getId(), model.getId());
        assertEquals(mockResponse.getTitle(), model.getTitle());
        assertEquals(mockResponse.getBackdropPath(), model.getImagePath());
        assertEquals(mockResponse.getPopularity(), model.getPopularity());
        assertEquals(mockResponse.getVoteAverage(), model.getVoteAverage());
    }

}
