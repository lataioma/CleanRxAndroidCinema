package com.crpr.androidcinema.data.api.factories;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import okhttp3.OkHttpClient;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by claudioribeiro on 10/07/16.
 */
public class OkHttpClientFactoryTest {

    private InterceptorsFactory interceptorsFactory;

    @Before
    public void setup(){
        interceptorsFactory = Mockito.mock(InterceptorsFactory.class);
    }

    @Test
    public void getConfigClient(){
        OkHttpClientFactory factory = new OkHttpClientFactory(interceptorsFactory);

        assertThat(factory, notNullValue());

        OkHttpClient okHttpClient = factory.getClient(OkHttpClientFactory.CONFIG_CLIENT);

        assertThat(okHttpClient, notNullValue());
        verify(interceptorsFactory, times(1)).get(InterceptorsFactory.AUTH);
        verifyNoMoreInteractions(interceptorsFactory);
        assertEquals(30*1000, okHttpClient.readTimeoutMillis());
        assertEquals(30*1000, okHttpClient.connectTimeoutMillis());
        assertEquals(1, okHttpClient.networkInterceptors().size());
    }

    @Test
    public void getEmptyClient(){
        OkHttpClientFactory factory = new OkHttpClientFactory(interceptorsFactory);

        assertThat(factory, notNullValue());

        OkHttpClient okHttpClient = factory.getClient(-1);

        assertThat(okHttpClient, notNullValue());
        verify(interceptorsFactory, times(0)).get(InterceptorsFactory.AUTH);
        verifyZeroInteractions(interceptorsFactory);
        assertEquals(30*1000, okHttpClient.readTimeoutMillis());
        assertEquals(30*1000, okHttpClient.connectTimeoutMillis());
        assertEquals(0, okHttpClient.networkInterceptors().size());
    }
}
