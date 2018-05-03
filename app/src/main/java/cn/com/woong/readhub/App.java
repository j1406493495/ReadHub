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
import cn.com.woong.readhub.domain.apiservice.GankioApiService;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by wong on 2018/3/7.
 */

public class App extends Application implements HasSupportFragmentInjector, HasActivityInjector {
    private static App mInstance;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjectorSupportFragment;
    private ApiManager mApiManager = null;

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static <T> T apiService(Class<T> clz) {
        return getInstance().mApiManager.getService(clz);
    }

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApiManager = new ApiManager();
        Utils.init(mInstance);
        Stetho.initializeWithDefaults(this);
        ButterKnife.setDebug(true);
        DaggerAppComponent.create().inject(this);

        addApiService(GankioApiService.class);

//        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        //58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    }

    public <T> void addApiService(Class<T> clz) {
        getInstance().mApiManager.addService(clz);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectorActivity;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjectorSupportFragment;
    }
}
