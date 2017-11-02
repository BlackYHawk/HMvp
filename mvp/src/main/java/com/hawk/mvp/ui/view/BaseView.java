package com.hawk.mvp.ui.view;

/**
 * Created by heyong on 2016/9/30.
 */

public interface BaseView<VC> {

    void setCallbacks(VC callbacks);

    boolean isModal();

}
