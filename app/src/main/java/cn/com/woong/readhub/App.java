package cn.com.woong.readhub;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by wong on 2018/3/7.
 */

public class App extends Application implements HasSupportFragmentInjector, HasActivityInjector {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Utils.init(mInstance);
//        DaggerAppComponent.create().inject(this);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static App getInstance() {
        return mInstance;
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
