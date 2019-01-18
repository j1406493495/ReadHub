package cn.com.woong.readhub

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.tencent.bugly.Bugly
import javax.inject.Inject
import butterknife.ButterKnife
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.dagger.component.DaggerAppComponent
import cn.com.woong.readhub.domain.ApiManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by wong on 2018/3/7.
 */

class App : Application(), HasSupportFragmentInjector, HasActivityInjector {
//    @Inject var dispatchingAndroidInjectorActivity: DispatchingAndroidInjector<Activity>? = null
//    @Inject var dispatchingAndroidInjectorSupportFragment: DispatchingAndroidInjector<Fragment>? = null
    var dispatchingAndroidInjectorActivity: DispatchingAndroidInjector<Activity>? = null
    var dispatchingAndroidInjectorSupportFragment: DispatchingAndroidInjector<Fragment>? = null

    var mApiManager: ApiManager? = null

    companion object {
        var mInstance: App? = null

        fun getInstance(): App? {
            return mInstance
        }

        fun getAppContext(): Context? {
            return mInstance?.getApplicationContext()
        }

        fun <T> apiService(clz: Class<T>): T? {
            return getInstance()?.mApiManager?.getService(clz)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mApiManager = ApiManager()
        Utils.init(this)
        Stetho.initializeWithDefaults(this)
        ButterKnife.setDebug(true)
        Bugly.init(getApplicationContext(), "aee572880a", true)
//        DaggerAppComponent.create().inject(this)
        DBManager.getInstance(this).init()
    }

    fun <T> addApiService(clz: Class<T>) {
        getInstance()?.mApiManager?.addService(clz)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjectorActivity
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjectorSupportFragment
    }
}