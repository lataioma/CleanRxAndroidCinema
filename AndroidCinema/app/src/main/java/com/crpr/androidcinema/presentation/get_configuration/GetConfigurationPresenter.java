package com.crpr.androidcinema.presentation.get_configuration;

import com.crpr.androidcinema.data.api.models.configuration.ApiConfiguration;
import com.crpr.androidcinema.domain.get_configuration.GetConfiguration;
import com.crpr.androidcinema.presentation.common.AppView;
import com.crpr.androidcinema.presentation.common.Presenter;

/**
 * Created by claudioribeiro on 09/07/16.
 */
public class GetConfigurationPresenter extends Presenter implements GetConfiguration.Presenter {

    private GetConfiguration.Interactor _interactor;
    private GetConfiguration.View _view;

    public GetConfigurationPresenter(GetConfiguration.Interactor interactor){
        this._interactor = interactor;
    }

    @Override
    public void bindView(AppView view) {
        this._view = (GetConfiguration.View) view;
    }

    public final void getConfiguration(){
        if(_isMakingRequest){
            return;
        }

        _isMakingRequest = true;
        _subscription = _interactor.getConfiguration()
                            .subscribe(this::onReceiveConfiguration,
                                    this::onError);
    }

    public final void onReceiveConfiguration(ApiConfiguration configuration){
        _isMakingRequest = false;
        _view.displayConfig(configuration);
    }

    public final void onError(Throwable throwable) {
        _isMakingRequest = false;
        _view.showError(throwable.getMessage());
    }
}