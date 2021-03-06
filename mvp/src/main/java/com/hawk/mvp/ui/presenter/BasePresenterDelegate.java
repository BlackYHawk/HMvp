package com.hawk.mvp.ui.presenter;


import com.hawk.mvp.ui.display.BaseDisplay;
import com.hawk.mvp.ui.view.BaseView;
import com.hawk.mvp.util.Preconditions;

/**
 * Created by lan on 2016/10/27.
 */

public abstract class BasePresenterDelegate<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>> {
    private P mPresenter;

    public void onCreate(BaseDisplay display) {
        mPresenter = createPresenter();
        checkPresenter();
        mPresenter.setDisplay(display);
        mPresenter.init();
    }

    public void onStart() {
        checkPresenter();
        mPresenter.resume();
    }

    public void onStop() {
        checkPresenter();
        mPresenter.pause();
    }

    public void attachView(V view) {
        checkPresenter();
        mPresenter.attachView(view);
    }

    public void detachView(V view) {
        checkPresenter();
        mPresenter.detachView(view);
    }

    public void onDestroy() {
        checkPresenter();
        mPresenter.suspend();
        mPresenter.setDisplay(null);
    }

    protected abstract P createPresenter();

    private void checkPresenter() {
        Preconditions.checkState(mPresenter != null, "You must call BasePresenterDelegate#onCreate! " +
                "And createPresenter must return non-null");
    }
}
