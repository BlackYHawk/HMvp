package com.hawk.mvp.component;

import android.support.annotation.NonNull;

import com.hawk.mvp.ui.presenter.BasePresenter;
import com.hawk.mvp.ui.view.BaseView;


/**
 * Created by lan on 2016/10/27.
 */

public interface BaseComponent<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>> {

    @NonNull
    P presenter();

}
