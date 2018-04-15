package cn.com.woong.readhub;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;
import butterknife.ButterKnife;
import cn.com.woong.readhub.di.component.DaggerAppComponent;
import cn.com.woong.readhub.domain.ApiManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by wong on 2018/3/7.
 */

public class App extends Application implements HasSupportFragmentInjector, HasActivityInjector {
    private static App mInstance;
    private ApiManager mApiManager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApiManager = new ApiManager();
        Utils.init(mInstance);
        Stetho.initializeWithDefaults(this);
        ButterKnife.setDebug(true);
        DaggerAppComponent.create().inject(this);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static App getInstance() {
        return mInstance;
    }


    public static <T> T apiService(Class<T> clz) {
        return getInstance().mApiManager.getService(clz);
    }

    public <T> void addApiService(Class<T> clz) {
        getInstance().mApiManager.addService(clz);
    }

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjectorSupportFragment;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectorActivity;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjectorSupportFragment;
    }
}
