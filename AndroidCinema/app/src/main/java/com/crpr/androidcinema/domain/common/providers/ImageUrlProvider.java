package com.crpr.androidcinema.domain.common.providers;

import com.crpr.androidcinema.data.api.models.enums.Size;
import com.crpr.androidcinema.domain.common.configuration.ConfigurationModel;

/**
 * Created by claudioribeiro on 09/07/16.
 */
public class ImageUrlProvider {

    private static ImageUrlProvider _instance;

    public static ImageUrlProvider sharedInstance(){
        if(_instance == null){
            _instance = new ImageUrlProvider();
        }

        return _instance;
    }

    private ConfigurationModel _config;

    public void setCurrentConfig(ConfigurationModel configuration) {
        _config = configuration;
    }

    public String getUrlFor(Size size){
        return _config.getBaseUrl()+size.raw();
    }
}
