package com.hawk.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hawk.mvp.component.BaseComponent;
import com.hawk.mvp.ui.display.BaseDisplay;
import com.hawk.mvp.ui.presenter.BasePresenter;
import com.hawk.mvp.ui.presenter.BasePresenterDelegate;
import com.hawk.mvp.ui.presenter.GetPresenterDelegate;
import com.hawk.mvp.ui.view.BaseView;
import com.hawk.mvp.util.Preconditions;


public abstract class MvpDiActivity<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>,
		C extends BaseComponent<V, VC, P>> extends AppCompatActivity
		implements GetPresenterDelegate<V, VC, P> {
	protected BaseDisplay display;
	protected C component;
	protected P mPresenter;
	private BasePresenterDelegate<V, VC, P> mPresenterDelegate;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initializeDependence();
		initializDisplay();
		Preconditions.checkNotNull(component, "component not inited");
		mPresenter = component.presenter();
		mPresenterDelegate = new BasePresenterDelegate<V, VC, P>() {
			@Override
			protected P createPresenter() {
				return MvpDiActivity.this.createPresenter();
			}
		};
		mPresenterDelegate.onCreate(display);
	}

	@Override
	protected void onDestroy() {
		mPresenterDelegate.onDestroy();
		mPresenterDelegate = null;
		display = null;
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		mPresenterDelegate.onStart();
		super.onStart();
	}

	@Override
	protected void onStop() {
		mPresenterDelegate.onStop();
		super.onStop();
	}

	protected abstract void initializeDependence();

	protected void initializDisplay() {}

	@Override
	public BasePresenterDelegate<V, VC, P> getPresenterDelegate() {
		return mPresenterDelegate;
	}

	protected final P createPresenter() {
		return mPresenter;
	}

}
